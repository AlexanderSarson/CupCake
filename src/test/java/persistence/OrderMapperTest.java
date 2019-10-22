package persistence;

import logic.Bottom;
import logic.Cupcake;
import logic.Topping;
import org.junit.After;
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
    public void getAllOrders() throws SQLException {
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