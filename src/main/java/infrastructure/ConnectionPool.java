package infrastructure;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * SQL connection pool interface.
 */
public interface ConnectionPool {
    Connection getConnection() throws SQLException;
}
