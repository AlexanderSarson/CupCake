package persistence;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private static DataSource instance;
    private BasicDataSource basicDataSource;

    private DataSource() throws IOException {
        String fileUser = "", filePassword = "", fileURL ="";
        FileInputStream fileInput = new FileInputStream("db.properties");
        Properties properties = new Properties();
        properties.load(fileInput);
        fileURL = properties.getProperty("url");
        fileURL += "?" + "serverTimezone=UTC";
        fileUser = properties.getProperty("user");
        filePassword = properties.getProperty("password");


        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername(fileUser);
        basicDataSource.setPassword(filePassword);
        basicDataSource.setUrl(fileURL);

        basicDataSource.setMinIdle(20);
        basicDataSource.setMaxIdle(50);
        basicDataSource.setMaxOpenPreparedStatements(200);
    }

    public static DataSource getInstance() throws IOException {
        if(instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    public static int lastID(Connection connection, PreparedStatement statement) throws SQLException {
        statement = connection.prepareStatement("select last_insert_id() as id");
        ResultSet rs = statement.executeQuery();
        rs.next();
        return rs.getInt("id");
    }
}
