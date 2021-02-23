package com.first.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/life.do")
public class LifecycleServlet extends HttpServlet{

	private static final long serialVersionUID = -3513752255571499986L;
	
	public LifecycleServlet() {
		System.out.println("LifecycleServlet 객체생성~");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	System.out.println("doGet매소드 실행");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	System.out.println("doPost매소드 실행");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service매소드 실행");
		super.service(req, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	System.out.println("destroy매소드 실행");
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init매소드 실행");
	}
	
}
