package persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rando
 */
public class SQLConnection {
    private Connection connection;
    private PreparedStatement statement;
    private final static String SERVERTIME = "serverTimezone=UTC";

    public SQLConnection() {
        try(FileInputStream fileInput = new FileInputStream("db.properties")) {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.load(fileInput);
            String fileURL = properties.getProperty("url");
            fileURL += "?"+SERVERTIME;
            String fileUSER = properties.getProperty("user");
            String filePASSWORD = properties.getProperty("password");
            connection = DriverManager.getConnection(fileURL, fileUSER, filePASSWORD);
            statement = connection.prepareStatement(fileURL);
        } catch (IOException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, String>> selectQuery(PreparedStatement query) {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        try {
            ResultSet rs = query.executeQuery();
            ResultSetMetaData rsdm = rs.getMetaData();
            while (rs.next()) {
                HashMap<String, String> column = new HashMap<>();
                for (int i = 1; i <= rsdm.getColumnCount(); i++) {
                    String name = rsdm.getColumnLabel(i).toLowerCase();
                    column.put(name, rs.getString(name));
                }
                result.add(column);
            }
            return result;
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
