package com.servletdata.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MovePageServlet
 */
@WebServlet("/movePage.do")
public class MovePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//페이지전환 해주는 서블릿
		System.out.println("movePageServlet실행");
		
		//서버에서 작성한 데이터를 넣기
		// request객체에 데이터를 보관할 수 있음
		// request.setAttribute("key",object)매소드를 이용함
		// setAttribute()매소드로 넣은 값은 getAttribute()매소드로 값을 가져올 수 있다.
		String name=request.getParameter("name");
		request.setAttribute("msg",name+"님 우리 사이트를 방문해주셔서 감사합니다. :)");
		
		//DB의 데이터를 가져옴
		
		
		//TestPersonServlet을 호출하기
		//내부에서 다른 서블릿/페이지를 호출하는 방법은 2가지
		//1. request.RequestDispatcher객체를 이용하는 방법 -> 클라이언트에게 받은 데이터 같이 전송
		//	request.getRequestDispatcher()매소드 이용
		//	매개변수에 주소값 (서블릿주소값,페이지명칭(jsp))
		//2. SendRedirect매소드를 이용하는 방법 -> 클라이언트에게 받은 데이터가 같이 전송 안됨
		RequestDispatcher rd=request.getRequestDispatcher("/testPerson.do");
		rd.forward(request, response);//페이지 전환!
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
