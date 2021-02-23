package com.javascript.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JavascriptAjaxTestServlet
 */
@WebServlet("/ajaxTest")
public class JavascriptAjaxTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JavascriptAjaxTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		request.setCharacterEncoding("utf-8");
		
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		System.out.println("요청 잘 전달됐어!");
		System.out.println(name+" : "+age);
		
		//응답메세지 작성
		//response contentType
		//printwriter write()
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.write("서버에서 보낸 자료 : "+name+"님 안녕하세요"+age+"살이네요");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
