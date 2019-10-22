package persistence;

import logic.Bottom;
import logic.Cupcake;
import logic.Topping;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.*;

import static org.junit.Assert.*;
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
    @Ignore
    public void getAllOrders() throws SQLException {
        // Res order
        when(resSet.getInt("cupcake_id")).thenReturn(1);
        when(resSet.getInt("user_id")).thenReturn(1);
        when(resSet.getDate("order_date")).thenReturn(Date.valueOf("2019-04-02"));
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