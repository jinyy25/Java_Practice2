package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
	   
	  //회원탈퇴시
      String id=request.getParameter("userId");
      int result=new MemberService().deleteMember(id);
      String msg="";
      String loc="";
      
      //회원탈퇴시 메시지
      //세션이 있을 때만 객체를 가져옴
      if(result>0) {
         msg="탈퇴완료";
         loc="/";
         HttpSession session=request.getSession(false);
         if(session!=null) {
            session.invalidate();
         }         
      }else {
         msg="탈퇴실패";
         loc="/memberView";               
      }
      request.setAttribute("msg", msg);
      request.setAttribute("loc", loc);
      
      //회원탈퇴 페이지로 전환
      request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}
