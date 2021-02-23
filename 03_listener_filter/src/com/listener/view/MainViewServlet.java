package com.listener.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainViewServlet
 */
@WebServlet("/main.do")
public class MainViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자가 볼 화면을 출력해주는 서블릿
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String html="<html>";
		html+="<body>";
		//로그인을 한 사용자
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		if(id!=null) {
			//로그인 했음.
			html+="<h1>로그인한 "+id+"님 환영합니다</h1>";
			html+="<button onclick='location.href=\""
					+request.getContextPath()+"/logout.do\"'>로그아웃</button>";
		}else {
			//로그인을 하지 않은 사용자
			//로그인 안한애
			html+="<form action='"+request.getContextPath()
					+"/login.do' method='post'>";
			html+="아이디 <input type='text' name='id'><br>";
			html+="패스워드 <input type='password' name='pw'><br>";
			html+="<input type='submit' value='로그인'>";
			html+="</form>";					
		}
		
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
