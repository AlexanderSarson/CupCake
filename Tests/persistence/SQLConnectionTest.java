package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rando
 */
public class SQLConnectionTest {
    
    ArrayList<String> DBsetUp = scanFromFile("CupCake_Setup.sql");
    
    
    
    public SQLConnectionTest() {
    }
    
    /**
     * Test of selectQuery method, of class SQLConnection.
     */
    @Test
    public void testSelectQuery() {
        //Arrange
        
        //Act
        
        //Assert
    }

    /**
     * Test of executeQuery method, of class SQLConnection.
     */
    @Test
    public void testExecuteQuery() {
        //Arrange
        
        //Act
        
        //Assert
    }

    /**
     * Test of getConnection method, of class SQLConnection.
     */
    @Test
    public void testGetConnection() {
        //Arrange
        
        //Act
        
        //Assert
    }
    
}
