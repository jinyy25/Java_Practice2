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


	<form action="03_forTest.jsp">
		<h3>당신의 커피취향</h3>
		<label><input type="checkbox" name="coffee" value="IceAmericano">IceAmericano</label> <br>
		<label><input type="checkbox" name="coffee" value="CaramelMacchiato">CaramelMacchiato</label> <br>
		<label><input type="checkbox" name="coffee" value="Einspanner">Einspanner</label> <br>
		<label><input type="checkbox" name="coffee" value="MixCoffee">MixCoffee</label> <br>
		<label><input type="checkbox" name="coffee" value="Cold-Brew">Cold-Brew</label> <br>
		<label><input type="checkbox" name="coffee" value="HotAmericano">HotAmericano</label> 		 
		<input type="submit" value="전송">
	</form>
	
</body>
</html>