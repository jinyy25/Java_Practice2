package com.kh.member.model.service;

import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.rollback;
import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;
public class MemberService {

	private MemberDao dao=new MemberDao();
	
	public Member selectMember(String userId, String password) {
		Connection conn=getConnection();
		Member m=dao.selectMember(conn,userId,password);
		close(conn);
		return m;		
	}
	
	public int insertMember(Member m) {
		Connection conn=getConnection();
		int result=dao.insertMember(conn,m);
		//트렌젝션처리
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public String selectMemberId(String id) {
		Connection conn=getConnection();
		String result=dao.selectMemberId(conn,id);
		close(conn);
		return result;
	}
	
	public Member selectMember(String id) {
		Connection conn=getConnection();
		Member m=dao.selectMember(conn, id);
		close(conn);
		return m;
	}
	
	
	public int updateMember(Member m) {
		Connection conn=getConnection();
		int result=dao.updateMember(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int updatePassword(String ori, String newPw, String id) {
		Connection conn=getConnection();
		//1. 원래비번이 맞는지 확인하기
		int result=-1;
		Member m=dao.selectMember(conn, id, ori);
		if(m!=null) {
			//2. 비밀번호변경
			result=dao.updatePassword(conn,id,newPw);
			if(result>0) commit(conn);
			else rollback(conn);
		}
		close(conn);
		return result;//-1을 반환하면 현재비번이 틀림, 1이상반환 업데이드성공 0반환 업데이트 실패
	}
}









