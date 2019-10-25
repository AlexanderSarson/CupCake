package persistence;

import logic.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Benjamin Paepke
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderMapperTest {
    @InjectMocks
    private OrderMapper orderMapper;
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resSet;
    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;

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
        //when(resSet.getString("bottom_picture")).thenReturn("chocolatePicture");
        // Topping
        when(resSet.getInt("topping_id")).thenReturn(6);
        when(resSet.getString("topping_name")).thenReturn("BlueBerry");
        when(resSet.getInt("topping_price")).thenReturn(11);
        //when(resSet.getString("topping_picture")).thenReturn("blueBerryPicture");

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);

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
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);

        User user = new User(1,"Peter Larsen", "larsen@example.com",Role.CUSTOMER,null);
        ArrayList<Order> orders = orderMapper.getAllOrders(user);
    }

    @Test (expected = OrderException.class)
    public void test_create_order_when_insert_order_fails() throws SQLException, OrderException {
        Bottom bot = new Bottom (5,"Cherry");
        Topping top = new Topping (5,"Coconut");
        Cupcake cup = new Cupcake (bot,top);
        Account acc = new Account(10000);
        User user = new User(1,"Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        Order order = new Order();
        LineItem lineItem = new LineItem(cup,5);
        order.addLineItem(lineItem);
        when(resSet.next()).thenReturn(true).thenReturn(false).thenReturn(true);

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        when(statement.executeUpdate()).thenReturn(1).thenReturn(0);

        orderMapper.createOrder(order,user);
        verify(statement,times(1)).executeUpdate();

    }
    @Test (expected = OrderException.class)
    public void test_create_order_when_insert_lineitem_fails() throws SQLException, OrderException {
        Bottom bot = new Bottom (5,"Cherry");
        Topping top = new Topping (5,"Coconut");
        Cupcake cup = new Cupcake (bot,top);
        Account acc = new Account(10000);
        User user = new User(1,"Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        Order order = new Order();
        LineItem lineItem = new LineItem(cup,5);
        order.addLineItem(lineItem);
        when(resSet.next()).thenReturn(true).thenReturn(false).thenReturn(true).thenReturn(true);

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        when(statement.executeUpdate()).thenReturn(1).thenReturn(0);

        orderMapper.createOrder(order,user);
        verify(statement,times(2)).executeUpdate();

    }
    @Test
    public void test_create_order() throws SQLException, OrderException {
        Bottom bot = new Bottom (5,"Cherry");
        Topping top = new Topping (5,"Coconut");
        Cupcake cup = new Cupcake (bot,top);
        Account acc = new Account(10000);
        User user = new User(1,"Peter Larsen","peter@example.com",Role.CUSTOMER,acc);
        Order order = new Order();
        LineItem lineItem = new LineItem(cup,5);
        order.addLineItem(lineItem);
        when(resSet.next()).thenReturn(true).thenReturn(false).thenReturn(true).thenReturn(true);

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        when(statement.executeUpdate()).thenReturn(1).thenReturn(1).thenReturn(1);

        orderMapper.createOrder(order,user);
        verify(statement,times(2)).executeUpdate();
    }
    @Test
    public void updateOrder() {
    }

    @Test
    public void deleteOrder() {

    }
}