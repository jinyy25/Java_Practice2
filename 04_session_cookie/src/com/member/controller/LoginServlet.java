package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
	  //로그인 로직 구현하기
	  //DB member table에 값이 전달받은 값이랑 일치하는 데이터가 있는지 확인
      String id = request.getParameter("userId");
      String pw = request.getParameter("pw");
      
      //DB가져오기 => JDBC
      //웹에 쓰는 라이브러리는 WEB-INF아래 lib에 넣음 (ojdbc파일)
      Member m = new MemberService().selectMember(id,pw);
      
      //로그인 처리하기
      if(m != null ) {
      //로그인 성공
         HttpSession session = request.getSession();
         session.setAttribute("loginMember", m);
      }
      //메인으로 이동
      response.sendRedirect(request.getContextPath()+ "/main.do");
      
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}
