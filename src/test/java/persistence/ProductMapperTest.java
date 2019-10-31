/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import logic.Cupcake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author rando
 * @author Benjamin Paepke
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
    private DataSource dataSource;
    @Mock
    private Connection connection;

    @Test
    public void test_getAllCupcakes() throws SQLException, ProductException {
        //First and 2'nd time next() is called, it returns true, the third time false
        when(resSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resSet.getInt("topping_id")).thenReturn(1);
        when(resSet.getInt("topping_price")).thenReturn(5);
        when(resSet.getString("topping_name")).thenReturn("testTopName");
        when(resSet.getString("topping_picture")).thenReturn("testTopPic");
        when(resSet.getInt("bottom_id")).thenReturn(1);
        when(resSet.getInt("bottom_price")).thenReturn(5);
        when(resSet.getString("bottom_name")).thenReturn("testBotName");
        when(resSet.getString("bottom_picture")).thenReturn("testBotPic");

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        
        ArrayList<Cupcake> cupcakes = productMapper.getAllCupcakes();
        Cupcake cupcake = cupcakes.get(0);
        assertEquals(1, cupcake.getBottom().getId());
        assertEquals(1, cupcake.getTopping().getId());
        assertEquals(10, cupcake.getPrice());
        assertEquals("testTopName", cupcake.getTopping().getName());
        assertEquals("testBotName", cupcake.getBottom().getName());
        //assertEquals("testTopPic", cupcake.getTopping().getPicture());
        //assertEquals("testBotPic", cupcake.getBottom().getPicture());
        assertEquals(5, cupcake.getBottom().getPrice());
        assertEquals(5, cupcake.getTopping().getPrice());
    }
    
    @Test
    public void test_getCupCakesFromID() throws SQLException, ProductException {
        when(resSet.getInt("topping_id")).thenReturn(1);
        when(resSet.getInt("topping_price")).thenReturn(5);
        when(resSet.getString("topping_name")).thenReturn("testTopName");
        when(resSet.getString("topping_picture")).thenReturn("testTopPic");
        when(resSet.getInt("bottom_id")).thenReturn(1);
        when(resSet.getInt("bottom_price")).thenReturn(5);
        when(resSet.getString("bottom_name")).thenReturn("testBotName");
        when(resSet.getString("bottom_picture")).thenReturn("testBotPic");
        when(resSet.getInt("cupcake_id")).thenReturn(1);
        when(resSet.next()).thenReturn(true);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resSet);
        
        Cupcake cupcake = productMapper.getCupcakeFromID(1);
        assertEquals(1, cupcake.getId());
    }
}
