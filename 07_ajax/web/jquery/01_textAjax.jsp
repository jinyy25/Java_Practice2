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
	<h1>서버에 있는 파일의 내용을 ajax로 가져오기</h1>
	<button id="btn">가져오기</button>
	<div id="result"></div>

	<script>
		$("#btn").click(e=>{
			//매개변수로 요청하는 설정정보를 대입
			$.ajax({
				url:"sample.txt",//요청주소설정
				type:"get",//요청방식
				dataType:"text",//응답한 데이터의 형식
				success:data=>{//응답왔을때 실행되는 함수(callback) 매개변수에는 서버가 응답한 데이터가 대입됨
					console.log(data);
					$("#result").html(data);
				}					
			});
		});
	</script>
	
	
</body>
</html>