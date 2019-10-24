package persistence;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author rando
 */
public class SQLConnectionTest {
    
    ArrayList<String> DBsetUp = scanFromFile("CupCake_Setup.sql");
    private final static String user = "myuser";
    private final static String password = "Password123";
    private final static String IP = "127.0.0.1";
    private final static String PORT = "3306";
    private final static String DATABASE = "CupCake";
    private final static String serverTime = "serverTimezone=UTC";
    private final static String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE + "?" + serverTime;
    SQLConnection sqlcon;
    
    public ArrayList<String> scanFromFile(String filename) {
        ArrayList<String> lines = new ArrayList();
        try {
            Scanner scan = new Scanner(new File("Scripts/" + filename));
            scan.useDelimiter(Pattern.compile(";"));
            while (scan.hasNext()) {
                lines.add(scan.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    
    @Before
    public void setUp() {
        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement()) {
            for (String sqlStatement : DBsetUp) {
                stmt.executeUpdate(sqlStatement);
            }
            sqlcon = new SQLConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
    @Test
    public void testSelectQuery_NonExistingSelect() {
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
    @Test
    public void testExecuteQuery_InvalidData() {
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
