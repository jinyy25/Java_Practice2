<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h2>경품추첨결과</h2>
	
	<c:choose>
	<!-- 위에 해당되면 아래는 읽지 않음 -->
		<c:when test="${Integer.parseInt(param.su) == 0}">
			<h4>자동차(BMW)에 당첨되셨습니다.</h4>
		</c:when>
		
		<c:when test="${Integer.parseInt(param.su) == 3}">
			<h4>물티슈에 당첨되셨습니다.</h4>
		</c:when>	
			
		<c:when test="${Integer.parseInt(param.su) == 1}">
			<h4>galaxy note 20 ultra에 당첨되셨습니다.</h4>
		</c:when>
		
		<c:otherwise>
			<h4>꽝 다음기회에..</h4>
		</c:otherwise>
					
	</c:choose>
</body>
</html>