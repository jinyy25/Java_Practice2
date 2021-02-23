package com.kh.admin.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.admin.model.dao.AdminDao;
import com.kh.member.model.vo.Member;

public class AdminService {
   private AdminDao dao = new AdminDao();
   
//   public List<Member> memberList() {
    public List<Member> memberList(int cPage, int numPerPage){
	   Connection conn = getConnection();
      
      List<Member> list = dao.memberList(conn,cPage,numPerPage);
      close(conn);
      return list;
   }
   
   public List<Member> searchMemberList(String type, String key){
	 Connection conn=getConnection();
	 List<Member> list=dao.searchMemberList(conn,type,key);
	 close(conn);
	 return list;
   }
   
   public int memberCount() {
	   Connection conn=getConnection();
	   int count=dao.memberCount(conn);
	   close(conn);
	   return count;	   
   }
   
   
   public Member selectMember(String userId) {
	   Connection conn=getConnection();
	   Member m=dao.selectMember(conn,userId);
	   close(conn);
	   return m;
   }
   

}