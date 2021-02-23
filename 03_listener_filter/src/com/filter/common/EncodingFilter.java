package com.filter.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//클래스를 필터로 만들어주기 위해
//Filter인터페이스를 구현해주면 됨
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		//인코딩처리하기
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//다음필터 또는 서블릿으로 전환
		chain.doFilter(request, response);
		
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
