package com.olam.blsheet.servlets;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.olam.blsheet.beans.User;
import com.olam.blsheet.users.UserDAO;

public class DetailServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		UserDAO user = new UserDAO();
		User userDetails = null;
		int id = 0;
		try {
			System.out.println(request.getParameter("userId"));
			id = Integer.parseInt(request.getParameter("userId"));
			if (id > 0) {
				userDetails = user.getCurrentLoginUsersDetails(id);
				if (userDetails != null) {
					userDetails.setUserList(user.getUserDetailsList(id));
					PrintWriter out = response.getWriter();
					out.println(new JSONObject(userDetails));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}