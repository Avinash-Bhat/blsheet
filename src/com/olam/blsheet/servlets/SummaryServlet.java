package com.olam.blsheet.servlets;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import com.olam.blsheet.beans.SummaryDetails;
import com.olam.blsheet.summary.UserSummaryDAO;
import com.olam.blsheet.utils.date.DateFormatter;

public class SummaryServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5079102916034280984L;
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		SummaryDetails summaryDetails = new SummaryDetails();
		boolean done = false;
		UserSummaryDAO dao = new UserSummaryDAO();
		try{
			System.out.println("Selected users : "+request.getParameter("selectedUsers"));
			summaryDetails.setUserId(request.getParameter("userId"));
			summaryDetails.setEndDate(DateFormatter.getDateForDataBase(request.getParameter("daoteTo")));
			summaryDetails.setStartDate(DateFormatter.getDateForDataBase(request.getParameter("dateFrom")));
			done = dao.spendInformation(summaryDetails);
			if(done){
				done = dao.ownExpenseForTimeSpan(summaryDetails);
				if(done){
					dao.otherExpenseForTimeSpan(summaryDetails);
				}
			}
			float temp = summaryDetails.getAmountSpend()-summaryDetails.getSelfExpense();
			System.out.println("Final Amount : "+ (temp-summaryDetails.getOtherExpense()));
			summaryDetails.setCreditOrDebit(temp-summaryDetails.getOtherExpense());
			PrintWriter out = response.getWriter();
			out.println(new JSONObject(summaryDetails));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
