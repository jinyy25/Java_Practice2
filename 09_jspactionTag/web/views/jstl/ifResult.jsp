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



	<h1>결과출력하기</h1>
	<%-- <%if((Integer.parseInt(request.getParameter("su"))
			+Integer.parseInt(request.getParameter("su2")) >100)) {%>
	<p>더한 숫자가 크다</p>	
	<%} %>	
	
	<!-- 위의 로직을 el과 c태그로  간편화 --> 
	
	<c:if test="${Integer.parseInt(param.su)+Integer.parseInt(param.su2)>100 }">
		<p><c:out value="${param.su}"/>와 <c:out value="${param.su2}"/>더한값이 숫자가 더 크다 </p>
	</c:if> --%>
	
	
	
	
	<h3>선택한 색 확인하기</h3>
	 <c:set var="color" value="${param.color}"/>
	 <c:if test="${color eq 'red'}">
	 	<p style="color: <c:out value="${color}"/>">빨강색</p>
	 </c:if>
	 
	 <c:if test="${color eq 'purple'}">
	 	<p style="color: <c:out value="${color}"/>">보라색</p>
	 </c:if>
	  
	 <c:if test="${color eq 'aqua'}">
	 	<p style="color: <c:out value="${color}"/>">아쿠아</p>
	 </c:if>
	 
	 <c:if test="${color eq 'lime'}">
	 	<p style="color: <c:out value="${color}"/>">라임</p>
	 </c:if>
	 
	 	
</body>
</html>