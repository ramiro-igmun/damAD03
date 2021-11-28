package infrastructure;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariConnectionPool implements ConnectionPool {

    private static HikariDataSource ds;

    public HikariConnectionPool() {
        if (ds == null) {
            Properties properties = new Properties();

            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("datasource.properties");) {
                properties.load(inputStream);
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(properties.getProperty("jdbcUrl"));
            config.setUsername(properties.getProperty("user"));
            config.setPassword(properties.getProperty("password"));
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            ds = new HikariDataSource(config);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}

