<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSON이용하기</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>


<!-- jason은 javascript로 넘기는 annotation느낌 -->
	<h1>jason을 이용하여 자바객체 받아오기</h1>
	<p>기본적으로 json을 이용하여 데이터를 받아오려면 라이브러리 파일이 필요함 </p>
	
	<button id="btn">json으로 자바객체 받아오기</button>
	<button id="btn2">MemberList Json으로 받아와 출력</button>
	
	<div id="container"></div>
	<script>
		$("#btn2").click(e=>{
			$.ajax({
				url:"<%=request.getContextPath()%>/json/jasonTest2",
				dataType:"json",
				success:data=>{
					console.log(data);
				}
			})
			
		})
	
	
		$("#btn").click(e=>{
			$.ajax({
				url:"<%=request.getContextPath()%>/json/jsonTest",
				dataType:"json",
				success:data=>{
					 console.log(data);/* JSON.parse(responseText); */
			            let table = $("<table>");
			            let th = $("<tr>")
			              .append($("<th>").html("이름"))
			              .append($("<th>").html("나이"))
			              .append($("<th>").html("키"))
			              .append($("<th>").html("성별"))
			              .append($("<th>").html("생일"));
			            table.append(th);
			            for (let i = 0; i < data.length; i++) {
			              let tr = $("<tr>")
			                .append($("<td>").html(data[i]["name"]))//객체의 property를 가져올떄 [][]
			                .append($("<td>").html(data[i]["age"]))
			                .append($("<td>").html(data[i]["height"]))
			                .append($("<td>").html(data[i]["gender"]))
			                .append($("<td>").html(data[i]["birthday"]));
			              
			              table.append(tr);
			            }
			            //table.append(th).append(tr);
			            $("#container").html(table);						
				},
				error:function(request){
					console.log(request);
				}
			})
		})
	</script>
	
</body>
</html>