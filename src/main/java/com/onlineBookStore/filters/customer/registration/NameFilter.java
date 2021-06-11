package com.onlineBookStore.filters.customer.registration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/processCustomerRegister")
public class NameFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String name = request.getParameter("name");
		String nameRegex = "^[a-zA-Z ]*$";
		int len = name.length();
		
		if(len <= 30 && len >= 3 && name.matches(nameRegex)) {
			chain.doFilter(request, response);
		} else {
			response.getWriter().append("Invalid Name\nName must be between 3-20 characters.\nName must always contain alphabets");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
