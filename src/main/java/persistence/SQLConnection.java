package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rando
 */
public class SQLConnection {
    private Connection connection;
    private String jdbcURL = "jdbc:mysql://localhost:3306/CupCake?serverTimezone=UTC";
    private String dbUser = "root";
    private String dbPassword = "ngk99zag";

    public SQLConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet selectQuery(PreparedStatement query) {
        try {
            return query.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean executeQuery(PreparedStatement query) {
        try {
            return query.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
