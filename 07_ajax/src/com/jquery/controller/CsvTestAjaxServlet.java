package com.jquery.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.vo.User;

/**
 * Servlet implementation class CsvTestAjaxServlet
 */
@WebServlet("/jquery/csvTest")
public class CsvTestAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CsvTestAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> list=new ArrayList();
		list.add(new User("박보검","01051049205","parkBogum.jpg"));
		list.add(new User("쥴리아 로버트","01052223233","juliaRoberts.jpg"));
		list.add(new User("맷데이먼","01022522205","mattDamon.jpg"));
	
		//csv방식으로 데이터 전송중..
		String csv="";
		for(int i=0;i<list.size();i++) {
			if(i!=0) csv+="\n";
			csv+=list.get(i);
		}
		System.out.println(csv);
		
		response.setContentType("text/csv;charset=utf-8");
		response.getWriter().write(csv);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
