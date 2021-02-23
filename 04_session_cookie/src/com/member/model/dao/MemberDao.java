package com.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.member.model.vo.Member;

public class MemberDao {
   
   public Member selectMember(Connection conn, String id, String pw) {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      Member m = null;
      String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, pw);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            m  = new Member();
            m.setMemberId(rs.getNString("member_id"));
            m.setMemberPwd(rs.getNString("member_pwd"));
            m.setMemberName(rs.getNString("member_name"));
         }   
      } catch(SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(rs!=null) rs.close();
            if(pstmt !=null) pstmt.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return m;
   }
}
