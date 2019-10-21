/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.Role;
import logic.Topping;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 *
 * @author rando, Benjamin
 */

public class ProductMapperTest {
    @InjectMocks
    private ProductMapper productMapper;
    @Mock
    private ResultSet resSet;
    @Mock
    private PreparedStatement statement;
    @Mock
    private SQLConnection sqlConnection;
    @Mock
    Connection connection;

    @Test
    public void test_getAllProducts() throws SQLException{
    }

    @Test
    public void test_createTopping() throws SQLException {
        Topping top = new Topping(5,"Chocolate");
        when(resSet.next()).thenReturn(false).thenReturn(true).thenReturn(false);
        when(resSet.getInt("id")).thenReturn(100);

        when(sqlConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(sqlConnection.selectQuery(statement)).thenReturn(resSet);

        productMapper.createTop(top);

        assertEquals(100,top.getId());
    }

}
