package exercises;

import infrastructure.ViburConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class Exercise4 {

    public static void main(String[] args) {
        Connection connection;
        {
            try {
                connection = new ViburConnectionPool().getConnection();
                System.out.println(connection.isValid(0));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
