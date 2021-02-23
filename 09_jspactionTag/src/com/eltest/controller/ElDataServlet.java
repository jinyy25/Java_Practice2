package com.eltest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspaction.model.vo.Person;

/**
 * Servlet implementation class ElDataServlet
 */
@WebServlet("/dataEl.do")
public class ElDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setAttribute("name", "유병승");
		request.setAttribute("age", 19);
		request.setAttribute("today", new Date());
		
		//객체를 request에 담기
		request.setAttribute("p", new Person("윤진영",28,"남"));
		
		//리스트 넣기
		ArrayList<Person> list = new ArrayList();
		list.add(new Person("남지선",22,"여"));
		list.add(new Person("조현",32,"남"));
		list.add(new Person("유다해",21,"여"));
		list.add(new Person("김정민",33,"남"));
		
		request.setAttribute("list", list);
		
		
		
		
		
		//객체에 값넣기
		request.setAttribute("sessionVal", "request인데~!");
		request.getSession().setAttribute("sessionVal", "session값");
		getServletContext().setAttribute("contextVal", "context값");		
		
		
		//쿠키값넣기
		Cookie c=new Cookie("userId","admin");
		c.setMaxAge(60*60); 	//Cookie수명
		response.addCookie(c);
		
		
		
		request.getRequestDispatcher("/views/el/resultEl.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
