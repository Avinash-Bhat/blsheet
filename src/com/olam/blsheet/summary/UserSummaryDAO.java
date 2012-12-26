package com.olam.blsheet.summary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.olam.blsheet.beans.SummaryDetails;
import com.olam.blsheet.database.ConnectionPool;

public class UserSummaryDAO {

	public boolean spendInformation(SummaryDetails summaryDetails){
		boolean done = false;
		String query = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs  =null;
		float total = 0f;
		try{
			query = "SELECT * FROM spend_details S WHERE S.DATE>='"+summaryDetails.getStartDate()+"' AND "
					+" S.DATE<='"+summaryDetails.getEndDate()+"' AND S.USER_ID="+summaryDetails.getUserId()+" ORDER BY ID ASC";
			System.out.println("SQL : "+query);
			con = ConnectionPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				total = total+Float.parseFloat(rs.getString("AMOUNT_SPEND"));
			}
			System.out.println("Total Amount spend by user "+summaryDetails.getUserId()+" : "+total);
			summaryDetails.setAmountSpend(total);
			done = true;
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
	
	public boolean ownExpenseForTimeSpan(SummaryDetails summaryDetails){
		boolean done = false;
		String query = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs  =null;
		float total = 0f;
		try{
			query = "SELECT * FROM share_details S WHERE S.DATE>='"+summaryDetails.getStartDate()+"' AND "
					+" S.DATE<='"+summaryDetails.getEndDate()+"' AND S.USER_ID='"+summaryDetails.getUserId()+"'"
					+" AND S.CREDIT=0 ORDER BY ID ASC";
			con = ConnectionPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				total = total+Float.parseFloat(rs.getString("PER_HEAD"));
			}
			System.out.println("Own Expense : "+total);
			summaryDetails.setSelfExpense(total);
			done = true;
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
	
	public boolean otherExpenseForTimeSpan(SummaryDetails summaryDetails){
		boolean done = false;
		String query = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs  =null;
		float total = 0f;
		try{
			query = "SELECT * FROM share_details S WHERE S.DATE>='"+summaryDetails.getStartDate()+"' AND "
					+" S.DATE<='"+summaryDetails.getEndDate()+"' AND S.USER_ID='"+summaryDetails.getUserId()+"'"
					+" AND S.CREDIT=1 ORDER BY ID ASC";
			con = ConnectionPool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()){
				total = total+Float.parseFloat(rs.getString("PER_HEAD"));
			}
			System.out.println("Amount need to given to others : "+total);
			summaryDetails.setOtherExpense(total);
			done = true;
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
}
