<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>


	<h3>조건문 활용하기</h3>
	<h4>c:if 사용하기</h4>	
	<c:set var="su" value="20"/>
	
	<c:if test="${su > 10}">
		<p>10보다 크다 </p>
	</c:if>
	
	<form action="ifResult.jsp">
		<input type="text" name="su">
		<input type="text" name="su2">
		<input type="submit" value="전송"/>
	</form>
	
	<form action="ifResult.jsp">
		<select name="color">
			<option value="red">빨강</option>
			<option value="purple">보라색</option>
			<option value="aqua">아쿠아</option>
			<option value="lime">라임</option>
		</select>		
		<input type="submit" value="전송"/>
	</form>


	
	<h3>c:choose이용하기</h3>
	<p>
		c:choose문은 자바의 switch문이랑 비슷 
		c:choose태그안에 c:when태그를 작성하여 분기처리
		c:when태그의 test속성으로 분기처리하고
		else -> c:otherwise
	</p>
	<form action="chooseTest.jsp">
		<h2>경품뽑기</h2>
		숫자를 입력하세요 <input type="text" name="su"> <br>
		<input type="submit" value="제출">
	</form>
	
	
</body>
</html>