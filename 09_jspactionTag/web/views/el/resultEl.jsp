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


	<h3>서블릿에서 보낸값 받기</h3>
	<p>쓰던방법 : <%=request.getAttribute("name") %> </p>
	<p>el이용 : ${name} </p>
	<p>${age} </p>
	
	<h3>객체출력하기</h3>
	<table>
		<tr>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
		</tr>
		<tr>
			<td>${p.name}</td>
			<td>${p.age}</td>
			<td>${p.gender}</td>
		</tr>		
	</table>
		<ul>
			<li>${list[0].name}${list[0].age} </li>
			<li>${list[1].name}${list[1].age} </li>
			<li>${list[2].name}${list[2].age} </li>
			<li>${list[3].name}${list[3].age} </li> 
		</ul>
	
	<p> ${today} </p>
	
	
	
	
	<h2>클라이언트가 보낸 파라미터값 받기 (basicEl.jsp에서 보냄)</h2>
	<p><%=request.getParameter("pname")%></p>
	<p>${param.pname }</p>
	<p>${param.pcount }</p>
	<%-- <p>${param.option }</p> --%>
	<p>${paramValues.option[0] }</p>
	<p>${paramValues.option[1] }</p>
	
	
	<!-- 내장객체  request scope /session scope/ application scope -->
	<h3>객체자동탐색</h3>
	<p>${name} </p>
	<p>${requestScope.sessionVal} </p>
	<p>${sessionScope.sessionVal} </p>
	<p>${applicationScope.contextVal} </p>
	<p>값 : ${test} (없음)</p>
	
	<h3>쿠키값받기</h3>
	<%-- <%
		Cookie[] cookies=request.getCookies();
		for
	%> --%>
	<p>${cookie.userId.value}</p>
	<h3>Header 값 가져오기</h3>
	<p>${header["Referer"] } </p>
	<p>${header["Cookie"] } </p>
	
	
	
	
	
		
</body>
</html>