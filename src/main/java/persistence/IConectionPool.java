package persistence;

import java.sql.Connection;

/**
 * Pooling Interface
 * @author rando
 */

public interface IConectionPool {
    public Connection getConnection();
    public boolean releaseConnection(Connection connection);
}
