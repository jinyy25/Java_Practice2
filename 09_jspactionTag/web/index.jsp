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


	<h3>usebeantest하기</h3>
	<a href="<%=request.getContextPath() %>/useBeanTest">useBean활용하기</a>
	<form action="<%=request.getContextPath() %>/useBeanTest">
		이름 <input type="text" name="name"/><br>
		나이 <input type="text" name="age"/><br>
		성별 <input type="text" name="gender"/><br>
		<input type="submit" value="등록"/>	 
	</form>


	
	<h3>include 테스트하기</h3>
	<p>
		%@ include와 동일한 기능을 수행하는 태그 -> 다른 페이지를 불러오는것  <br>
		1. 다른 페이지(불러오는 페이지)에 있는 변수를 사용하지 못함. <br>
		2. 다른 페이지(불러오는 페이지)에 파라미터 값을 전달할 수 있음	<br>			
	</p>
	<a href="<%=request.getContextPath() %>/views/include/main.jsp">include 테스트하기</a>
	
	
	
	
	<h3>forward 테스트하기</h3>
	<p> 페이지를 전환할때 사용하는 RequestDispatcher.forward와 같은 기능 </p>
	
	<form action="<%=request.getContextPath()%>/views/forward/sumResult.jsp">
		<input type="text" name="su1">
		<input type="text" name="su2">
		<input type="submit" value="전송">
	</form>
	
	
</body>
</html>