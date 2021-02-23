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

	<h2>c:forEach이용</h2>
	<p> 
		기본반복문, 배열을 이용한 반복문을 처리  <br>
		
		
		---- 배열,컬렉션을 이용한 반복문 ----		
		items : 배열, 컬렉션값을 넣는 속성
		var : items에 있는 값이 0번인덱스부터 차례로 들어가는 변수
		varStatus : 반복문의 현황정보를 알려주는 객체 count, index, first, last <br>
		
		
		---- 기본반복문 ----		
		begin : 초기값,
		end : 조건식(종료되는 값) 
		step : 증감식
	</p>
	
	
	<h3>기본반복문 이용하기</h3>
	<p>1부터 10까지 출력</p>
	<ul>
		<c:forEach var="i" begin="1" end="10" step="1">
			<li><c:out value="${i}"/> </li>
		</c:forEach> 
		<c:forEach var="i" begin="1" end="10" step="2" varStatus="vs">
			<li><c:out value="${vs.count}"/> : <c:out value="${i}"/> </li>
		</c:forEach>
	</ul>
	
	
	
	<!-- index는 i값을 따라가고 count는 차례로 올라감 -->
	<c:forEach var="i" begin="1" end="6" varStatus="vs">
		<h${i}>${vs.index}</h${i}> 
	</c:forEach>
	
	
	
	<h3>배열,컬렉션을 이용한 반복문</h3>
	<%@ page import="java.util.ArrayList,com.jspaction.model.vo.Person" %>
	<%
		String[] names={"유병승","조현","설진호","이세현","남지선"};
		request.setAttribute("names", names);
		ArrayList<Person> persons=new ArrayList();
		persons.add(new Person("김다희",29,"여"));
		persons.add(new Person("김다예",28,"여"));
		persons.add(new Person("박현우",30,"남"));
		persons.add(new Person("박철우",31,"남"));
		request.setAttribute("persons", persons);
	%>
	
	
	<!-- for(String n: names) -->
	<ul>
		<c:forEach items="${names}" var="n">
			<%-- <c:forEach> --%>		
			<li><c:out value="${n}"/></li>
		</c:forEach>
	</ul>
	
	
	
	<h3>저장된 사람 테이블로 출력하기</h3>
	<table>
		<c:forEach items="${persons }" var="p" varStatus="vs">		
			<c:if test="${vs.first}">		
				<tr>
					<th>순번 </th>
					<th>이름 </th>
					<th>나이 </th>
					<th>성별 </th>
				</tr>
			</c:if>
			<tr>
				<td> <c:out value="${vs.count}"/> </td>
				<td> <c:out value="${p.name}"/> </td>
				<td> <c:out value="${p.age}"/> </td>
				<td> <c:out value="${p.gender}"/> </td>
			</tr>	
			<c:if test="${vs.last}">
				<tr>
					<td colspan="3">총인원</td>
					<td><c:out value="${vs.count }명"/></td>
				</tr>								
				</c:if>	
		</c:forEach>		
	</table>
	
		
	<h3>다중데이터를 전달받았을때</h3>
	<p><c:out value="${paramValues.coffee}"/> </p>	
	<ul>
		<c:forEach var="c" items="${paramValues.coffee }">
			<li><c:out value="${c }"/> </li>
		</c:forEach>
	</ul>
	
	
	<h4>c:forTokens활용하기</h4>
	<p>StringTokenizer와 비슷한 기능을 가진 태그 </p>
	 <p>
	 	속성
	 	items : 분리할 문자열
	 	delims : 분리할때 기준이 되는 문자
	 	var : 분리된 값을 받는 변수 *순차적으로 변함	 	
	 </p>
	 <%
	 	String hobby="ComicBook,NetFlix,Health,Codding,Dancing,Sleeping.Soccer.Guitar";
	 	request.setAttribute("hobby", hobby);
	 %>
	<p><c:out value="${hobby}"/> </p>
	
	
	
	
	<h2>문자열 분리하여 출력하기</h2> <!-- delims:구분자   /  varStatus:value상태 -->
	<c:forTokens items="${hobby }" var="h" delims=",." varStatus="vs">
		<c:if test="${vs.first or vs.last }"> <!-- 첫번째/마지막 -->
			<p><c:out value="${h }"/> </p> <br>
		</c:if>
		<c:if test="${not(vs.first or vs.last )}">
			<p><c:out value="${h }"/> </p>
		</c:if>		
	</c:forTokens>	
	
	
	
	
</body>
</html>