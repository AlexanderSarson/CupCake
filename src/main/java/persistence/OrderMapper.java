package persistence;

import logic.*;
import org.apache.xpath.operations.Or;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapping of orders from the database to java objects
 * @author Benjamin Paepke
 * @author Oscar Laurberg
 * @version 1.0
 */
class OrderMapper {
    private DataSource dataSource;
    public OrderMapper(DataSource dataSource) { this.dataSource = dataSource;}

    /**
     * Gets a specific users orders from database
     * @param user the user we which orders we want to get
     * @return the users orders
     * @throws OrderException if the user don't have any orders in the database
     */
    public List<Order> getAllOrders(User user) throws OrderException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders where Orders.user_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("order_id");
                LocalDate date = (rs.getDate("order_date")).toLocalDate();
                Order order = new Order(date);
                order.setId(id);
                orders.add(order);
            }
            if (orders.size() == 0) {
                throw new OrderException("User does not have any orders");
            } else {
                for (Order order : orders) {
                    sql = "select * from LineItems join Cupcakes on LineItems.cupcake_id = Cupcakes.cupcake_id join Bottoms on Cupcakes.bottom_id = Bottoms.bottom_id  join Toppings on Cupcakes.topping_id = Toppings.topping_id where LineItems.order_id = ?";
                    try (PreparedStatement orderPS = connection.prepareStatement(sql)) {
                        orderPS.setInt(1, order.getId());
                        ResultSet resultSet = orderPS.executeQuery();
                        while (resultSet.next()) {
                            // Get all cupcakes from a lineItem
                            // Topping
                            int toppingID = resultSet.getInt("topping_id");
                            String toppingName = resultSet.getString("topping_name");
                            int toppingPrice = resultSet.getInt("topping_price");
                            //String toppingPicture = rs.getString("topping_picture");
                            Topping topping = new Topping(toppingPrice, toppingName);
                            topping.setId(toppingID);
                            // Bottom
                            int bottomID = resultSet.getInt("bottom_id");
                            String bottomName = resultSet.getString("bottom_name");
                            int bottomPrice = resultSet.getInt("bottom_price");
                            //String bottomPicture = rs.getString("bottom_picture");
                            Bottom bottom = new Bottom(bottomPrice, bottomName);
                            bottom.setId(bottomID);
                            // Cupcake
                            int cupcakeID = resultSet.getInt("cupcake_id");
                            Cupcake cupcake = new Cupcake(bottom, topping);
                            cupcake.setId(cupcakeID);
                            // LineItem
                            int qty = resultSet.getInt("lineitem_qty");
                            LineItem item = new LineItem(cupcake, qty);
                            order.addLineItem(item);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new OrderException("Could not fetch user's orders.");
        }
        return orders;
    }

    /**
     * Creates an order in the database
     * @param order is the order object which contains LineItems with cupcakes and quantity
     * @param user is the user who "owns" the order
     * @return the order
     * @throws SQLException
     * @throws OrderException if anything goes wrong while creating order
     */
    public Order createOrder(Order order, User user) throws OrderException, UserBalanceException {
        String sql = "SELECT cupcake_id from Cupcakes WHERE topping_id = ? and bottom_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < order.getSize(); i++) {
                ps.setInt(1, order.getLineItem(i).getCupcake().getTopping().getId());
                ps.setInt(2, order.getLineItem(i).getCupcake().getBottom().getId());
                ResultSet rs = ps.executeQuery();
                //ID'et på cupcake skal gemmes
                if (!rs.next()) {
                    String cupcakePrepare = "INSERT INTO Cupcakes(topping_id,bottom_id) values (?,?)";
                    try (PreparedStatement cupcakePS = connection.prepareStatement(cupcakePrepare)) {
                        cupcakePS.setInt(1, order.getLineItem(i).getCupcake().getTopping().getId());
                        cupcakePS.setInt(2, order.getLineItem(i).getCupcake().getBottom().getId());
                        if (cupcakePS.executeUpdate() == 0) {
                            connection.rollback();
                            connection.setAutoCommit(false);
                            throw new SQLException("Cupcake could not be created");
                        }
                        int CupCakeID = dataSource.lastID(connection,cupcakePS);
                        order.getLineItem(i).getCupcake().setId(CupCakeID);
                    }
                }
                else {
                    order.getLineItem(i).getCupcake().setId(rs.getInt("cupcake_id"));
                }
            }
            connection.setAutoCommit(false);
            //Insert into orders
            int orderID = 0;
            String orderPrepare = "INSERT INTO Orders (user_id,order_date) values(?,?)";
            try (PreparedStatement orderPS = connection.prepareStatement(orderPrepare)) {
                orderPS.setInt(1, user.getId());
                // Why does this produce a date that is one day behind????
                if(order.getDate() == null)
                    order.setDate(LocalDate.now());
                orderPS.setDate(2, java.sql.Date.valueOf(order.getDate().toString()));
                if (orderPS.executeUpdate() != 1) {
                    connection.rollback();
                    connection.setAutoCommit(false);
                    throw new SQLException("Could not insert into orders");
                }
                order.setId(dataSource.lastID(connection, orderPS));
                int OrderID = dataSource.lastID(connection,orderPS);
                order.setId(OrderID);
            }
            //Insert into lineitems
            String lineItemPrepare = "INSERT INTO LineItems (cupcake_id,order_id,lineitem_qty) values(?,?,?)";
            try (PreparedStatement lineItemPS = connection.prepareStatement(lineItemPrepare)) {
                for (int i = 0; i < order.getSize(); i++) {
                    lineItemPS.setInt(1, order.getLineItem(i).getCupcake().getId());
                    lineItemPS.setInt(2, order.getId());
                    lineItemPS.setInt(3, order.getLineItem(i).getQuantity());
                    if (lineItemPS.executeUpdate() != 1) {
                        connection.rollback();
                        connection.setAutoCommit(false);
                        throw new SQLException("Coult not insert into lineitems");
                    }
                }
            }
            // Update the balance of the user
            String userBalance = "select user_balance from Accounts where user_id = ?";
            try (PreparedStatement balanceStatement = connection.prepareStatement(userBalance)) {
                balanceStatement.setInt(1,user.getId());
                ResultSet balance = balanceStatement.executeQuery();
                if(balance.next()) {
                    int newBalance = balance.getInt("user_balance") - order.getPrice();
                    if(newBalance >= 0) {
                        userBalance = "UPDATE Accounts SET user_balance = ? where user_id = ?";
                        try(PreparedStatement updateStatement = connection.prepareStatement(userBalance)) {
                            updateStatement.setInt(1,newBalance);
                            updateStatement.setInt(2,user.getId());
                            if(updateStatement.executeUpdate() != 1) {
                                connection.rollback();
                                connection.setAutoCommit(true);
                                throw new UserException("Could not update user account balance");
                            }
                        }
                        user.getAccount().setBalance(newBalance);
                    }
                    else {
                        throw new UserBalanceException("Insufficient funds!");
                    }
                } else {
                    throw new UserException("Could not get user balance.");
                }
            }
            connection.commit();
        } catch (SQLException | UserException e) {
            throw new OrderException("Could not create order");
        }
        return order;
    }

    /**
     * This is a method for testing, it should not be used outside of the Test Package.
     * @return number of orders currently in the database.
     */
    public int getNumberOfOrders() throws OrderException {
        int res = 0;
        String sql = "SELECT count(*) FROM Orders";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            if(!rs.next()) {
                throw new OrderException("Could not fetch number of orders!");
            }
            res = rs.getInt("count(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Deletes a order stored persistently.
     *
     * @param order The order to be removed.
     * @throws OrderException If anythings goes wrong with removing the order.
     * @throws SQLException
     */
    public void deleteOrder(Order order) throws OrderException{
        String sql = "DELETE FROM LineItems where order_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (isOrderStored(connection, order)) {
                connection.setAutoCommit(false);
                statement.setInt(1, order.getId());
                if (statement.executeUpdate() >= 1) {
                    sql = "DELETE FROM Orders where order_id = ?";
                    try (PreparedStatement ps = connection.prepareStatement(sql);) {
                        ps.setInt(1, order.getId());
                        if (ps.executeUpdate() != 0) {
                            // IF we succeeded in both removing all the LineItems and the actual order, commit the changes.
                            connection.commit();
                        } else {
                            // If we succeeded in removing the LineItems but not the actual order, do a rollback.
                            connection.rollback();
                        }
                    }
                }
                else {
                    connection.rollback();
                }
                connection.setAutoCommit(true);
            } else {
                throw new OrderException("Order is not stored");
            }
        } catch (SQLException e) {
            throw new OrderException("Connection error");
        }
    }

    /**
     * Checks if a given order is stored in the database.
     * @param order The order to be checked.
     * @return TRUE if the order is currently stored, FALSE if the order is not currently stored.
     * @throws SQLException If there is a connection error or MySQL syntax error.
     */
    private boolean isOrderStored(Connection connection, Order order) throws SQLException {
        if (order.getId() == 0)
            return false;
        else {
            String sql = "SELECT * From Orders where order_id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, order.getId());
                ResultSet rs = statement.executeQuery();
                return rs.next();
            }
        }
    }
}
