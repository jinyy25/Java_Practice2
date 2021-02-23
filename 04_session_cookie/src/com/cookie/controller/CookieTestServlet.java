package com.cookie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieTestServlet
 */
@WebServlet("/cookieTest.do")
public class CookieTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//cookie생성하기
		Cookie c=new Cookie("name","윤진영");
		
		//cookie설정하기
		//유효기간설정
		c.setMaxAge(24*60*60);//1일
		//생성한 cookie전송하기
		response.addCookie(c);
		
		Cookie c2=new Cookie("email","jinyy25@naver.com");
		c.setMaxAge(24*60*60);//1일
		response.addCookie(c2);
		
		//응답페이지 작성
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String html="<html>";
		html+="<body>";
		html+="<p>쿠기카 저장되었습니다</p>";
		html+="<button onclick='location.href=\""+request.getContextPath()
		+"/checkCookie.do\"'>쿠키확인</button>";
		html+="</body>";
		html+="</html>";
		out.print(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
