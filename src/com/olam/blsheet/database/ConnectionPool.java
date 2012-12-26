package com.olam.blsheet.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			String dbUrl = "jdbc:mysql://localhost:3306/olam?user=root&password=root";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl);
			System.out.println("Connection created!!!!!!!!!!!!!");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static boolean closeConnection(Connection con) {
		boolean close = false;
		try {
		} catch (Exception e) {
		} finally {
			try {
				if (con != null) {
					con.close();
					close = true;
				}
				System.out.println("Connection Closed !!!!!!!!!!!!");
			} catch (SQLException se) {
				close = false;
				se.printStackTrace();
			}
		}
		return close;
	}
	
	/*
	 * public static void main(String args[]){
	 * String dbtime;
	 * String dbUrl =
	 * "jdbc:mysql://10.0.0.2/TRAINING?user=comads&password=cdpdemo";
	 * String query = "Select * FROM Contacts";
	 * 
	 * try {
	 * 
	 * Class.forName("com.mysql.jdbc.Driver");
	 * Connection con = DriverManager.getConnection (dbUrl);
	 * Statement stmt = con.createStatement();
	 * ResultSet rs = stmt.executeQuery(query);
	 * 
	 * while (rs.next()) {
	 * System.out.println(rs.getString(1)+", "+rs.getString(2)+" "+rs.getString(3
	 * ));
	 * } //end while
	 * 
	 * con.close();
	 * } //end try
	 * 
	 * catch(ClassNotFoundException e) {
	 * e.printStackTrace();
	 * }
	 * 
	 * catch(SQLException e) {
	 * e.printStackTrace();
	 * }
	 * 
	 * } //end main
	 */
} // end class