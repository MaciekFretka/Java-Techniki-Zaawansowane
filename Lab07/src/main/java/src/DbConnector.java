package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static String URL ="jdbc:postgresql://localhost:5433/jdbctest";
    private static String USER  = "postgres";
    private static String PASSWORD = "kawazmlekiem";

    public static Connection connect() throws SQLException {
        Connection conection = DriverManager.getConnection(URL,USER,PASSWORD);
        System.out.println("Connected with database");
        return conection;
    }
}
