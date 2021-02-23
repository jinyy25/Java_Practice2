<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map,java.util.HashMap"  %>

<%
	Map<String,String> map=new HashMap();
	map.put("1","금요일");
	map.put("2","만나요");
	map.put("3","아이유");
	
	//로그인값 가져오기
	String id=(String)session.getAttribute("id");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	nav>ul{
		display: flex;
		list-style-type: none;
	}
	p{
		background-color: gold;
	}
</style>
</head>
<body>
	<header>
		<h1>Welcome to JY page</h1>
		<%if(id==null){ %>
			<form action="<%=request.getContextPath()%>/controller/login.jsp" method="post">
				<input type="text" name="userId" placeholder="enter ID"> <br>
				<input type="password" name="password" placeholder="enter PW">
				<input type="submit" value="로그인">
			</form>
		<%} else{%>
			<h4><%=session.getAttribute("id") %>님 접속을 환영합니다.</h4>
		<%} %>
		<nav>
			<ul>
				<li><a href="">Main</a></li>
				<li><a href="">Notice</a></li>
				<li><a href="">Board</a></li>
				<li><a href="">Gallery</a></li>
			</ul>
		</nav>
	</header>	


	
	
	
	
