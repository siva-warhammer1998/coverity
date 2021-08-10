package coverity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class Resource_leak {
	
	  static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/?user=student";
	   static final String USER = "student";
	   static final String PASS = "student";
	   static final String QUERY = "SELECT top 10 * FROM marksheet";
	   

	   public static void check_leakage(Connection conn) {
			String QUERY1 = "SELECT * FROM marksheet";
			Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(QUERY1);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		}
	public static void main(String args[]) {
		   try{
		
			   Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			   Statement stmt = conn.createStatement();
		       ResultSet rs = stmt.executeQuery(QUERY);
		       String inputFile = "siva";
			   BufferedReader br = new BufferedReader(new FileReader(inputFile ));
			   check_leakage(conn);
			   while(rs.next()) {
				   System.out.println("name:" + rs.getString("Name"));
			   }
			   
			   //connection prep stmt result set not closed
			   conn.close();
			   rs.close();
			   stmt.close();
			   
			   
		   }
		   catch(Exception e) {
			   System.out.println("broken connection..");
		   }
	}
	
	
}
