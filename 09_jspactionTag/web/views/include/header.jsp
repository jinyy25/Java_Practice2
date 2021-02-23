<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<style>
		ul>li{
			display:inline;
			margin-left:10px;
		}
	</style>
	
<%
	String title="header의 값!";
	String path=request.getParameter("path");
	String t=request.getParameter("t");
%>

</head>
<body>
	<header>
		<h1><%=t%></h1>
		<ul>
			<li> <a href="#"> 메인화면 </a></li>
			<li> <a href="<%=request.getContextPath()%>/<%=path %>"> 공지사항 </a></li>
			<li> <a href="#"> 갤러리 </a></li>
		</ul>
	</header>