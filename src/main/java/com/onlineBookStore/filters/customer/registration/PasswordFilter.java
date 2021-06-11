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
public class PasswordFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		int len1 = password1.length();
		int len2 = password2.length();
		
		System.out.println("Password: " + password1);
		System.out.println("Password: " + password2);
		
		boolean flag = false;
		
		if(len1 >=8 && len1 <= 20 && len2 >= 8 && len2 <= 20) {
			if(password1.equals(password2)) {
				flag = true;
			}
		}
		
		if(flag) {
			chain.doFilter(request, response);
		} else {
			response.getWriter().append("Invalid Password\nPassword must be same between 8-20 characters\nBoth Passwords must match.");
		}
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
