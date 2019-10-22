package persistence;

import logic.Order;
import logic.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderMapper {
    private SQLConnection connection;
    public OrderMapper(SQLConnection connection) {
        this.connection=connection;
    }

    public ArrayList<Order> getAllOrders(User user) { return null;}

    public Order createOrder(Order order, User user) throws SQLException {
            //Burde man pakke nedenstående ind i en metode der tjekker om cupcake allerede findes i db?
    try {
        String sql = "SELECT * from cupcakes WHERE cupcake id = ?";
        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        //Skal vi sætte autocommit til false inden for-loopet??
        for (int i = 0; i < order.getSize(); i++) {
            ps.setInt(1, order.getLineItem(i).getCupcake().getId());
            ResultSet rs = connection.selectQuery(ps);
            //ID'et på cupcake skal gemmes
            if (!rs.next()) {
                String cupcakePrepare = "INSERT INTO cupcake(cupcake_id,topping_id,bottom_id) values (?,?,?)";
                PreparedStatement cupcakePS = connection.getConnection().prepareStatement(cupcakePrepare);
                int nextID = connection.lastID();
                cupcakePS.setLong(1, nextID);
                order.getLineItem(i).getCupcake().setId(nextID);
                cupcakePS.setInt(2, order.getLineItem(i).getCupcake().getTopping().getId());
                cupcakePS.setInt(3, order.getLineItem(i).getCupcake().getBottom().getId());
                if (!connection.executeQuery(cupcakePS)) {
                    throw new SQLException("Cupcake could not be created");
                }
            }
        }
        connection.getConnection().setAutoCommit(false);
        //Insert into orders
        String orderPrepare = "INSERT INTO Orders (order_id,user_id,order_date) values(?,?,?)";
        PreparedStatement orderPS = connection.getConnection().prepareStatement(orderPrepare);
        //Er ikke lige sikker på hvordan vi får fat i vores order ID eller hvordan lastID() skal bruges... :-(
        orderPS.setInt(1, order.getId());
        orderPS.setInt(2, user.getId());
        orderPS.setDate(3, Date.valueOf(LocalDate.now()));
        if (!connection.executeQuery(orderPS)) {
            throw new SQLException("Could not insert into orders");
        }
        //Insert into lineitems
        String lineItemPrepare = "INSERT INTO LineItems (cupcake_id,order_id,lineitem_qty) values(?,?,?)";
        PreparedStatement lineItemPS = connection.getConnection().prepareStatement(lineItemPrepare);
        int lineItemID = connection.lastID();
        for (int i = 0; i < order.getSize(); i++) {
            lineItemPS.setInt(1, order.getLineItem(i).getCupcake().getId());
            lineItemPS.setInt(2, order.getId());
            lineItemPS.setInt(3, order.getLineItem(i).getQuantity());
            if (!connection.executeQuery(lineItemPS)) {
                throw new SQLException("Coult not insert into lineitems");
            }

        }
        connection.getConnection().commit();
    }catch (SQLException ex){
        connection.getConnection().rollback();
        //TODO (Oscar) Create OrderException
        throw new SQLException("Order creation failed");
    }
    finally{
        connection.getConnection().setAutoCommit(true);
    }
    return order;

    }

    public void updateOrder(Order order) {}
    public void deleteOrder(Order order) {}

}
