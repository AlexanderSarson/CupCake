package persistence;

import logic.Bottom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Benjamin Paepke
 */
@RunWith(MockitoJUnitRunner.class)
public class BottomMapperTest {
    @InjectMocks
    BottomMapper bottomMapper;
    @Mock
    private ResultSet resSet;
    @Mock
    private PreparedStatement statement;
    @Mock
    private SQLConnection sqlConnection;
    @Mock
    private Connection connection;

    @Test(expected = ProductException.class)
    public void test_createProduct_with_existing_topping() throws SQLException, ProductException {
        Bottom top = new Bottom(5,"Chocolate");
        when(resSet.next()).thenReturn(true).thenReturn(false);
        when(sqlConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(sqlConnection.selectQuery(statement)).thenReturn(resSet);

        bottomMapper.createProduct(top);
        verify(sqlConnection,times(0)).executeQuery(any(PreparedStatement.class));
    }

    @Test
    public void test_createProduct() throws SQLException, ProductException {
        Bottom top = new Bottom(5,"Chocolate");
        when(resSet.next()).thenReturn(false);
        when(sqlConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(sqlConnection.selectQuery(statement)).thenReturn(resSet);
        when(sqlConnection.executeQuery(statement)).thenReturn(true);

        bottomMapper.createProduct(top);
        verify(sqlConnection, times(1)).executeQuery(any(PreparedStatement.class));
    }

    @Test(expected = ProductException.class)
    public void test_updateProduct_without_existing_product() throws SQLException, ProductException {
        Bottom top = new Bottom(5,"Chocolate");
        when(resSet.next()).thenReturn(false);
        when(sqlConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(sqlConnection.selectQuery(statement)).thenReturn(resSet);

        bottomMapper.updateProduct(top);
        verify(sqlConnection, times(0)).executeQuery(statement);
    }

    @Test
    public void test_updateProduct_with_existing_product() throws SQLException, ProductException {
        Bottom top = new Bottom(5,"Chocolate");
        when(resSet.next()).thenReturn(true).thenReturn(true);
        when(sqlConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(sqlConnection.selectQuery(statement)).thenReturn(resSet);
        when(sqlConnection.executeQuery(statement)).thenReturn(true);

        bottomMapper.updateProduct(top);
        verify(sqlConnection, times(1)).executeQuery(statement);
    }
}