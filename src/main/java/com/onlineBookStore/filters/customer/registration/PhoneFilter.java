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
public class PhoneFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String phone = request.getParameter("phone");
		int len = phone.length();
		boolean flag = false;
		
		if(len == 10) {
			for(int i=0; i<len; i++) {
				if(phone.charAt(i) >= '0' && phone.charAt(i) <= '9') {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
		}
		
		if(flag) {
			chain.doFilter(request, response);
		} else {
			response.getWriter().append("Invalid Phone Number");
		}
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
