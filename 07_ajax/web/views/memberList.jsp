<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.kh.member.model.vo.Member" %>

<!--
     ajax의 요청에 응답하는 jsp페이지는 페이지에 표시될 태그부분만 작성하면됨.
	   기본구조를 빼고 태그 일부만 작성  
-->

<% 
		List<Member> list=(List)request.getAttribute("list");
%>

<table>
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>성별</th>
		<th>나이</th>
		<th>이메일</th>
		<th>전화번호</th>
	</tr>
	<%for(Member m : list) {%>
		<tr>
			<td><%=m.getUserId()%></td>
			<td><%=m.getUserName()%> </td>
			<td><%=m.getGender()%> </td>
			<td><%=m.getAge() %> </td>
			<td><%=m.getEmail() %> </td>
			<td><%=m.getPhone() %> </td>			
		</tr>
	<%}%>
</table>
