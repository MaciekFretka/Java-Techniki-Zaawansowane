package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DueDaoImpl implements DueDao {
    Connection conn=null;
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
    public void addDue(Due d) {
        String sql = "INSERT INTO public.\"DUES\" (\n" +
                "\"DATE\", \"AMOUNT\", \"ROUTER_ID\") VALUES (\n" +
                "?, ?,?)";

                        try{
            connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1,d.getDate());
            pstmt.setInt(2,d.getAmount());
            pstmt.setInt(3,d.getROUTER_ID());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Due> getAllDues() {
        String sql = "SELECT * FROM public.\"DUES\"";
        List<Due> list = new ArrayList<>();
        try{
            connect();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                list.add(new Due(resultSet.getDate("DATE"),resultSet.getInt("AMOUNT"), resultSet.getInt("ROUTER_ID")));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Due getDue(int id) {
        String sql = "SELECT \"DATE\",\"AMOUNT\" FROM public.\"DUES\" WHERE \"ROUTER_ID\"=?";
        Due d = null;
        try{
            connect();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,id);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()){
                d = new Due(rs.getDate("DATE"),rs.getInt("AMOUNT"),id);
            }
            psmt.close();
            disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public void updateDue(Due d) {
        String sql = "UPDATE public.\"DUES\"  SET\n" +
                "\"DATE\" = ?, \"AMOUNT\" = ?,  \"ROUTER_ID\" = ? WHERE\n" +
                "\"ROUTER_ID\" = ?";
        try{
            connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1,d.getDate());
            pstmt.setInt(2,d.getAmount());
            pstmt.setInt(3,d.getROUTER_ID());
            pstmt.setInt(4,d.getROUTER_ID());
            pstmt.executeUpdate();
            pstmt.close();
            disconnect();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteDue(int id) {
        String sql =  "DELETE FROM public.\"DUES\" WHERE \"ROUTER_ID\"=?";

        try{
            connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            disconnect();
        }catch (SQLException e){

        }
    }
}

