package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstallationDaoImpl implements InstallationDao{

    private Connection conn = null;
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
    public void addInstallation(Installation i) {
        String sql = "INSERT INTO public.installation (\n" +
                "\"ROUTER_ID\", \"ADDRESS\", \"TYPE\", \"USER_ID\") VALUES (\n" +
                "?::integer, ?::text, ?::text, ?::integer)";
        try{
            connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,i.getROUTERID());
            pstmt.setString(2,i.getAddress());
            pstmt.setString(3,i.getType());
            pstmt.setInt(4,i.getUserID());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Installation> getAllInstallations() {
        String sql = "SELECT * FROM public.installation";
        List<Installation> list = new ArrayList<>();
        try{
            connect();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                list.add(new Installation(resultSet.getInt("USER_ID"),resultSet.getString("ADDRESS"), resultSet.getString("TYPE"), resultSet.getInt("USER_ID")));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Installation getInstallation(int id) {
        String sql = "SELECT \"ROUTER_ID\",\"ADDRESS\",\"TYPE\",\"USER_ID\" FROM public.installation WHERE \"ROUTER_ID\"=?";
        Installation i = null;
        try{
            connect();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,id);
            ResultSet rs = psmt.executeQuery();
            if(rs.next()){
                i = new Installation(id, rs.getString("ADDRESS"),rs.getString("TYPE"),rs.getInt("ROUTER_ID" ));

            }
            psmt.close();
            disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public void updateInstallation(Installation i) {
    String sql ="UPDATE public.installation SET\n" +
            "                \"ADDRESS\" = ?, \"TYPE\" = ?, \"USER_ID\" = ?  WHERE\n" +
            "                \"ROUTER_ID\" = ?";
    try{
        connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,i.getAddress());
        pstmt.setString(2,i.getType());
        pstmt.setInt(3,i.getUserID());
        pstmt.setInt(4,i.getROUTERID());
        pstmt.executeUpdate();
        pstmt.close();
        disconnect();
    }catch (SQLException e){
        System.out.println(e.getMessage());
    }
    }

    @Override
    public void deleteInstallation(int id) {
        String sql =  "DELETE FROM public.installation WHERE \"ROUTER_ID\"=?";
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
