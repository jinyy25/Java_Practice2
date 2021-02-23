package com.servletdata.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UesDataServlet
 */
@WebServlet("/useData.do")
public class UseDataServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UseDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub

   HttpSession session=request.getSession();
   ServletContext context=request.getServletContext();
   
   String requestData=(String)request.getAttribute("request");
   String sessionData=(String)session.getAttribute("session");
   String contextData=(String)context.getAttribute("context");
   
   response.setContentType("text/html;charset=utf-8");
   PrintWriter out=response.getWriter();
   String html="<html>";
   html+="<body>";
   html+="<h1>공유객체 데이터확인</h1>";
   html+="<ul>";
   html+="<li>request : " + requestData+"</li>";
   html+="<li>session : " + sessionData+"</li>";
   html+="<li>context : " + contextData+"</li>";
   html+="</ul>";
   html+="<button onclick='location.href=\""+request.getContextPath()+"/logout.do\"'>로그아웃</button>";
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
