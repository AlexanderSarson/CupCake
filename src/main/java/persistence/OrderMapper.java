package persistence;

import logic.*;

import javax.xml.transform.Result;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Mapping of orders from the database to java objects.
 * @author Benjamin Paepke
 * @version 1.0
 */
class OrderMapper {
    private SQLConnection connection;
    public OrderMapper(SQLConnection connection) {
        this.connection = connection;
    }

    public ArrayList<Order> getAllOrders(User user) throws OrderException {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Orders where Orders.user_id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1,user.getId());
            ResultSet rs = connection.selectQuery(statement);
            while(rs.next()) {
                int id = rs.getInt("order_id");
                LocalDate date = (rs.getDate("order_date")).toLocalDate();
                Order order = new Order(date);
                order.setId(id);
                orders.add(order);
            }
            if(orders.size() == 0) {
                throw new OrderException("User does not have any orders");
            } else {
                for(Order order : orders) {
                    sql = "select " +
                            "Cupcakes.cupcake_id, LineItems.lineitem_qty, " +
                            "Toppings.topping_id, Toppings.topping_name, Toppings.topping_price, Toppings.topping_picture, " +
                            "Bottoms.bottom_id, Bottoms.bottom_name, Bottoms.bottom_price, Bottoms.bottom_picture " +
                            "from LineItems " +
                            "join Cupcakes on LineItems.cupcake_id = Cupcakes.cupcake_id " +
                            "join Bottoms on Cupcakes.bottom_id = Bottoms.bottom_id " +
                            "join Toppings on Cupcakes.topping_id = Toppings.topping_id " +
                            "where LineItems.order_id = ?";
                    statement = connection.getConnection().prepareStatement(sql);
                    rs = connection.selectQuery(statement);
                    while(rs.next()) {
                        // Get all cupcakes from a lineItem
                        // Topping
                        int toppingID = rs.getInt("topping_id");
                        String toppingName = rs.getString("topping_name");
                        int toppingPrice = rs.getInt("topping_price");
                        //String toppingPicture = rs.getString("topping_picture");
                        Topping topping = new Topping(toppingPrice,toppingName);
                        topping.setId(toppingID);
                        // Bottom
                        int bottomID = rs.getInt("bottom_id");
                        String bottomName = rs.getString("bottom_name");
                        int bottomPrice = rs.getInt("bottom_price");
                        //String bottomPicture = rs.getString("bottom_picture");
                        Bottom bottom = new Bottom(bottomPrice,bottomName);
                        bottom.setId(bottomID);
                        // Cupcake
                        int cupcakeID = rs.getInt("cupcake_id");
                        Cupcake cupcake = new Cupcake(bottom,topping);
                        cupcake.setId(cupcakeID);
                        // LineItem
                        int qty = rs.getInt("lineitem_qty");
                        LineItem item = new LineItem(cupcake,qty);
                        order.addLineItem(item);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order createOrder(Order order, User user) throws SQLException, OrderException {
            //Burde man pakke nedenstående ind i en metode der tjekker om cupcake allerede findes i db?
        try {
            connection.getConnection().setAutoCommit(false);
            String sql = "SELECT * from cupcakes WHERE cupcake id = ?";
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            //Skal vi sætte autocommit til false inden for-loopet??
            for (int i = 0; i < order.getSize(); i++) {
                ps.setInt(1, order.getLineItem(i).getCupcake().getId());
                ResultSet rs = connection.selectQuery(ps);
                //ID'et på cupcake skal gemmes
                if (!rs.next()) {
                    String cupcakePrepare = "INSERT INTO cupcake(topping_id,bottom_id) values (?,?)";
                    PreparedStatement cupcakePS = connection.getConnection().prepareStatement(cupcakePrepare);
                    cupcakePS.setInt(1, order.getLineItem(i).getCupcake().getTopping().getId());
                    cupcakePS.setInt(2, order.getLineItem(i).getCupcake().getBottom().getId());
                    if (!connection.executeQuery(cupcakePS)) {
                        throw new SQLException("Cupcake could not be created");
                    }
                    int CupCakeID = connection.lastID();
                    order.getLineItem(i).getCupcake().setId(CupCakeID);
                }
            }

            //Insert into orders
            String orderPrepare = "INSERT INTO Orders (user_id,order_date) values(?,?)";
            PreparedStatement orderPS = connection.getConnection().prepareStatement(orderPrepare);
            //Er ikke lige sikker på hvordan vi får fat i vores order ID eller hvordan lastID() skal bruges... :-(
            orderPS.setInt(1, user.getId());
            orderPS.setDate(2, Date.valueOf(LocalDate.now()));
            if (!connection.executeQuery(orderPS)) {
                throw new SQLException("Could not insert into orders");
            }
            int OrderID = connection.lastID();
            order.setId(OrderID);

            //Insert into lineitems
            String lineItemPrepare = "INSERT INTO LineItems (cupcake_id,order_id,lineitem_qty) values(?,?,?)";
            PreparedStatement lineItemPS = connection.getConnection().prepareStatement(lineItemPrepare);
            int lineItemID = connection.lastID();
            for (int i = 0; i < order.getSize(); i++) {
                lineItemPS.setInt(1, order.getLineItem(i).getCupcake().getId());
                lineItemPS.setInt(2, order.getId());
                lineItemPS.setInt(3, order.getLineItem(i).getQuantity());
                if (!connection.executeQuery(lineItemPS)) {
                    throw new SQLException("Could not insert into lineitems");
                }
            }
            connection.getConnection().commit();
        } catch (SQLException ex){
            connection.getConnection().rollback();
            //TODO (Oscar) Create OrderException
            throw new OrderException("Order creation failed");
        }
        finally{
            connection.getConnection().setAutoCommit(true);
        }
        return order;
    }
    public void updateOrder(Order order) {

    }

    /**
     * Deletes a order stored persistently.
     * @param order The order to be removed.
     * @throws OrderException If anythings goes wrong with removing the order.
     * @throws SQLException
     */
    public void deleteOrder(Order order) throws OrderException, SQLException {
        try {
            if(isOrderStored(order)) {
                connection.getConnection().setAutoCommit(false);
                String sql = "DELETE FROM LineItems where order_id = ?";
                PreparedStatement statement = connection.getConnection().prepareStatement(sql);
                statement.setInt(1, order.getId());
                if(connection.executeQuery(statement)) {
                    sql = "DELETE FROM Orders where order_id = ?";
                    statement = connection.getConnection().prepareStatement(sql);
                    statement.setInt(1, order.getId());
                    if(connection.executeQuery(statement)) {
                        // IF we succeeded in both removing all the LineItems and the actual order, commit the changes.
                        connection.getConnection().commit();
                    } else {
                        // If we succeeded in removing the LineItems but not the actual order, do a rollback.
                        connection.getConnection().rollback();
                    }
                }
                connection.getConnection().setAutoCommit(true);
            }
            else {
                throw new OrderException("Order is not stored");
            }
        } catch (SQLException e) {
            connection.getConnection().rollback();
            throw new OrderException("Connection error");
        } finally {
            connection.getConnection().setAutoCommit(false);
        }
    }


    /**
     * Checks if a given order is stored in the database.
     * @param order The order to be checked.
     * @return TRUE if the order is currently stored, FALSE if the order is not currently stored.
     * @throws SQLException If there is a connection error or MySQL syntax error.
     */
    private boolean isOrderStored(Order order) throws SQLException {
        if(order.getId() == 0)
            return false;
        else {
            String sql = "SELECT * From Orders where order_id = ?";
            PreparedStatement statement = connection.getConnection().prepareStatement(sql);
            statement.setInt(1,order.getId());
            ResultSet rs = connection.selectQuery(statement);
            return rs.next();
        }
    }
}
