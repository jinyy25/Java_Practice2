package com.kh.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.admin.model.service.AdminService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/admin/memberList")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		//원하는 만큼의 데이터만 가져오기
		int cPage;
//		if(request.getParameter("cPage")!=null) {
//			cPage=Integer.parseInt(request.getParameter("cPage"));
//		}else {
//			cPage=1;
//		}
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		//int numPerPage=5;
		int numPerPage;
		try {
			numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
		}catch(NumberFormatException e) {
			numPerPage=5;//default값
		}
		
		List<Member> list=new AdminService().memberList(cPage,numPerPage);
		
		//페이지 바 구성하기
		//1. 전체페이지의 갯수 확인
		//  전체 데이터를 numPerPage로 나누면 전페 페이지수가 나옴.
		int totalData=new AdminService().memberCount();
		int totalPage=(int)Math.ceil((double)totalData/numPerPage);// X 
		
		int pageBarSize=5;
		//cPage에 따라 각페이지바의 시작번호가 나와야함.
		//cPage 1~5 : pageNo 1
		//cPage 6~10 : pageNo 6
		//cPage 11~15 : pageNo 11
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		//페이지를 요청할 수 있는 페이지html구성하기
		String pageBar="";
		
		//이전
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()
					+"/admin/memberList?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
		}
		
		//반복문을 이용해서 번호를 출력해줌
		//pageNo<=pageEnd && pageNo<=totlaPage
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()
				+"/admin/memberList?cPage="+pageNo+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		//다음출력
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()
			+"/admin/memberList?cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>";
		}
		
		
		
		
		request.setAttribute("pageBar", pageBar);	
		
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/views/admin/memberList.jsp").forward(request,response);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
