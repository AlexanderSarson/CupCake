package persistence;

import java.sql.Connection;

/**
 *
 * @author rando
 */

public interface IConectionPool {
    public Connection getConnection();
    public boolean releaseConnection(Connection connection);
}
