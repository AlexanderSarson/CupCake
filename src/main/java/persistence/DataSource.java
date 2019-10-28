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

/**
 * @author rando
 * @author Benjamin Paepke
 */
public class DataSource {
    private Properties prop = null;
    private BasicDataSource basicDataSource;

    public DataSource() throws IOException {
        String fileUser = "", filePassword = "", fileURL ="";
        FileInputStream fileInput = new FileInputStream("db.properties");
        Properties properties = new Properties();
        properties.load(fileInput);
        fileURL = properties.getProperty("url");
        fileURL += "?" + "serverTimezone=UTC";
        fileUser = properties.getProperty("user");
        filePassword = properties.getProperty("password");


        basicDataSource = new BasicDataSource();
        basicDataSource.setUsername(fileUser);
        basicDataSource.setPassword(filePassword);
        basicDataSource.setUrl(fileURL);

        basicDataSource.setMinIdle(20);
        basicDataSource.setMaxIdle(1000);
        basicDataSource.setMaxOpenPreparedStatements(400);
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
