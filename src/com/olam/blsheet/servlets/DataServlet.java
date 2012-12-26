package com.olam.blsheet.servlets;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.olam.blsheet.beans.ShareDetails;
import com.olam.blsheet.share.ShareDAO;
import com.olam.blsheet.utils.date.DateFormatter;

public class DataServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2346860776407512579L;

	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		ShareDetails shareDetails = new ShareDetails();
		ShareDAO dao = new ShareDAO();
		boolean done = false;
		try{
			System.out.println("Selected users : "+request.getParameter("selectedUsers"));
			shareDetails.setUserId(Integer.parseInt(request.getParameter("userId")));
			shareDetails.setName(request.getParameter("name"));
			shareDetails.setAmount(Float.parseFloat(request.getParameter("amount")));
			shareDetails.setSelectedUsers(request.getParameter("selectedUsers"));
			shareDetails.setDescription(request.getParameter("description"));
			shareDetails.setDate(DateFormatter.getDateForDataBase(request.getParameter("date")));
			done = dao.insertValuesToSpendDetails(shareDetails);
			if(done){
				done = dao.getCurrentInsertedInfo(shareDetails);
				shareDetails.setPerHead(dao.getEqualSHare(shareDetails));
				System.out.println("Spend id received ="+shareDetails.getSpendId());
				if(shareDetails.getPerHead()>0){
					done = dao.insertOwnShareInformation(shareDetails);
					if(done){
						done = dao.insertIntoShareDetails(shareDetails);
					}
				}
				
			}
			PrintWriter out = response.getWriter();
			out.println(new JSONObject(shareDetails));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
