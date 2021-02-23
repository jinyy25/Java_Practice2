package com.kh.admin.model.dao;

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

import com.kh.member.model.vo.Member;

public class AdminDao {

	private Properties prop=new Properties();

	public AdminDao() {
		try {
			String path=AdminDao.class.getResource("/sql/admin/admin_sql.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
//	public List<Member> memberList(Connection conn){
	public List<Member> memberList(Connection conn, int cPage, int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("memberList"));
			//cPage 1 : 1 | 5
			//cPage 2 : 6 | 10
			//cPage 3 : 11| 15
			//cPage 4 : 16| 20
			pstmt.setInt(1,(cPage-1)*numPerPage+1);
			pstmt.setInt(2,cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Member m=new Member();
	            m.setUserId(rs.getNString("userId"));
	            m.setUserName(rs.getNString("username"));
	            m.setGender(rs.getNString("gender"));
	            m.setAge(rs.getInt("age"));
	            m.setPhone(rs.getNString("phone"));
	            m.setEmail(rs.getNString("email"));
	            m.setAddress(rs.getNString("address"));
	            m.setHobby(rs.getNString("hobby"));
	            m.setEnrollDate(rs.getDate("enrolldate"));
				list.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
	
	//회원검색한 정보를 배열에 저장
	public List<Member> searchMemberList(Connection conn, String type, String key){
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   List<Member> list=new ArrayList();
//		   String sql="";		   
		   try {
//			   switch(type) {
//			   case "userId" : sql=prop.getProperty("userId");break;
//			   case "userName" : sql=prop.getProperty("userName");break;
//			   case "usergender" : sql=prop.getProperty("gender");break;
//			   }
			   System.out.println("변경전 : "+prop.getProperty("searchMemberList"));
			   String sql=prop.getProperty("searchMemberList").replaceAll("@type",type);
			   System.out.println("변경후 : "+sql);
			   pstmt=conn.prepareStatement(sql);
			   pstmt.setString(1,"%"+key+"%");
			   rs=pstmt.executeQuery();
			   
				while(rs.next()) {
					Member m=new Member();
		            m.setUserId(rs.getNString("userId"));
		            m.setUserName(rs.getNString("username"));
		            m.setGender(rs.getNString("gender"));
		            m.setAge(rs.getInt("age"));
		            m.setPhone(rs.getNString("phone"));
		            m.setEmail(rs.getNString("email"));
		            m.setAddress(rs.getNString("address"));
		            m.setHobby(rs.getNString("hobby"));
		            m.setEnrollDate(rs.getDate("enrolldate"));
					list.add(m);
				}			   
		   }catch(SQLException e) {
			   e.printStackTrace();
		   }finally {
			   close(rs);
			   close(pstmt);
		   }return list;
	   }
	
	
	//회원수를 db에서 가져옴
	public int memberCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("memberCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	
	//관리자 게시판에서 회원검색
	public Member selectMember(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMemberOne"));
			pstmt.setString(1,userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=new Member();
				m.setUserId(rs.getNString("userid"));
				m.setPassword(rs.getNString("password"));
				m.setUserName(rs.getNString("username"));
				m.setGender(rs.getNString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getNString("address"));
				m.setHobby(rs.getNString("hobby"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
}
