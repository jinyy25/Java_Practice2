package com.filter.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapperTest extends HttpServletRequestWrapper{

	public RequestWrapperTest(HttpServletRequest request) {
		super(request);
	}
	
	@Override 
	public String getParameter(String name) {
		//supper.getParameter(name) -> 원본상태;
				
		return super.getParameter(name)+" -JY- ";
	}
}
