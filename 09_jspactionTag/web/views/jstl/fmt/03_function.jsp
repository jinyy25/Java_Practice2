<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

	<h2>jstl함수이용하기</h2>
	<c:set var="str" value="Hello How Are You? I'm Fine"/>
	
	<p>uppercase : 모든 문자를 대문자로 출력</p>
	<p> <c:out value="${fn:toUpperCase(str) }"/> </p>
	
	<p>replace : 문자 변경 </p> 
	<p><c:out value="${fn:replace(str,'Fine','Found') }"/> </p>	

	


</body>
</html>