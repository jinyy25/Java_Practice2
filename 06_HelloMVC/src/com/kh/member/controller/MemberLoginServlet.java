package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet(name="loginServlet",urlPatterns="/login.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 서비스를 구현해야함.
		//1. 클라이언트가 보낸 id, password를 받아옴
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		String saveId=request.getParameter("saveId");
		System.out.println(saveId);//checkbox의 value값을 지정하지 않으면 
		//check했을때 on, check하지 않았을때 null
		if(saveId!=null) {
			Cookie c=new Cookie("saveId",userId);
			c.setMaxAge(24*60*60);
			response.addCookie(c);
		}else {
			Cookie c=new Cookie("saveId","");
			c.setMaxAge(0);//쿠키삭제하기!
			response.addCookie(c);
		}
		//2. 받아온 id, password가 DB의 Member테이블이 있는지 확인
		Member m=new MemberService().selectMember(userId, password);
		
		//3. 있으면 그 정보를 유지하기 위해 저장함. request, session, context
		//객체 m값이 null이면 -> 로그인 실패
		//객체 m값이 null이 아니면 -> 로그인 성공
		if(m!=null) {
			//로그인 성공!
			//HttpSession객체에 데이터를 대입하기!
			//로그인 후에는 HttpSession객체를 이용해서 로그인 여부를 확인함.
			HttpSession session=request.getSession();
			session.setAttribute("loginMember",m);
			response.sendRedirect(request.getContextPath());			
		}else {
			//로그인 실패!
			//실패하면 메세지 띄우고 메인으로 돌아가기!
			String msg="아이디나 패스워드가 일치하지 않습니다!";
			request.setAttribute("msg", msg);
			request.setAttribute("loc","/");
			request.getRequestDispatcher("/views/common/msg.jsp")
			.forward(request, response);
			
		}
		
		//4. 페이지는 반환.
		//새로고침 시 다시 로직이 동작하지 않도록 sendRedirect를 이용
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
