package com.kh.board.contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int no=Integer.parseInt(request.getParameter("no"));
		
		//읽은 게시글 히스토리 유지하기
		//조회수를 한번만 증가시키기
		//글을 읽은적 있는지 없는지 조회 
		Cookie[] cookies=request.getCookies();
		String boardHistory="";
		boolean flag=false;//flag가 true 읽은거 false안읽은거
		if(cookies!=null) {
			for(Cookie c:cookies) {
				String name=c.getName();
				String value=c.getValue();
				if("boardHistory".equals(name)) {
					boardHistory=value;
					if(value.contains("|"+no+"|")) {
						flag=true;
						break;
					}
				}
				
			}
		}
		
		if(!flag) {
			Cookie c=new Cookie("boardHistory",boardHistory+"|"+no+"|");
			c.setMaxAge(-1);//session끊기면,브라우저종료시 삭제!
			response.addCookie(c);
		}
					
		
		
		Board b=new BoardService().selectBoardOne(no,flag);
		//게시글에 연결되어있는 댓글들도 가져오기
		List<BoardComment> list=new BoardService().selectBoardComment(no);
		
		request.setAttribute("comment", list);
		
		String path="";
		if(b==null) {
			request.setAttribute("msg", "선택한 글을 찾을 수 없습니다.");
			request.setAttribute("loc","/board/boardList");
			path="/views/common/msg.jsp";
		}else {
			request.setAttribute("board", b);
			path="/views/board/boardView.jsp";
		}
		request.getRequestDispatcher(path).forward(request,response);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
