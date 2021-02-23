<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
	div#container{
		width:200px;
		height:300px;
		border:1px solid red;
		overflow:hidden;
	}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>



	<h3>c:import활용하기</h3>
	<p>다른페이지를 특정변수에 저장하고 출력하는 기능 </p>
	<p> 		
		url : 페이지 위치
		var : 페이지를 저장하는 변수명		
	</p>
	<c:import url="01_set_out.jsp" var="page1"/>
	<div id="container">
		<c:out value="${page1 }" escapeXml="false"/>
	</div>	
	<c:out value="${page1 }" escapeXml="false"/> <!-- escapaXml: Xml구문 피할거니?  -->
	<c:out value="${page1 }" escapeXml="false"/>
	<c:out value="${page1 }" escapeXml="false"/>
	
	
	
	
	<h3>c:redirect활용하기</h3>
	<p>url주소를 변경하면서 페이지 이동하기 </p>
	<%-- <c:redirect url="http://search.naver.com/search.naver?query=코로나"/> --%>
	<%-- <c:redirect url="http://search.naver.com/search.naver?query=코로나">
	 	<c:param name="query" value="코로나"/>
	</c:redirect> --%>
	
	
	
	<h3>c:catch활용하기</h3>
	<p>예외처리구문 </p>
	<%
		String[] a=null;	
		request.setAttribute("a", a);
	%>	
	<c:catch var="e">
		<p>이 값은>?? </p>
		<p><%=a[0] %> </p>		
	</c:catch>
		<p>값이 없네</p>
		<p><c:out value="${e}"/> </p>
		
		<!-- choose if forEach 잘 기억할것! -->
		
</body>
</html>