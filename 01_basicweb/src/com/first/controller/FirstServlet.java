package com.first.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//자바객체를 서블릿으로 만들어 주기 위해 규약을 준수하면됨.
//javax.servlet.http.HttpServlet; -> httpServlet객체 상속
//서블릿 클래스를 생성한뒤 web.xml설정을 해줘야함.
//서블릿클래스는 서버에서 사용자의 요청을 받는 클래스로 
//사용자 요청(주소(url))과 1:1로 매핑되어 있어야함

public class FirstServlet extends HttpServlet{
	

	private static final long serialVersionUID = 8026014901813786466L;

	//일반 자바클래스가 아닌 서블릿이 된것임
	public FirstServlet() {
	// TODO Auto-generated constructor stub
	}
	
	//두개의 메소드를 구현하게됨
	//doGet(), doPost() 메소드를 재정의해서 로직을 구현
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("서블릿 메소드 실행");
	
		//요청에 대한 응답페이지 작성
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		out.write("<html>");
		out.write("<body>");
		out.write("<h1>내가 만든 첫 서블릿입니다 :)</h1>");
		out.write("<p>나도 이제 웹개발자~</p>");
		out.write("</body>");
		out.write("</html>");		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub	
	}
	

}