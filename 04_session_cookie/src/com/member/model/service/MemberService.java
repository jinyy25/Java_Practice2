package com.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.common.JDBCTemplate;
import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;

public class MemberService {
	
	private MemberDao dao=new MemberDao();
	
	public Member selectMember(String id,String pw) {
		Connection conn=JDBCTemplate.getConnection();
		Member m=dao.selectMember(conn,id,pw);
		try {
			if(conn!=null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
}
