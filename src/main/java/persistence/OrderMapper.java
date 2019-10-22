package persistence;

import logic.*;

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
public class OrderMapper {
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

    public Order createOrder(Order order) {return null;}
    public void updateOrder(Order order) {}
    public void deleteOrder(Order order) {}

}
