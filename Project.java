import java.io.*;
import java.sql.*;
import java.util.*;

import com.jcraft.jsch.*;

public class Project {

	public static void main(String[] args) {
		try {
			
// TODO: student needs to replace port,db and pwd here:
			Class.forName("com.mysql.jdbc.Driver").getConstructor().newInstance();
			System.out.println();
			System.out.println("JDBC driver loaded");
    Connection conn = makeConnection("51851", "FinalProject","DB2020");

// TO DO:  add to the code here to run the appropriate function
// when the getdata request is made 
// at the command line

		 if (args[0].equals("/?") ){
			 	System.out.println("Usage:");
			 	System.out.println("$java Project CreateItem <itemCode> <itemDescription> <price>");
			 	System.out.println("$java Project CreatePurchase <itemCode> <PurchaseQuantity> ");
			 	System.out.println("$java Project CreateShipment <itemCode> <ShipmentQuantity> <shipmentDate>");
			 	System.out.println("$java Project GetItems <itemCode>");
			 	System.out.println("$java Project GetShipments <itemCode>");
			 	System.out.println("$java Project GetPurchases <itemCode>");
			 	System.out.println("$java Project ItemsAvailable <itemCode> ");
			 	System.out.println("$java Project UpdateItem <itemCode> <price>");
			 	System.out.println("$java Project DeleteItem <itemCode>");
			 	System.out.println("$java Project DeleteShipment <itemCode> ");
			 	System.out.println("$java Project DeletePurchase <itemCode> ");
				
			}
			else if (args[0].equals( "CreateItem") ){
			 	System.out.println("Running createItem");
				runQuery(conn);
			}
			else {
				System.out.println("No process requested");
			}
			conn.close();
			System.out.println();
			System.out.println("Database connection closed");
			System.out.println();
		} catch (Exception ex) {
			// handle the error
			System.err.println(ex);
		}
	}
	public static void runQuery(Connection conn) {

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT 1,2,3,4");  // no real code required... just a real db connection
			// Now do something with the ResultSet ....
			
			rs.beforeFirst();
			while (rs.next()) {
				System.out.println(rs.getInt(1) 
						+ ":" + rs.getString(2) 
						+ ":" + rs.getString(3) 
						+ ":" + rs.getString(4));
			}

		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release resources in a finally{} block
			// in reverse-order of their creation if they are no-longer needed
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}
	}
	
	public static Connection makeConnection(String port, String database, String password) {
		try {
			Connection conn = null;
      
      System.out.println("try to get a connection");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:" + port+ "/" + database+
					"?verifyServerCertificate=false&useSSL=true&serverTimezone=UTC", "msandbox",
					password);
			// Do something with the Connection
			System.out.println("Database " + database +" connection succeeded!");
			System.out.println();
			return conn;
		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}
	
	
		
		private static void createItem(Connection con, String[] data) throws SQLException {
			String[] cleanData = removeFirstArg(data);
			 String sql;
			  java.sql.Statement stmt = null;
			  stmt = con.createStatement();
			  sql = "CALL CreateItem(" + cleanData[0] + ", " + cleanData[1] + ", " + cleanData[2] + ");";
			  ResultSet res = stmt.executeQuery(sql);
			  System.out.println(res);
			  
			
		}
		
		private static void createPurchase(Connection con, String[] data) throws SQLException {
			String[] cleanData = removeFirstArg(data);
			 String sql;
			  java.sql.Statement stmt = null;
			  stmt = con.createStatement();
			  sql = "CALL CreatePurchase(" + cleanData[0] + ", " + cleanData[1] + ");";
			  ResultSet res = stmt.executeQuery(sql);
			  System.out.println(res);
			  
			
		}
		
		private static void createShipment(Connection con, String[] data) throws SQLException {
			String[] cleanData = removeFirstArg(data);
			 String sql;
			  java.sql.Statement stmt = null;
			  stmt = con.createStatement();
			  sql = "CALL CreateShipment(" + cleanData[0] + ", " + cleanData[1] + ", " + cleanData[2] + ");";
			  ResultSet res = stmt.executeQuery(sql);
			  System.out.println(res);
			  
			
		}
		
		private static Statement getItems(Connection con, String[] data) throws SQLException {
			String[] cleanData = removeFirstArg(data);
			 String sql;
			  java.sql.Statement stmt = null;
			  stmt = con.createStatement();
			  
			  sql = "CALL GetItems(" + cleanData[0] + ");";
			  ResultSet res = stmt.executeQuery(sql);
			  System.out.println(res);
			  return stmt;
			
		}
		
		
		// removes the first arguement of the string so only the data is left
		private static String[] removeFirstArg(String[] args) {
			String[] newArgs = new String[args.length - 1]; 
			
			for(int i = 0; i < newArgs.length; i++) {
				newArgs[i] = args[i+1];
			}
			
			return newArgs;
			
		}

}
