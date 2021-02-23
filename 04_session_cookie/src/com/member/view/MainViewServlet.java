package com.member.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.vo.Member;

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
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String html="<html>";
		html+="<body>";
		html+="<h1>JY 홈페이지에 오신것을 환영합니다</h1>";
		HttpSession session=request.getSession();
		if(session.getAttribute("loginMember")!=null) {
			Member m=(Member)session.getAttribute("loginMember");
			html+="<p>"+m.getMemberName()+"님 환영합니다 </p>";
		}else {
			html+="<form action='"+request.getContextPath()+"/login.do' method='post'>";
			html+="아이디<input type='text' name='userId'>";
			html+="비밀번호<input type='password' name='pw'>";
			html+="<input type='submit' value='로그인'>";
			html+="</form>";
		}
		html+="<ul style='display:flex;list-style-type:none'>";
		html+="<li>메인</li>";
		html+="<li>게시판</li>";
		html+="<li>갤러리</li>";
		html+="<li>자료실</li>";
		html+="</ul>";
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
