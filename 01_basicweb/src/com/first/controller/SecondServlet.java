package com.first.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//어노테이션 방식으로 서블릿 매핑처리하기
//@WebServlet어노테이션을 이용해서 이름, 매핑주소를 설정할 수 있음
@WebServlet(name="second",urlPatterns="/second.do")
public class SecondServlet extends HttpServlet {

	private static final long serialVersionUID = -1884636139965970770L;

	public SecondServlet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("어노테이션으로 적용한 서블릿");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


}
