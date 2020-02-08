import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
-- command line code:
export CLASSPATH=/opt/mysql/mysql-connector-java-8.0.18.jar:.:$CLASSPATH
javac  javatest.java
javac javatest /?
java javatest test

*/
class javatest {
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

	public static void main(String[] args) {
		try {
			// The newInstance() call is a work around for some broken Java implementations
			
			
			if (args[0].equals("/?") ){
			  System.out.println ("Usage :   test ");
// TODO: update the usage to show the new GetData option
			  return;
			}
			else {
				System.out.println(args[0]);
				System.out.println("**");

				if (args.length == 2)
				{
					System.out.println(args[1]);  // show extra args
				}
			}
// TODO: student needs to replace port,db and pwd here:
			Class.forName("com.mysql.jdbc.Driver").getConstructor().newInstance();
			System.out.println();
			System.out.println("JDBC driver loaded");
    Connection conn = makeConnection("51851", "FinalProject","DB2020");

// TO DO:  add to the code here to run the appropriate function
// when the getdata request is made 
// at the command line

		 if (args[0].equals("/?") ){
			 	System.out.println("Running test");
				
			}
			else if (args[0].equals( "test") ){
			 	System.out.println("Running test");
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
}





