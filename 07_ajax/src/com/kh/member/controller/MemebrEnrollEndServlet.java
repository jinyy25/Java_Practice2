package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemebrEnrollEndServlet
 */
@WebServlet(name="memberEnrollEnd",urlPatterns = "/memberEnrollEnd.do")
public class MemebrEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemebrEnrollEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//1.클라언트가 보낸데이터를 db에 저장하고 저장결과를 출력해주는 로직
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		String userName=request.getParameter("userName");
		String gender=request.getParameter("gender");
		int age=Integer.parseInt(request.getParameter("age"));
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		//오라클의 type에는 배열이 없다! -> varchar2->문자열
		String[] hobby=request.getParameterValues("hobby");
		String hobbys=String.join(",", hobby);
		
		Member m=new Member(userId, password,userName, gender,age,email,phone,address,hobbys,null);
		
		//DB에 저장하는 로직 구성하기 -> insert문 실행 결과 -> int
		int result=new MemberService().insertMember(m);
		
		
		//회원등록 메세지
		//restult가 0보다 크면 등록 성공 아니면 등록실패
		//결과에 따라 메세지를 출력하고 메인화면으로 이동
		String msg="";
		String loc="/";
		if(result>0) {
			msg="회원등록 성공!";
		}else {
			msg="회원등록 실패!";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request,response);
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
