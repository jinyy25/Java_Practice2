<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
	Person p=(Person)request.getAttribute("person");
%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h3>useBean활용해보기</h3>
	<!-- jsp:userBean을 이용해서 vo객체 받아오기 
		id : 변수명이됨
		class : 자료형 -> 패키지명까지 다 작성을 해야함.
		액션태그는 반드시 닫기태그가 있어야함.
	-->
	<%-- <jsp:useBean id="person" class="com.jspaction.model.vo.Person" scope="request"/> --%>
	<jsp:useBean id="person" class="com.jspaction.model.vo.Person" scope="request"/>
	<p>이름 : <jsp:getProperty name="person" property="name"/></p>
	<p>나이 : <jsp:getProperty name="person" property="age"/></p>
	<p>성별 : <jsp:getProperty name="person" property="gender"/></p>
	<jsp:useBean id="p" class="com.jspaction.model.vo.Person" scope="request"/>
	<p>이름 : <jsp:getProperty name="p" property="name"/></p>
	<p>나이 : <jsp:getProperty name="p" property="age"/></p>
	<p>성별 : <jsp:getProperty name="p" property="gender"/></p>
	<h3>useBean에 값넣기</h3>
	<jsp:setProperty name="p" property="name" value="설진호"/>
	<jsp:setProperty name="p" property="age" value="29"/>
	<jsp:setProperty name="p" property="gender" value="남"/>
	<p>이름 : <jsp:getProperty name="p" property="name"/></p>
	<p>나이 : <jsp:getProperty name="p" property="age"/></p>
	<p>성별 : <jsp:getProperty name="p" property="gender"/></p>
	
	<h3>클라이언트가 보낸 파라미터값 넣기</h3>
	<jsp:useBean id="paramP" class="com.jspaction.model.vo.Person"/>
	<jsp:setProperty name="paramP" property="name" param="name"/>
	<jsp:setProperty name="paramP" property="age" param="age"/>
	<jsp:setProperty name="paramP" property="gender" param="gender"/>
	<p>이름 : <jsp:getProperty name="paramP" property="name"/></p>
	<p>나이 : <jsp:getProperty name="paramP" property="age"/></p>
	<p>성별 : <jsp:getProperty name="paramP" property="gender"/></p>
</body>
</html>











