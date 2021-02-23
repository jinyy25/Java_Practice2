package com.servletdata.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = -4616864414822403926L;

	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//클라이언트가 보낸값 받아오기
		req.setCharacterEncoding("UTF-8");
		String id=req.getParameter("id");
		String pw=req.getParameter("pw");
		System.out.println("doGet메소드가 호출됨"+id+" : "+pw);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//클라이언트가 보낸값 받기
		//request객체의 getParameter("key -> input name속성값||?name=값")매소드를 이용
//		req.setCharacterEncoding("UTF-8");
//		String id=req.getParameter("id");
//		String pw=req.getParameter("pw");
//		System.out.println("doPost메소드가 호출됨"+id+" : "+pw);
		doGet(req,resp);	
	}
	
	

}
