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

import logic.Bottom;
import logic.Topping;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 *
 * @author rando, Benjamin
 */

@RunWith(MockitoJUnitRunner.class)
public class ProductMapperTest {
    @InjectMocks
    private ProductMapper productMapper;
    @Mock
    private ResultSet resSet;
    @Mock
    private PreparedStatement statement;
    @Mock
    private SQLConnection connection;
    @Mock
    Connection sqlConnection;

    @Test
    public void test_getAllProducts() throws SQLException, ProductException {
        //First and 2'nd time next() is called, it returns true, the third time false
        //when(resSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        
    }

    @Test
    public void test_createProduct() throws SQLException, ProductException {
        Topping top = new Topping(5,"Chocolate");
        when(resSet.next()).thenReturn(false).thenReturn(true).thenReturn(false);
        when(connection.lastID()).thenReturn(100);
        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(statement);
        when(connection.executeQuery(statement)).thenReturn(true);
        when(connection.selectQuery(statement)).thenReturn(resSet);

        productMapper.createProduct(top);

        assertEquals(100,top.getId());
    }

    @Test(expected = ProductException.class)
    public void test_createProduct_with_connection_error() throws SQLException, ProductException {
        Topping top = new Topping(5,"Chocolate");
        when(resSet.next()).thenReturn(false).thenReturn(true).thenReturn(false);
        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(statement);
        when(connection.executeQuery(statement)).thenReturn(false);
        when(connection.selectQuery(statement)).thenReturn(resSet);
        productMapper.createProduct(top);
    }

    @Test(expected = ProductException.class)
    public void test_createProduct_with_existing_topping() throws SQLException, ProductException {
        Bottom top = new Bottom(5,"Chocolate");
        when(resSet.next()).thenReturn(true).thenReturn(false);
        when(connection.getConnection()).thenReturn(sqlConnection);
        when(sqlConnection.prepareStatement(any(String.class))).thenReturn(statement);
        when(connection.selectQuery(statement)).thenReturn(resSet);

        productMapper.createProduct(top);
    }

}
