package com.kh.board.contoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentWriteServlet
 */
@WebServlet("/board/boardCommentWrite")
public class BoardCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int level=Integer.parseInt(request.getParameter("boardCommentLevel"));
		String writer=request.getParameter("boardCommentWriter");
		int boardRef=Integer.parseInt(request.getParameter("boardRef"));
		int boardCommentRef=Integer.parseInt(request.getParameter("boardCommentRef"));
		String boardCommentContent=request.getParameter("boardCommentContent");
		
		BoardComment bm=new BoardComment(level,writer,boardCommentContent,boardRef,boardCommentRef);
		
		int result=new BoardService().insertComment(bm);
		String msg="";
		if(result>0) {	
			msg="댓글등록 성공";
		}else {
			msg="댓글등록 실패";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc","/board/boardView?no="+boardRef);
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
