package mj;

import java.io.IOException;
import java.sql.SQLException;

public class test {

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		DatabaseManagment db = new DatabaseManagment();
		db.addInstalation("xd", "silver", 3);
		System.out.println(db.getClientInstalations(3));
		System.out.println(db.getClientDues(3));
	
		System.out.println(db.getAllClients());
	}

}
//http://127.0.0.1:58512/wse/wsexplorer/wsexplorer.jsp?org.eclipse.wst.ws.explorer=1
