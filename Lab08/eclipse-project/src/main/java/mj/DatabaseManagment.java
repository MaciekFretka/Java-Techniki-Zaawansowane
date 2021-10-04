package mj;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManagment {
	public int id=70;
	public static Connection conn = null;
	  String URL ="jdbc:postgresql://localhost:5433/jdbctest";
      String USER  = "postgres";
      String PASSWORD = "kawazmlekiem";
	public int addClient(String Name, String LastName) throws SQLException {
		id++;
		 if(conn != null)
	            return -1;
	 

	      
		
        String sql = "INSERT INTO public.clients (\n" +
                "\"USER_ID\", \"NAME\", \"LAST_NAME\") VALUES (\n" +
                "?::integer, ?::text, ?::text)";
        
        	 conn = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2,Name);
            pstmt.setString(3,LastName);
            pstmt.executeUpdate();
            pstmt.close();
            if(conn == null)
                return-1;
            conn.close();
            conn = null;
      
		
		return id;
	}
	public String getAllClients() throws SQLException, IOException {
			
		
	        
	      
		String sql = "SELECT * FROM public.clients";
		conn = DriverManager.getConnection(URL,USER,PASSWORD);
		Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            

		File fout = new File("Clients.txt");
                    FileOutputStream fos = new FileOutputStream(fout);
                    OutputStreamWriter os = new OutputStreamWriter(fos);
                    BufferedWriter bw = new BufferedWriter(os);

					while(resultSet.next()){
						bw.write("Client id:"+resultSet.getInt("USER_ID")+", Name: "+resultSet.getString("NAME") + ", Last Name:" + resultSet.getString("LAST_NAME"));
               			bw.newLine();
            }
                    bw.close();


		return "Utworzono plik : Clients.txt z danymi";
	}
	
	public int addInstalation( String address, String type, int userID) throws SQLException {
		id++;
		
		 if(conn != null)
	            return -1;
	        

	      
		
	        String sql = "INSERT INTO public.installation (\n" +
	                "\"ROUTER_ID\", \"ADDRESS\", \"TYPE\", \"USER_ID\") VALUES (\n" +
	                "?::integer, ?::text, ?::text, ?::integer)";
     
     	 conn = DriverManager.getConnection(URL,USER,PASSWORD);
     	PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        pstmt.setString(2,address);
        pstmt.setString(3,type);
        pstmt.setInt(4,userID);
        pstmt.executeUpdate();
        
         conn.close();
         conn = null;
		
		return id;
	}
	
	public String getClientInstalations(int client_id) throws SQLException, IOException {
		String sql = "SELECT \"ROUTER_ID\",\"ADDRESS\",\"TYPE\",\"USER_ID\" FROM public.installation WHERE \"USER_ID\"=?";
	
		conn = DriverManager.getConnection(URL,USER,PASSWORD);
     	PreparedStatement pstmt = conn.prepareStatement(sql);
     	 pstmt.setInt(1,client_id);
     	ResultSet rs = pstmt.executeQuery();
        

		File fout = new File(client_id+"ClientInstallation.txt");
                    FileOutputStream fos = new FileOutputStream(fout);
                    OutputStreamWriter os = new OutputStreamWriter(fos);
                    BufferedWriter bw = new BufferedWriter(os);

					while(rs.next()){
						bw.write("Router id:"+rs.getInt("ROUTER_ID")+", Address "+rs.getString("ADDRESS") + ", Type: " + rs.getString("TYPE"));
               			bw.newLine();
            }
                    bw.close();
     	
		return "Utworzono plik: "+client_id+"ClientInstallation.txt";
	}
	
	public String getClientDues(int client_id) throws SQLException, IOException {
		 String sql1 = "SELECT \"ROUTER_ID\" FROM public.installation WHERE \"USER_ID\"=?";
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
	     	 pstmt1.setInt(1,client_id);
	     	ResultSet rs = pstmt1.executeQuery();
	     	List<Integer> list = new ArrayList<Integer>();
            if(rs.next()){
               
                
                list.add(rs.getInt("ROUTER_ID"));	
            }
		
       String sql = "SELECT \"DATE\",\"AMOUNT\" FROM public.\"DUES\" WHERE \"ROUTER_ID\"=?";
       File fout = new File(client_id+"ClientDues.txt");
       FileOutputStream fos = new FileOutputStream(fout);
       OutputStreamWriter os = new OutputStreamWriter(fos);
       BufferedWriter bw = new BufferedWriter(os);
		for(int routerId : list) {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,routerId);
			ResultSet rs1 = pstmt.executeQuery();
			while(rs1.next()) {bw.write("Router id: "+routerId+", DATE:"+rs1.getDate("DATE")+", Amount: "+rs1.getInt("AMOUNT"));
   			bw.newLine();
			}
		}
		bw.close();
		
     	
     	 
		return "Utworzono plik: "+client_id+"ClientDues.txt";
	}
	
}
