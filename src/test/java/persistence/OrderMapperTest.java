package persistence;

import logic.*;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * @author Benjamin Paepke
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderMapperTest {
    @InjectMocks
    private OrderMapper orderMapper;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet resSet;
    @Mock
    private SQLConnection connection;
    @Mock
    private Connection sqlConnection;

    @Test
    public void getAllOrders() throws SQLException, OrderException {
        // Get the order.
        when(resSet.next()).thenReturn(true).thenReturn(false).thenReturn(true).thenReturn(false);
        when(resSet.getInt("order_id")).thenReturn(1);
        when(resSet.getDate("order_date")).thenReturn(Date.valueOf("2019-01-01"));
        // Get line items associated with the order.
        when(resSet.getInt("cupcake_id")).thenReturn(2);
        when(resSet.getInt("lineitem_qty")).thenReturn(5);
        // Bottom
        when(resSet.getInt("bottom_id")).thenReturn(5);
        when(resSet.getString("bottom_name")).thenReturn("Chocolate");
        when(resSet.getInt("bottom_price")).thenReturn(10);
        when(resSet.getString("bottom_picture")).thenReturn("chocolatePicture");
        // Topping
        when(resSet.getInt("topping_id")).thenReturn(6);
        when(resSet.getString("topping_name")).thenReturn("BlueBerry");
        when(resSet.getInt("topping_price")).thenReturn(11);
        when(resSet.getString("topping_picture")).thenReturn("blueBerryPicture");

        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(ps);
        when(connection.selectQuery(ps)).thenReturn(resSet);

        User user = new User(1,"Peter Larsen", "larsen@example.com",Role.CUSTOMER,null);
        ArrayList<Order> orders = orderMapper.getAllOrders(user);
        Order order = orders.get(0);
        assertEquals(1, orders.size());
        assertEquals(1,order.getId());
        assertEquals(2,order.getLineItem(0).getCupcake().getId());
        assertEquals(5,order.getLineItem(0).getCupcake().getBottom().getId());
        assertEquals(6,order.getLineItem(0).getCupcake().getTopping().getId());
    }

    @Test(expected = OrderException.class)
    public void test_getAllOrders_with_no_orders() throws SQLException, OrderException {
        when(resSet.next()).thenReturn(false);
        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(ps);
        when(connection.selectQuery(ps)).thenReturn(resSet);

        User user = new User(1,"Peter Larsen", "larsen@example.com",Role.CUSTOMER,null);
        ArrayList<Order> orders = orderMapper.getAllOrders(user);
    }

    @Test
    public void createOrder() {
    }

    @Test
    public void updateOrder() {
    }

    @Test
    public void deleteOrder() {
    }
}