package com.kh.member.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.member.model.vo.Member;

public class MemberDao {

	private Properties prop=new Properties();
	
	public MemberDao() {
		try {
			String path=MemberDao.class.getResource("/sql/member/member_sql.properties").getPath();
			prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//회원조회
	public Member selectMember(Connection conn, String userId, String password) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMember"));
			pstmt.setString(1,userId);
			pstmt.setNString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=new Member();
				m.setUserId(rs.getNString("userid"));
				m.setUserName(rs.getNString("username"));
				m.setGender(rs.getNString("gender"));
				m.setAge(rs.getInt("age"));
				m.setPhone(rs.getString("phone"));
				m.setEmail(rs.getNString("email"));
				m.setAddress(rs.getNString("address"));
				m.setHobby(rs.getNString("hobby"));
				m.setEnrollDate(rs.getDate("enrolldate"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;				
	}
	
	
	//회원가입
	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("insertMember"));
			pstmt.setNString(1, m.getUserId());
			pstmt.setString(2,m.getPassword());
			pstmt.setNString(3, m.getUserName());
			pstmt.setString(4,m.getGender());
			pstmt.setInt(5,m.getAge());
			pstmt.setNString(6, m.getEmail());
			pstmt.setNString(7, m.getPhone());
			pstmt.setNString(8, m.getAddress());
			pstmt.setNString(9, m.getHobby());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	//중복확인을 위한 회원Id조회
	public String selectMemberId(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String result=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMemberId"));
			pstmt.setNString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getNString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	//회원찾기를 위한 Id조회
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
		
	//회원정보 수정
	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateMember"));
			//pstmt.setNString(1, m.getPassword());
			pstmt.setNString(1, m.getUserName());
			pstmt.setNString(2, m.getGender());
			pstmt.setInt(3, m.getAge());
			pstmt.setNString(4, m.getEmail());
			pstmt.setNString(5, m.getPhone());
			pstmt.setNString(6, m.getAddress());
			pstmt.setNString(7, m.getHobby());
			pstmt.setNString(8, m.getUserId());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	//회원비밀번호 수정
	public int updatePassword(Connection conn, String userId, String pw) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updatePassword"));
			pstmt.setNString(1, pw);
			pstmt.setNString(2, userId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;//-1반환
	}
	
	
	//회원삭제
	public int deleteMember(Connection conn,String id) {
	      PreparedStatement pstmt = null;
	      int result = 0;
	      try {
	         pstmt=conn.prepareStatement(prop.getProperty("deleteMember"));
	         pstmt.setString(1,id);
	         result=pstmt.executeUpdate();
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }finally {
	         close(pstmt);
	      }
	      return result;
	   }	
}








