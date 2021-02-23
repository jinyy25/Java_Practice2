package com.kh.board.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;
public class BoardDao {

	private Properties prop=new Properties();
	
	public BoardDao() {
		try {
			String path=BoardDao.class.getResource("/sql/board/board_sql.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Board> selectBoardList(Connection conn, int cPage, int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectBoardList"));
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getNString("board_title"));
				b.setBoardWriter(rs.getNString("board_writer"));
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setBoardOriginalFilename(rs.getNString("board_original_filename"));
				b.setBoardRenamedFilename(rs.getNString("board_renamed_filename"));
				b.setBoardReadCount(rs.getInt("board_readcount"));
				list.add(b);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
	
	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectBoardCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public Board selectBoardOne(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectBoardOne"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getNString("board_title"));
				b.setBoardWriter(rs.getNString("board_writer"));
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setBoardOriginalFilename(rs.getNString("board_original_filename"));
				b.setBoardRenamedFilename(rs.getNString("board_renamed_filename"));
				b.setBoardReadCount(rs.getInt("board_readcount"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return b;
	}
	
	public int updateReadCount(Connection conn,int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateReadCount"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int insertComment(Connection conn,BoardComment bc) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("insertComment"));
			pstmt.setInt(1, bc.getBoardCommentLevel());
			pstmt.setNString(2, bc.getBoardCommentWriter());
			pstmt.setNString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
			pstmt.setString(5,bc.getBoardCommentRef()==0?null:String.valueOf(bc.getBoardCommentRef()));//문제발생
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	
	public List<BoardComment> selectBoardComment(Connection conn, int no){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardComment> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectBoardComment"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardComment bc=new BoardComment();
				bc.setBoardCommentNo(rs.getInt("board_comment_no"));
				bc.setBoardCommentLevel(rs.getInt("board_comment_level"));
				bc.setBoardCommentWriter(rs.getNString("board_comment_writer"));
				bc.setBoardCommentContent(rs.getNString("board_comment_content"));
				bc.setBoardRef(rs.getInt("board_ref"));
				bc.setBoardCommentRef(rs.getInt("board_comment_ref"));
				bc.setBoardCommentDate(rs.getDate("board_comment_date"));
				list.add(bc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
}











