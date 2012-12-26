package com.olam.blsheet.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.olam.blsheet.beans.User;

/**
 * servlet that authenticates the request to jsp files in folder 'www'. 
 */
public class JSPAuthFilter implements Filter {
	
	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String servletPath = request.getServletPath();
		
		if (servletPath.startsWith("/www") && servletPath.endsWith(".jsp")) {
			HttpSession session = request.getSession(false);
			if (session == null) {
				request.getSession().setAttribute("message", "Invalid Access");
				response.sendRedirect(request.getContextPath());
				return;
			}
			User user = (User) session.getAttribute("user");
			if (user == null || !user.isAuthenticated()) {
				session.setAttribute("message", "Invalid Access");
				response.sendRedirect(request.getContextPath());
				return;
			}
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	
}
