<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>error page</h1>
	<p>An error has occurred</p>
	<p><%=exception  %> </p>
	<button onclick="location.replace('/05_jspbasic/')">to main view</button>
</body>
</html>