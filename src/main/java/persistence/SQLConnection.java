package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author rando
 */
public class SQLConnection implements IConectionPool {

    private final List<Connection> connectionPool = new ArrayList<>();
    private final List<Connection> usedConnections = new ArrayList<>();
    private final int INITIAL_POOL_SIZE = 20;
    private Connection connection;
    private PreparedStatement statement;
    private final static String SERVERTIME = "serverTimezone=UTC";

    public SQLConnection() {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            connectionPool.add(createConnection());
        }
    }

    private Connection createConnection() {
        try (FileInputStream fileInput = new FileInputStream("C:\\Users\\rando\\Documents\\NetBeansProjects\\CupCake\\db.properties")) {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.load(fileInput);
            String fileURL = properties.getProperty("url");
            fileURL += "?" + SERVERTIME;
            String fileUSER = properties.getProperty("user");
            String filePASSWORD = properties.getProperty("password");
            connection = DriverManager.getConnection(fileURL, fileUSER, filePASSWORD);
            statement = connection.prepareStatement(fileURL);
            return connection;
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    
    
    
    
    
    
    
    public ResultSet selectQuery(PreparedStatement query) throws SQLException {
        return query.executeQuery();
    }

    public boolean executeQuery(PreparedStatement query) {
        try {
            return query.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        }
    }
}
