<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 선언하기 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- taglib: 라이브러리 가져옴 /  prefix: 미리 c라고선언  -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl이용하기</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h3>core태그 활용하기</h3>
	<p>
		1. c:set태그 이용하기
		- 변수를 선언하여 활용하기 위한 태그, 데이터를 저장시키는 태그				
		 var : 변수명, 저장된 값을 호출
		 value : 변수에 들어갈 값 (리터럴, EL표현식)
		 <br>
		 
		 2. c:out태그 이용하기
		 - 저장되어 있는 데이터를 화면에서 출력해주는 태그 <%-- ${값} --%>, <%-- <%= %> --%>		 
		  value : 출력할 값을 지정하는 속성 (리터럴, EL표현식)
		  default : value에 지정한 값이 없는 경우 출력할 값 지정
		  escapeXML : 태그형식으로 value를 작성했을때 태그로 해석할지 결정 		 		   
	</p>
	
	
	
	<h4>c:set으로 저장하기</h4>
	<c:set var="name" value="윤진영"/>		
	
	<!-- set으로 저장된 값은 EL로 출력이 가능 -->
	<c:set var="path" value="${pageContext.request.contextPath} "/>			
	<p>저장된 값 출력 : ${name} </p>
	<a href="${path}/text">이동</a>
	
	<h4>c:out을 이용해서 출력하기</h4>
	<c:out value="이거출력!"/> 이거출력
	<c:out value="${name}"/>
	<h3><c:out value="${name}"/></h3>
	
	
	<c:out value="<h3 style='color:red'>${name}</h3>" escapeXml="false"/>  <!--기본적으로html태그는 문자처리 -->
	<c:out value="<script>alert('호호');</script>" escapeXml="false"/>
	<%-- ${"<script>alert('해킹!');</script>" } --%> 
	<%-- <c:out value="${'<script>alert(\'해킹!\');</script>' }"/> --%>
	<%-- <c:set var="age" value="19"/> --%>
	<p>나이 : <c:out value="${age}" default="1"/> </p>
	
	
	<!-- 보완적인 측면에서 아래가 더 나음 -->
	<input type="text" value="<c:out value='${name}'/>">
	
	<p> 지우기 전 : ${name} </p>
	<c:remove var="name"/>
	<p> 지운 후 : ${name} </p>
	
	
	
	
</body>
</html>