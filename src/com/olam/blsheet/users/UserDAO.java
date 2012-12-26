package com.olam.blsheet.users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.olam.blsheet.beans.User;
import com.olam.blsheet.database.ConnectionPool;

public class UserDAO {

	public User getCurrentLoginUsersDetails(int userId){
		User userDetails = null;
		String query = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs  =null;
		try{
			query = "Select * FROM users urs where urs.USER_ID="+userId;
			System.out.println("SQL : "+query);
			con = ConnectionPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {	
				userDetails = new User();
				userDetails.setName(String.valueOf(rs.getString(2)) );
				userDetails.setDescription(rs.getString(3));
				userDetails.setMobileNumber(rs.getString(4));
				userDetails.setStatus(Integer.parseInt(rs.getString(5)));
				System.out.println(rs.getString(1)+" : "+rs.getString(2)+" : "+rs.getString(3)+" : "+rs.getString(4)+" : "+rs.getString(5));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionPool.closeConnection(con);
		}
		return userDetails;
	}
	
	public List<User> getUserDetailsList(int userId){
		List<User> userDetailsList = null;
		String query = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs  =null;
		User userDetails = null;
		try{
			userDetailsList =  new ArrayList<User>();
			query = "Select * FROM users urs where urs.USER_ID!="+userId;
			System.out.println("SQL : "+query);
			con = ConnectionPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {	
				userDetails = new User();
				userDetails.setName(String.valueOf(rs.getString(2)) );
				userDetails.setDescription(rs.getString(3));
				userDetails.setMobileNumber(rs.getString(4));
				userDetails.setStatus(Integer.parseInt(rs.getString(5)));
				System.out.println(rs.getString(1)+" : "+rs.getString(2)+" : "+rs.getString(3)+" : "+rs.getString(4)+" : "+rs.getString(5));
				userDetailsList.add(userDetails);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(con!=null){
				ConnectionPool.closeConnection(con);
			}
		}
		return userDetailsList;
		
	}
}
