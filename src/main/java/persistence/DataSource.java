package persistence;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author rando
 * @author Benjamin Paepke
 */
public class DataSource {
    private static Properties properties;
    private BasicDataSource basicDataSource; //Apache comns

    public DataSource() throws IOException {
        if(properties == null) {
            FileInputStream fileInput = new FileInputStream("db.properties");
            properties = new Properties();
            properties.load(fileInput);
        }
        String fileUser = "", filePassword = "", fileURL ="";
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
        basicDataSource.setMaxIdle(100);
        basicDataSource.setMaxOpenPreparedStatements(200);
    }

    public static void setProperties(Properties prop) {
        properties = prop;
    }

    public Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    public int lastID(Connection connection, PreparedStatement statement) throws SQLException {
        statement = connection.prepareStatement("select last_insert_id() as id");
        ResultSet rs = statement.executeQuery();
        rs.next();
        return rs.getInt("id");
    }
}
