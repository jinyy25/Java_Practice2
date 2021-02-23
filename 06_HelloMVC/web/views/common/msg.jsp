<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
	String loc=(String)request.getAttribute("loc");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지출력 페이지</title>
</head>
<body>
	<script>
		alert('<%=msg%>');//자바의 변수를 가져왔을 때는 반드시 '',"" 문자열로 표시! 안하면 변수명으로 인식해서 에러가 발생
		<%=request.getAttribute("script")!=null?request.getAttribute("script"):""%>
		location.replace('<%=request.getContextPath()%><%=loc%>');
	</script>
</body>
</html>