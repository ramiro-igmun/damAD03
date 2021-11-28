package infrastructure;

import org.vibur.dbcp.ViburDBCPDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ViburConnectionPool implements ConnectionPool{

    private static ViburDBCPDataSource ds;

    public ViburConnectionPool() {
        if (ds == null) {
            ds = new ViburDBCPDataSource();

            ds.setJdbcUrl("jdbc:hsqldb:file:db/testdb;shutdown=false");
            ds.setUsername("SA");
            ds.setPassword("");

            ds.setPoolInitialSize(10);
            ds.setPoolMaxSize(100);

            ds.setConnectionIdleLimitInSeconds(30);
            ds.setTestConnectionQuery("isValid");

            ds.setLogQueryExecutionLongerThanMs(500);
            ds.setLogStackTraceForLongQueryExecution(true);

            ds.setStatementCacheMaxSize(200);

            ds.start();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
