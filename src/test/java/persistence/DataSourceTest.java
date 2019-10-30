package persistence;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 *
 * @author rando
 */
public class DataSourceTest {

    private ArrayList<String> DBsetUp = scanFromFile("CupCake_Setup.sql");
    private DataSource dataSource = new DataSource();
    public DataSourceTest() throws IOException { }

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
        try (Statement stmt = dataSource.getConnection().createStatement()){
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

    /**
     * Test of selectQuery method, of class SQLConnection.
     */
    @Test
    public void testSelectQuery() {
        try (PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT * FROM Users WHERE user_id = 1")){
            //Act
            ResultSet rs = ps.executeQuery();
            rs.next();
            String result = rs.getString("user_name");
            //Assert
            assertEquals("userNameTest", result);
        } catch (SQLException ex) {
        }
    }

    @Test
    public void testSelectQuery_NonExistingSelect() throws SQLException {
        //Arrange
        try(PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT * FROM Users WHERE user_id = 5")) {
            //Act
            ResultSet rs = ps.executeQuery();
            //Assert
            assertFalse(rs.next());
        }
    }

    @Test(expected = SQLException.class)
    public void testSelectQuery_InvalidID() throws SQLException {
        //Arrange
        try(PreparedStatement ps = dataSource.getConnection().prepareStatement("SELECT * FROM Users WHERE user_id = ASDF")) {
            //Act
            ResultSet rs = ps.executeQuery();
            //Assert
            rs.next();
        }
    }

    @Test
    public void testExecuteQuery() throws SQLException {
        //Arrange
        try(PreparedStatement ps = dataSource.getConnection().prepareStatement("INSERT INTO Users(user_name, user_role) VALUES (?, ?)")) {
            //Act
            ps.setString(1, "testName");
            ps.setString(2, "testRole");
            boolean rowsChanged = ps.executeUpdate() >= 1;
            //Assert
            assertTrue(rowsChanged);
        }
    }

    @Test(expected = SQLException.class)
    public void testExecuteQuery_InvalidData() throws SQLException {
        //Arrange
        try(PreparedStatement ps = dataSource.getConnection().prepareStatement("INSERT INTO USERS(user_name, user_role) VALUES (?, ?)")) {
            ps.setString(1, "testName");
            ps.setInt(2, 5);
            //Act
            ResultSet rs = ps.executeQuery();
            //Assert
        }
    }
}
