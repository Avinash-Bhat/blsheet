package com.olam.blsheet.share;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.olam.blsheet.beans.ShareDetails;
import com.olam.blsheet.database.ConnectionPool;
import com.olam.blsheet.utils.calculations.MathematicalUtils;

public class ShareDAO {

	public boolean insertValuesToSpendDetails(ShareDetails details){
		boolean done = false;
		String query = null;
		Connection con = null;
		PreparedStatement ps = null;
		try{
			System.out.println("Going to insert");
			query = "insert into spend_details "
					+"(AMOUNT_SPEND,USER_ID,DATE,DESCRIPTION) VALUES (?,?,?,?)";
			con = ConnectionPool.getConnection();
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setFloat(1, details.getAmount());
			ps.setInt(2, details.getUserId());
			ps.setString(3, details.getDate());
			ps.setString(4, details.getDescription());
			if(ps.executeUpdate()>0){
				done = true;
				System.out.println("Successfully inserted");
			}else{
				System.out.println("Not inserted");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			if(con!= null){
				ConnectionPool.closeConnection(con);
			}
		}
		System.out.println("done : "+done);
		return done;
	}
	
	public boolean getCurrentInsertedInfo(ShareDetails shareDetails){
		boolean done = false;
		String query = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs  =null;
		try{
			System.out.println("getCurrentInsertedInfo ");
			query = "Select * FROM spend_details sp where sp.USER_ID='"+String.valueOf(shareDetails.getUserId())+"' order by sp.ID desc LIMIT 1";
			System.out.println("SQL : "+query);
			con = ConnectionPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {	
				shareDetails.setSpendId(rs.getInt(1));
				done = true;
			}
			System.out.println("Spend Id ==="+shareDetails.getSpendId());
		}catch (Exception e) {
			e.printStackTrace();
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
		return done;
	}
	
	public float getEqualSHare(ShareDetails shareDetails){
		float share = 0f;
		try{
			int noMembers = (shareDetails.getSelectedUsers().split(",")).length;
			System.out.println("Number of members : "+noMembers);
			share = MathematicalUtils.FloatDivision(shareDetails.getAmount(), ++noMembers) ;
			System.out.println("Per head share : "+share);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return share;
	}
	
	public boolean  insertOwnShareInformation(ShareDetails shareDetails) {
		boolean done = false;
		String query = null;
		Connection con = null;
		PreparedStatement ps = null;
		try{
			query = "insert into share_details "
					+"(SPND_ID,PER_HEAD,USER_ID,DATE,CREDIT) VALUES(?,?,?,?,?)";
			con = ConnectionPool.getConnection();
			ps = (PreparedStatement) con.prepareStatement(query);
			ps.setInt(1, shareDetails.getSpendId());
			ps.setString(2, String.valueOf(shareDetails.getPerHead()));
			ps.setString(3, String.valueOf(shareDetails.getUserId()));
			ps.setString(4, shareDetails.getDate());
			ps.setInt(5, 0);
			if(ps.executeUpdate()>0){
				done = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch (Exception e) {
			}
			if(con!= null){
				ConnectionPool.closeConnection(con);
			}
		}
		return done;
	}
	public boolean insertIntoShareDetails(ShareDetails shareDetails){
		boolean done = false;
		String query = null;
		Connection con = null;
		PreparedStatement ps = null;
		try{
			query = "insert into share_details "
					+"(SPND_ID,PER_HEAD,USER_ID,DATE) VALUES(?,?,?,?)";
			con = ConnectionPool.getConnection();
			ps = (PreparedStatement) con.prepareStatement(query);
			int size = shareDetails.getSelectedUsers().split(",").length;
			System.out.println("Selected users : "+size);
			for(int i = 0; i<size;i++){
				ps.setInt(1, shareDetails.getSpendId());
				ps.setString(2, String.valueOf(shareDetails.getPerHead()));
				ps.setString(3, shareDetails.getSelectedUsers().split(",")[i]);
				ps.setString(4, shareDetails.getDate());
				ps.executeUpdate();
			}
			done = true;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(ps!=null){
					ps.close();
				}
			}catch (Exception e) {
			}
			if(con!= null){
				ConnectionPool.closeConnection(con);
			}
		}
		return done;
	}
	
	
	
	
	
	
	
}
