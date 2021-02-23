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

	<h1>서버에서 html파일 전송받기</h1>
	<button id="btn">회원정보가져오기</button> <br>
	
	이름 조회하기<input type="text" id="name"><button id="searchBtn">이름으로 조회</button>
	
	<div id="result"></div>
	
	
	<script>	
	$("#searchBtn").click((e)=>{
		$.ajax({
			url:"<%=request.getContextPath()%>/jquery/nameSearch",
			data:{"name":$("#name").val()},//서버에 전달할 데이터 자바스크립트 객체로 보냄{키:값,키:값,....}
			dataType:"html", //생략시 auto설정됨
			success:function(data){
				$("#result").html(data);
			}
		});
	});	
	
	
	$("#btn").click(e=>{
			$.ajax({
				url:"<%=request.getContextPath()%>/jquery/memberSearch",
				type:"get",
				dataType:"html",
				success:data=>{
					console.log(data);
					$("#result").html(data);
				},
				error:(request,status,error)=>{
					console.log(request);
					console.log(status);
					console.log(error);
				}
			});			
		});
				
	</script>
</body>
</html>