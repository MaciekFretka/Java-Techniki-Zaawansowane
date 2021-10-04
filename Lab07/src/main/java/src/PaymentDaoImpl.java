package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao{

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
    public void addPayment(Payment p) {
        String sql = "INSERT INTO public.\"payments\" (\n" +
                "\"DATE\", \"AMOUNT\", \"ROUTER_ID\") VALUES (\n" +
                "?::date, ?::integer, ?::integer)";
        try{
            connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1,p.getDate());
            pstmt.setInt(2,p.getAmount());
            pstmt.setInt(3,p.getROUTER_ID());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        String sql = "SELECT * FROM public.payments";
        List<Payment> list = new ArrayList<>();
        try{
            connect();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                list.add(new Payment(resultSet.getDate("DATE"),resultSet.getInt("AMOUNT"), resultSet.getInt("ROUTER_ID")));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Payment getPayment(int id) {
        String sql = "SELECT \"DATE\",\"AMOUNT\" FROM public.payments WHERE \"ROUTER_ID\"=?";
        Payment p = null;
        try{
            connect();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,id);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()){
                p = new Payment(rs.getDate("DATE"),rs.getInt("AMOUNT"),id);
            }
            psmt.close();
            disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public void updatePayment(Payment p) {
        String sql = "UPDATE public.payments SET\n" +
                "\"DATE\" = ?, \"AMOUNT\" = ?,  \"ROUTER_ID\" = ? WHERE\n" +
                "\"ROUTER_ID\" = ?";
        try{
            connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDate(1,p.getDate());
            pstmt.setInt(2,p.getAmount());
            pstmt.setInt(3,p.getROUTER_ID());
            pstmt.setInt(4,p.getROUTER_ID());
            pstmt.executeUpdate();
            pstmt.close();
            disconnect();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deletePayment(int id) {
        String sql =  "DELETE FROM public.payments WHERE \"ROUTER_ID\"=?";

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
