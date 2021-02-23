<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <%
	Person p=(Person)request.getAttribute("person");
%>  --%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>


	<h3>useBean활용해보기</h3>
	<!-- 
		jsp:useBean을 이용해서  vo객체 받아오기	 
		id : 변수명
		class : 자료형 -> 패키지명까지 다 작성해야함
		action tag : 반드시 닫기 태그가 있어야함 "/"
	-->
	
	<!-- 첫번째: servlet에서 저장한 객체를 가져옴 -->
	<jsp:useBean id="person" class="com.jspaction.model.vo.Person" scope="request"/>
	<p>이름 : <jsp:getProperty name="person" property="name"/></p>
	<p>나이 : <jsp:getProperty name="person" property="age"/></p>
	<p>성별 : <jsp:getProperty name="person" property="gender"/></p> <br><br>
		
		
	<!-- name에 키값을 일치시켜주어야 한다 -->	
	<!-- name은 단독적으로 변수명을 갖는다 -->
	<jsp:useBean id="p" class="com.jspaction.model.vo.Person" scope="request"/>
	<p>이름 : <jsp:getProperty name="p" property="name"/></p>
	<p>나이 : <jsp:getProperty name="p" property="age"/></p>
	<p>성별 : <jsp:getProperty name="p" property="gender"/></p> <br><br>							
	
	
	
	
	<!-- 두번째: value에 값을 넣어 setProperty로 저장한후 -->
	<h3>useBean에 값 넣기</h3>
	<jsp:setProperty name="p" property="name" value="설진호"/>
	<jsp:setProperty name="p" property="age" value="29"/>
	<jsp:setProperty name="p" property="gender" value="남"/> 
	<!-- getProperty로 값을 가져온다 --> 
	<p>이름 : <jsp:getProperty name="p" property="name"/></p>
	<p>나이 : <jsp:getProperty name="p" property="age"/></p>
	<p>성별 : <jsp:getProperty name="p" property="gender"/></p> <br><br>
	
	
	
	<!-- 세번째 : setProperty로 저장할 변수 이름을 지정하고 
				param으로 전페이지에서input태그에 입력한 값을 가져와 저장한다--> 
	<h3>클라이언트가 보낸 파라미터값 넣기</h3>
	<jsp:useBean id="paramP" class="com.jspaction.model.vo.Person"/>	
	<jsp:setProperty name="paramP" property="name" param="name"/>
	<jsp:setProperty name="paramP" property="age" param="age"/>
	<jsp:setProperty name="paramP" property="gender" param="gender"/>	
	
	<!-- 위에서 저장된 값을 getProperty로 가져온다-->
	<p>이름 : <jsp:getProperty name="paramP" property="name"/></p>
	<p>나이 : <jsp:getProperty name="paramP" property="age"/></p>
	<p>성별 : <jsp:getProperty name="paramP" property="gender"/></p>
	
			
</body>
</html>