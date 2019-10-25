package persistence;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import logic.Topping;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Benjamin Paepke
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ToppingMapperTest {
    @InjectMocks
    ToppingMapper toppingMapper;
    @Mock
    private ResultSet resSet;
    @Mock
    private PreparedStatement statement;
    @Mock
    private DataSource dataSource;
    @Mock
    private SQLConnection DELETE_ME;
    @Mock
    private Connection connection;

    @Test
    public void test_createProduct() throws SQLException, ProductException {
        Topping top = new Topping(5,"Chocolate");
        when(resSet.next()).thenReturn(false).thenReturn(true).thenReturn(false);
        when(dataSource.lastID(any(Connection.class),any(PreparedStatement.class))).thenReturn(100);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);
        when(statement.executeQuery()).thenReturn(resSet);

        toppingMapper.createProduct(top);
        assertEquals(100,top.getId());
    }

    @Test(expected = ProductException.class)
    public void test_createProduct_with_connection_error() throws SQLException, ProductException {
        Topping top = new Topping(5,"Chocolate");
        when(resSet.next()).thenReturn(false).thenReturn(true).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(0);
        when(statement.executeQuery()).thenReturn(resSet);
        toppingMapper.createProduct(top);
    }
}