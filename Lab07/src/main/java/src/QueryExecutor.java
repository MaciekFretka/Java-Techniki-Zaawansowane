package src;

import java.sql.*;
import java.util.List;

public class QueryExecutor {

    public static ResultSet executeSelect(String selectQuery) throws SQLException {
        Connection connection = DbConnector.connect();
        Statement statement = connection.createStatement();
        return statement.executeQuery(selectQuery);
    }
    public static void executeQueries(List<String> queries) {
        queries.forEach(QueryExecutor::executeQuery);
    }
    public static void executeQuery(String query) {
        Connection connection = null;
        try {
            connection = DbConnector.connect();
            PreparedStatement updateName = connection.prepareStatement(query);
            updateName.setString(1,"kluska");
            updateName.setInt(2,1);

//            Statement statement = connection.createStatement();
//            statement.execute(query);
            updateName.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void executeQueriesInOneTransaction(List<String> queries) throws SQLException {
        Connection connection = DbConnector.connect();
        connection.setAutoCommit(false);
        queries.forEach(query-> executeQuery(query,connection));
        connection.commit();

        connection.close();

    }
    public static void executeQuery(String query, Connection connection){
        try{
            Statement statement = connection.createStatement();
            statement.execute(query);
        }catch (SQLException e){
            try{
                connection.rollback();
            }catch (SQLException el){
                el.printStackTrace();
            }
            throw new RuntimeException("Failed transaction");
        }
    }
}
