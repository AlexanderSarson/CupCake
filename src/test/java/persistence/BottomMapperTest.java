package persistence;

import logic.Bottom;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
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
    private DataSource dataSource;
    @Mock
    private PreparedStatement statement;
    @Mock
    private Connection connection;

    @Test(expected = ProductException.class)
    public void test_createProduct_with_existing_topping() throws SQLException, ProductException {
        Bottom top = new Bottom(5,"Chocolate");
        when(resSet.next()).thenReturn(true).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);

        bottomMapper.createProduct(top);
        verify(statement,times(0)).executeQuery();
    }

    @Test
    public void test_createProduct() throws SQLException, ProductException {
        Bottom top = new Bottom(5,"Chocolate");
        when(resSet.next()).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        when(statement.executeUpdate()).thenReturn(1);

        bottomMapper.createProduct(top);
        verify(statement, times(1)).executeUpdate();
    }

    @Test(expected = ProductException.class)
    public void test_updateProduct_without_existing_product() throws SQLException, ProductException {
        Bottom bottom = new Bottom(5,"Chocolate");
        bottom.setId(1);
        when(resSet.next()).thenReturn(false);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);

        bottomMapper.updateProduct(bottom);
        verify(statement, times(0)).executeQuery();
    }

    @Test
    public void test_updateProduct_with_existing_product() throws SQLException, ProductException {
        Bottom bottom = new Bottom(5,"Chocolate");
        bottom.setId(1);
        when(resSet.next()).thenReturn(true).thenReturn(true);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        when(statement.executeUpdate()).thenReturn(1);

        bottomMapper.updateProduct(bottom);
        verify(statement, times(1)).executeUpdate();
    }
}