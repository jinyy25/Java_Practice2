package com.filter.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LogincheckFilter
 */
@WebFilter("/filterTest.do")
public class LogincheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogincheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//login했는지 확인 후 로그인이 안됐으면 서블릿 이용못하기 막기
		HttpSession session=((HttpServletRequest)request).getSession(); 
		if(session.getAttribute("id")==null) {
			System.out.println("로그인 안되서 넌 이용못해!");
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath());
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
