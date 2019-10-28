package persistence;

import org.junit.*;

import persistence.StorageFacadeTest;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author rando
 */
public class SQLConnectionTest {

    private ArrayList<String> DBsetUp = scanFromFile("CupCake_Setup.sql");

    private SQLConnection sqlcon = new SQLConnection();

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
/*
    @Before
    public void setUp() {
        try (Statement stmt = sqlcon.getConnection().createStatement()){
            for (String sqlStatement : DBsetUp) {
                if(!sqlStatement.isEmpty())
                    stmt.executeUpdate(sqlStatement);
            }
        } catch (SQLException e) {
        }
    }*/

    @After
    public void tearDownClass() {
        ArrayList<String> DBsetUp = scanFromFile("CupCake_Setup.sql");
        rebuildDB();
    }

    private void rebuildDB() {
        ArrayList<String> DBsetUp = scanFromFile("CupCake_Setup.sql");
        try (Connection connection = dataSource.getConnection();
             Statement stmt = connection.createStatement()) {
            for (String sqlStatement : DBsetUp) {
                if(!sqlStatement.isEmpty())
                    stmt.executeUpdate(sqlStatement);
            }
        } catch (SQLException e) {
        }
    }
    private static DataSource dataSource;
    static {
        try {
            dataSource = new DataSource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Test of selectQuery method, of class SQLConnection.
     */
    @Test
    public void testSelectQuery() {
        try {
            //Arrange
            PreparedStatement ps = sqlcon.getConnection().prepareStatement("SELECT * FROM Users WHERE user_id = 1");
            //Act
            ResultSet rs = sqlcon.selectQuery(ps);
            rs.next();
            String result = rs.getString("user_name");
            //Assert
            assertEquals("userNameTest", result);
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testSelectQuery_NonExistingSelect() throws SQLException {
        //Arrange
        PreparedStatement ps = sqlcon.getConnection().prepareStatement("SELECT * FROM Users WHERE user_id = 5");
        //Act
        ResultSet rs = sqlcon.selectQuery(ps);
        //Assert
        assertFalse(rs.next());
    }

    @Test(expected = SQLException.class)
    public void testSelectQuery_InvalidID() throws SQLException {
        //Arrange
        PreparedStatement ps = sqlcon.getConnection().prepareStatement("SELECT * FROM Users WHERE user_id = ASDF");
        //Act
        ResultSet rs = sqlcon.selectQuery(ps);
        rs.next();
        //Assert
    }

    /**
     * Test of executeQuery method, of class SQLConnection.
     */
    @Test
    public void testExecuteQuery() throws SQLException {
        //Arrange
        PreparedStatement ps = sqlcon.getConnection().prepareStatement("INSERT INTO Users(user_name, user_role) VALUES (?, ?)");
        ps.setString(1, "testName");
        ps.setString(2, "testRole");
        //Act
        boolean rowsChanged = sqlcon.executeQuery(ps);
        //Assert
        assertTrue(rowsChanged);
    }

    @Test(expected = SQLException.class)
    public void testExecuteQuery_InvalidData() throws SQLException {
        //Arrange
        PreparedStatement ps = sqlcon.getConnection().prepareStatement("INSERT INTO USERS(user_name, user_role) VALUES (?, ?)");
        ps.setString(1, "testName");
        ps.setInt(2, 5);
        //Act
        ResultSet rs = sqlcon.selectQuery(ps);
        //Assert
    }
}
