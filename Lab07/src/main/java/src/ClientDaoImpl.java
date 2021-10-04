package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private static Connection conn = null;

    private void connect() throws SQLException {
        if(conn != null)
            return;
        String URL ="jdbc:postgresql://localhost:5433/jdbctest";
        String USER  = "postgres";
        String PASSWORD = "kawazmlekiem";

       conn = DriverManager.getConnection(URL,USER,PASSWORD);
    }

    private void disconnect() throws SQLException{
        if(conn == null)
            return;
        conn.close();
        conn = null;
    }
    @Override
    public void addClient(Client c) {
        String sql = "INSERT INTO public.clients (\n" +
                "\"USER_ID\", \"NAME\", \"LAST_NAME\") VALUES (\n" +
                "?::integer, ?::text, ?::text)";
        try{
            connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,c.getId());
            pstmt.setString(2,c.getName());
            pstmt.setString(3,c.getLastname());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Client> getAllClients() {
        String sql = "SELECT * FROM public.clients";
        List<Client> list = new ArrayList<>();
        try{
            connect();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                list.add(new Client(resultSet.getInt("USER_ID"),resultSet.getString("NAME"), resultSet.getString("LAST_NAME")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Client getClient(int id) {
        String sql = "SELECT \"LAST_NAME\",\"NAME\" FROM public.clients WHERE \"USER_ID\"=?";
        Client c = null;
        try{
            connect();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,id);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()){
                c = new Client(id, rs.getString("NAME"),rs.getString("LAST_NAME"));
            }
            psmt.close();
            disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void updateClient(Client c) {
        String sql = "UPDATE public.clients SET\n" +
                "\"NAME\" = '?'::text, \"LAST_NAME\" = '?'::text WHERE\n" +
                "\"USER_ID\" = ?;";
        try{
            connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,c.getName());
            pstmt.setString(2,c.getLastname());
            pstmt.setInt(3,c.getId());
            pstmt.executeUpdate();
            pstmt.close();
            disconnect();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteClient(int id) {
        String sql =  "DELETE FROM public.clients WHERE \"USER_ID\"=?";

        try{
            connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            disconnect();
        }catch (SQLException e){

        }
            //Transakcja
            //Należy usunąć również instalacje danego klienta
    }

    public static void executeQuery(PreparedStatement statement){
        try{
            statement.executeQuery();
        }catch (SQLException e){
            try{
                conn.rollback();
            }catch (SQLException el){
                el.printStackTrace();
            }
            throw new RuntimeException("Failed transaction");
        }
    }
}
