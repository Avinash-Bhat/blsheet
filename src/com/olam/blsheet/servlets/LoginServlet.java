package com.olam.blsheet.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.olam.blsheet.beans.User;

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4772850349731464722L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		boolean json = request.getContentType().contains("json");
		if (json) {
			response.setContentType("application/json");
		}
		try {
			System.out.println(request.getParameter("name"));
			User userDetails = new User(request.getParameter("name"),
					request.getParameter("password"));
			try {
				if (userDetails.isAuthenticated()) {
					request.getSession().setAttribute("user", userDetails);
					if (json) {
						response.getWriter().println(
								new JSONObject().put("success", true)
										.toString());
					} else {
						response.sendRedirect(request.getContextPath()
								+ "/TaskDetails.jsp");
						return;
					}
				} else {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
							"you shall not pass!!!");
				}
			} catch (IllegalArgumentException e) {
				if (json) {
					response.sendRedirect(request.getContextPath());
					response.getWriter().println(
							new JSONObject().put("success", false)
									.put("fault", e.getLocalizedMessage())
									.toString());
				} else {
					response.sendRedirect(request.getContextPath()
							+ "/?message=" + e.getLocalizedMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
