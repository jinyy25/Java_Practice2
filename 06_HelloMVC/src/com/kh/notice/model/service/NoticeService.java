package com.kh.notice.model.service;

import  static com.kh.common.JDBCTemplate.close;
import  static com.kh.common.JDBCTemplate.getConnection;
import  static com.kh.common.JDBCTemplate.commit;
import  static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;
public class NoticeService {
	
	private NoticeDao dao=new NoticeDao();
	
	public List<Notice> noticeList(int cPage, int numPerPage){
		Connection conn=getConnection();
		List<Notice> list=dao.noticeList(conn,cPage,numPerPage);
		close(conn);
		return list;
		
	}
	
	public int noticeCount() {
		Connection conn=getConnection();
		int count=dao.noticeCount(conn);
		close(conn);
		return count;
	}
	
	
	public int insertNotice(Notice n) {
		Connection conn=getConnection();
		int result=dao.insertNotice(conn, n);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public Notice selectNoticeOne(int no) {
		Connection conn=getConnection();
		Notice n=dao.selectNoticeOne(conn, no);
		close(conn);
		return n;
	}
	
	
	
}










