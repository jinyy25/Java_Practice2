package com.kh.board.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;
public class BoardService {
	private BoardDao dao=new BoardDao();
	
	public List<Board> selectBoardList(int cPage, int numPerPage){
		Connection conn=getConnection();
		List<Board> list=dao.selectBoardList(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	
	public int selectBoardCount() {
		Connection conn=getConnection();
		int count=dao.selectBoardCount(conn);
		close(conn);
		return count;
	}
	
	public Board selectBoardOne(int no,boolean flag) {
		Connection conn=getConnection();
		Board b=dao.selectBoardOne(conn,no);
		//만약에 해당글이 있으면 조회수를 1 증가시킴
		if(b!=null&&!flag) {
			//조회수를 증가
			int result=dao.updateReadCount(conn,no);
			if(result>0) {
				commit(conn);
				b.setBoardReadCount(b.getBoardReadCount()+1);
			}
			else rollback(conn);
		}
		//없으면 패스~!
		close(conn);
		return b;
	}
	
	public int insertComment(BoardComment bc) {
		Connection conn=getConnection();
		int result=dao.insertComment(conn,bc);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<BoardComment> selectBoardComment(int no){
		Connection conn=getConnection();
		List<BoardComment> list=dao.selectBoardComment(conn,no);
		close(conn);
		return list;
	}
}








