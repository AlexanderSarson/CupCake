package persistence;

import logic.*;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Mapping of orders from the database to java objects.
 *
 * @author Benjamin Paepke
 * @version 1.0
 */
class OrderMapper {

    public ArrayList<Order> getAllOrders(User user) throws OrderException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders where Orders.user_id = ?";
        try (Connection connection = DataSource.getInstance().getConnection();
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
        } catch (SQLException | IOException e) {
            throw new OrderException("Could not fetch user's orders.");
        }
        return orders;
    }

    public Order createOrder(Order order, User user) throws OrderException {
        String sql = "SELECT * from Cupcakes WHERE cupcake_id = ?";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < order.getSize(); i++) {
                ps.setInt(1, order.getLineItem(i).getCupcake().getId());
                ResultSet rs = ps.executeQuery();
                //ID'et på cupcake skal gemmes
                if (!rs.next()) {
                    String cupcakePrepare = "INSERT INTO Cupcakes(cupcake_id,topping_id,bottom_id) values (?,?,?)";
                    try (PreparedStatement cupcakePS = connection.prepareStatement(cupcakePrepare)) {
                        int nextID = DataSource.lastID(connection, cupcakePS);
                        cupcakePS.setLong(1, nextID);
                        order.getLineItem(i).getCupcake().setId(nextID);
                        cupcakePS.setInt(2, order.getLineItem(i).getCupcake().getTopping().getId());
                        cupcakePS.setInt(3, order.getLineItem(i).getCupcake().getBottom().getId());
                        if (cupcakePS.executeUpdate() != 1) {
                            connection.rollback();
                            connection.setAutoCommit(false);
                            throw new SQLException("Cupcake could not be created");
                        }
                    }
                }
            }
            connection.setAutoCommit(false);
            //Insert into orders
            int orderID = 0;
            String orderPrepare = "INSERT INTO Orders (user_id,order_date) values(?,?)";
            try (PreparedStatement orderPS = connection.prepareStatement(orderPrepare)) {
                orderPS.setInt(1, user.getId());
                orderPS.setDate(2, Date.valueOf(LocalDate.now()));
                if (orderPS.executeUpdate() != 1) {
                    connection.rollback();
                    connection.setAutoCommit(false);
                    throw new SQLException("Could not insert into orders");
                }
                order.setId(DataSource.lastID(connection, orderPS));
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
            connection.commit();
        } catch (IOException | SQLException e) {
            throw new OrderException("Could not create order");
        }
        return order;
    }

    /**
     * Deletes a order stored persistently.
     *
     * @param order The order to be removed.
     * @throws OrderException If anythings goes wrong with removing the order.
     * @throws SQLException
     */
    public void deleteOrder(Order order) throws OrderException, SQLException {
        String sql = "DELETE FROM LineItems where order_id = ?";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (isOrderStored(connection, order)) {
                connection.setAutoCommit(false);
                statement.setInt(1, order.getId());
                if (statement.executeUpdate() != 1) {
                    sql = "DELETE FROM Orders where order_id = ?";
                    try (PreparedStatement ps = connection.prepareStatement(sql);) {
                        statement.setInt(1, order.getId());
                        if (ps.executeUpdate() != 0) {
                            // IF we succeeded in both removing all the LineItems and the actual order, commit the changes.
                            connection.commit();
                        } else {
                            // If we succeeded in removing the LineItems but not the actual order, do a rollback.
                            connection.rollback();
                        }
                    }
                }
                connection.setAutoCommit(true);
            } else {
                throw new OrderException("Order is not stored");
            }
        } catch (SQLException | IOException e) {
            throw new OrderException("Connection error");
        }
    }


    /**
     * Checks if a given order is stored in the database.
     *
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
