<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	
	<h1>누적결과</h1>
	<p><%=request.getAttribute("su1")%>에서 <%=request.getAttribute("su2")%>까지의
	합계는 <%=request.getAttribute("total") %>이다. </p>
	<p><%=request.getParameter("age") %> </p>
	
</body>
</html>