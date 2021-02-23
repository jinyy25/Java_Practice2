package com.cookie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieCheck
 */
@WebServlet("/checkCookie.do")
public class CookieCheckServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
//      cookie의 key값은 중복이 불가능하고 중복된걸 이용시 덮어쓰기
//      Cookie c1= new Cookie("name", "인형");
//      response.addCookie(c1);
      
      Cookie[] cookies = request.getCookies();
      response.setContentType("text/html;charset=utf-8");
      PrintWriter out = response.getWriter();
      
      String html = "<html>";
      html += "<body>";
      html += "<h2>쿠키값 출력</h2>";
      html +="<ul>";
      for(Cookie c : cookies) {
         html += "<li>key : " + c.getName() + " value : " + c.getValue()+"</li>";
         System.out.println(c.getName() + " : " + c.getValue());
      }
      html +="</ul>";
      html +="<button onclick='location.href=\""+request.getContextPath()+"/deleteCookie.do\"'>쿠키삭제</button>";
      html +="</body>";
      html +="</html>";
      
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