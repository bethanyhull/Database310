import java.io.*;
import java.sql.*;
import java.util.*;



public class Project {

	public static void main(String[] args) {
		try {


			Class.forName("com.mysql.jdbc.Driver").getConstructor().newInstance();
			System.out.println();
			System.out.println("JDBC driver loaded");
			Connection conn = makeConnection("51851", "FinalProject","DB2020");

	
			switch (args[0].toLowerCase()) {
			
			case "/?" :
				
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
				break;
							
			case "createitem" :
				createItem(conn, args);
                		break;
            		case "createpurchase" :
              			createPurchase(conn, args);
                		break;
            		case "createshipment" :
              			createShipment(conn, args);
              			break;

			case "getitems" : 
				getItems(conn, args);
				break;
			case "getpurchases" : 
				getPurchases(conn, args);
				break;
			case "getshipments" : 
				getShipments(conn, args);
				break;		

			case "deleteitem" : 
				deleteItem(conn, args);
				break;
			case "deletepurchase" : 
				deletePurchase(conn, args);
				break;
			case "deleteshipment" : 
				deleteShipment(conn, args);
				break;

			case "updateitem" :
				updateItem(conn, args);
                		break;
			case "itemsavailable" :
				ItemsAvailable(conn, args);
               			break;
					
			default :
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



	private static void createItem(Connection conn, String[] data) throws SQLException {
		
		String[] cleanData = removeFirstArg(data);
		String sql;
		sql = "CALL CreateItem(\"" + cleanData[0] + "\", \"" + cleanData[1] + "\", \"" + cleanData[2] + "\");";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  			
			System.out.println("Item created");


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

	private static void createPurchase(Connection conn, String[] data) throws SQLException {
		
		String[] cleanData = removeFirstArg(data);
		String sql;
		sql = "CALL CreatePurchase(\"" + cleanData[0] + "\", \"" + cleanData[1] + "\");";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  			
			System.out.println("Item created");


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

	private static void createShipment(Connection conn, String[] data) throws SQLException {

		
		String[] cleanData = removeFirstArg(data);
		String sql;
		sql = "CALL CreateShipment(\"" + cleanData[0] + "\", \"" + cleanData[1] + "\", \"" + cleanData[2] + "\");";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  			
			System.out.println("Item created");


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

	private static void getItems(Connection conn, String[] data) throws SQLException {
		String[] cleanData = removeFirstArg(data);
		String sql;
		sql = "CALL GetItems(\"" + cleanData[0] + "\");";

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  // no real code required... just a real db connection
			// Now do something with the ResultSet ....

			rs.beforeFirst();

			
			System.out.println(String.format("%1$-" + 10 + "s", "Item ID")
					+ String.format("%1$-" + 15 + "s", "Item Code") 
					+ String.format("%1$-" + 20 + "s", "Item Description")
					+ String.format("%1$-" + 5 + "s", "Item Price"));
			while (rs.next()) {
				System.out.println(String.format("  %1$-" + 10 + "s", rs.getInt(1))
					+ String.format("%1$-" + 15 + "s", rs.getString(2)) 
					+ String.format("%1$-" + 20 + "s", rs.getString(3))
					+ String.format("%1$-" + 5 + "s", rs.getString(4)));
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
	
	private static void getShipments(Connection conn, String[] data) throws SQLException {
		String[] cleanData = removeFirstArg(data);
		String sql;
		sql = "CALL GetShipments(\"" + cleanData[0] + "\");";

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  // no real code required... just a real db connection
			// Now do something with the ResultSet ....

			rs.beforeFirst();

			System.out.println(String.format("%1$-" + 5 + "s", "Shipment ID")
					+ String.format("%1$-" + 15 + "s", "Item ID") 
					+ String.format("%1$-" + 5 + "s", "Quantity")
					+ String.format("%1$-" + 10 + "s", "Shipment Date"));
			while (rs.next()) {
				System.out.println(String.format("%1$-" + 5 + "s", rs.getInt(1))
						+ "+" + String.format("%1$-" + 15 + "s", rs.getString(2)) 
						+ ":" + String.format("%1$-" + 5 + "s", rs.getString(3))
						+ ":" + String.format("%1$-" + 10 + "s", rs.getString(4)));
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
	
	private static void getPurchases(Connection conn, String[] data) throws SQLException {
		String[] cleanData = removeFirstArg(data);
		String sql;
		sql = "CALL GetPurchases(\"" + cleanData[0] + "\");";

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  // no real code required... just a real db connection
			// Now do something with the ResultSet ....

			rs.beforeFirst();
	
			System.out.println(String.format("%1$-" + 15 + "s", "Purchase ID")
					+ String.format("%1$-" + 15 + "s", "Item ID") 
					+ String.format("%1$-" + 10 + "s", "Quantity")
					+ String.format("%1$-" + 10 + "s", "Purchase Date"));
			while (rs.next()) {
				System.out.println(String.format("  %1$-" + 15 + "s", rs.getInt(1))
						+ String.format("%1$-" + 15 + "s", rs.getString(2)) 
						+ String.format("%1$-" + 10 + "s", rs.getString(3))
						+ String.format("%1$-" + 10 + "s", rs.getString(4)));
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
	
	private static void ItemsAvailable(Connection conn, String[] data) {
		String[] cleanData = removeFirstArg(data);
		String sql;
		sql = "CALL ItemsAvailable(\"" + cleanData[0] + "\");";

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  // no real code required... just a real db connection
			// Now do something with the ResultSet ....

			rs.beforeFirst();

			System.out.println(String.format("%1$-" + 10 + "s", "Item Code")
					+ String.format("%1$-" + 17 + "s", "Item Description") 
					+ String.format("%1$-" + 14 + "s", "Total Shipped")
					+ String.format("%1$-" + 16 + "s", "Total Purchased")
					+ String.format("%1$-" + 16 + "s", "Total Available"));
			while (rs.next()) {
				System.out.println(String.format("%1$-" +10 + "s", rs.getInt(1))
						+ String.format("%1$-" + 17 + "s", rs.getString(2)) 
						+ String.format("%1$-" + 14 + "s", rs.getInt(3))
						+ String.format("%1$-" + 16 + "s", rs.getInt(4))
						+ String.format("%1$-" + 16 + "s", rs.getInt(5)));
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


	private static void updateItem(Connection conn, String[] data) {
		String[] cleanData = removeFirstArg(data);
		String sql;
		sql = "CALL updateItem(\"" + cleanData[0] + "\", \"" + cleanData[1] + "\");";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  			
			System.out.println("Item #" + cleanData[0] + " updated");


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


	private static void deleteShipment(Connection conn, String[] data) {
		String[] cleanData = removeFirstArg(data);
		String sql;
		sql = "CALL deleteShipment(\"" + cleanData[0] + "\");";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  			
			System.out.println("Shipment #" + cleanData[0] + " deleted");


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


	private static void deletePurchase(Connection conn, String[] data) {
		String[] cleanData = removeFirstArg(data);
		String sql;
		sql = "CALL deletePurchase(\"" + cleanData[0] + "\");";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  			
			System.out.println("Purchase for item #" + cleanData[0] + " deleted");


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


	private static void deleteItem(Connection conn, String[] data) {
		String[] cleanData = removeFirstArg(data);
		String sql;
		sql = "CALL deleteItem(\"" + cleanData[0] + "\");";

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);  			
			System.out.println("Item #" + cleanData[0] + " deleted");


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


	// removes the first arguement of the string so only the data is left
	private static String[] removeFirstArg(String[] args) {
		String[] newArgs = new String[args.length - 1]; 

		for(int i = 0; i < newArgs.length; i++) {
			newArgs[i] = args[i+1];
		}

		return newArgs;

	}

}
