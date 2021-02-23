<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> </title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

	<h3>날짜를 간단하게 양식에 맞춰 출력하기</h3>
	<c:set var="now" value="<%=new java.util.Date() %>"/>
	<p>기본출력 : <c:out value="${now }"/> </p>
	<p>
		fmt 속성을 이용하면 좀더 편리하게 형식에 맞춰서 출력할 수 있음  <br>
		fmt : formatDate태그
		type속성으로 출력값을 결정 			
	</p>
	
	<p> type: time -><fmt:formatDate value="${now }" type="time"/> </p>
	<p> type: default(date) -> <fmt:formatDate value="${now }"/> </p>
	<p> type: both -> <fmt:formatDate value="${now }" type="both"/> </p> 

	<p> 각 time에 대한 스타일을 다시 설정할 수 있음 -> timeStyle속성 </p>
	<p> timeStyle속성 : short, long, medium, full </p>
	<p> short : <fmt:formatDate value="${now }" type="time" timeStyle="short"/></p>
	<p> long : <fmt:formatDate value="${now }" type="time" timeStyle="long"/></p>
	<p> medium : <fmt:formatDate value="${now }" type="time" timeStyle="medium"/></p>
	<p> full : <fmt:formatDate value="${now }" type="time" timeStyle="full"/></p>
	
	<h3>dateStyle속성 : short, long, default, full</h3>
	<p>short : <fmt:formatDate value="${now }" type="date" dateStyle="short"/> </p>
	<p>long : <fmt:formatDate value="${now }" type="date" dateStyle="long"/> </p>
	<p>default : <fmt:formatDate value="${now }" type="date" dateStyle="default"/> </p>
	
	
	<h3>패턴으로 출력하기</h3>
	<p>pattern속성에 pattern을 넣어서 출력 </p>
	<p><fmt:formatDate value="${now }" pattern="yyyy년MM월dd일"/> </p>
	<p><fmt:formatDate value="${now }" pattern="yyyy-MM-dd"/> </p>
	<p><fmt:formatDate value="${now }" pattern="yyyy.MM.dd hh시mm분ss초"/> </p>
	<p><fmt:formatDate value="${now }" pattern="yyyy.MM.dd E hh시mm분ss초"/> </p>
	<p><fmt:formatDate value="${now }" pattern="yyyy.MM.dd (E) hh:mm:ss초"/> </p>
	<p><fmt:formatDate value="${now }" pattern="hh:mm:ss"/> </p>	
	
	
	<h2>날짜/시간 두개의 스타일을 적용하기</h2>
	<p><fmt:formatDate value="${now }" type="both" dateStyle="short" timeStyle="full"/> </p>


	<h3>시간에 대한 기준설정</h3>
	<p>timezone에 대한 설정을 변경하면 기준시간이 변경됨 </p>
	<fmt:timeZone value="GMT">
		<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/>
	</fmt:timeZone>		
	<fmttimeZone value="GMT+9">
		<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/>
	</fmttimeZone>
	
	<h2>locale변경</h2>
	<fmt:setLocale value="en_US"/>
	<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/>
	<br>
	<fmt:setLocale value="ja_JP"/>
	<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/>
	



</body>
</html>