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
	<h1>csv방식의 데이터 받아서 처리하기</h1>
	<p>
		문자열 값을 의미. 
		문자열 값인데, 각 데이터별로 값을 구분할 수 있는 구분자를 포함하고 있는 문장열을 csv방식라고 한다.
		"유병승,19,경기도시흥시\n조현,29,서울시\n설진호,31,경기도포천시\n"
		문장열에서 이름만 출력하기, 나이만 출력하기 
	</p>
	
	<button id="btn">값가져오기</button>
	<div id="container"></div>
	
	<script>
	
	
		$("#btn").click(function(e){
			$.ajax({
				url:"<%=request.getContextPath()%>/jquery/csvTest",
				success:function(data){
					console.log(data);
					//가져온 csv를 파싱처리해서 태그를 생성, 출력하기
					let persons=data.split("\n");
					console.log(persons);
					
					let table=$("<table>");//테이블태그생성				
					let th=$("<tr>").append($("<th>").html("프로필"))
						.append($("<th>").html("이름")).append($("<th>").html("전화번호"));//헤더로우 생성
					table.append(th);
					
						for(let i=0;i<persons.length;i++){//바디로우 생성
						let person=persons[i].split(",");
						let tr=$("<tr>");
						tr.append($("<td>").append($("<img>").attr({"src":"<%=request.getContextPath()%>/images/"+person[2]})));
						tr.append($("<td>").html(person[0]));
						tr.append($("<td>").html(person[1]));
						table.append(tr);
					}
					$("#container").html(table);
				}
			})
		});
	</script>
	
	
	<style>
		img{width:100px;height:100px;}
		table{border:1px solid; border-collapse:collapse;}
		th,td{border:1px solid; padding:10px;}
	</style>
	
	
	<h3>검색자동완성기능 ajax로 구현</h3>
	아이디조회<input type="text" id="userId" list="data">
	<datalist id="data"></datalist>
	
	<script>
		$("#userId").keyup(e =>{
			$.ajax({
				url:"<%=request.getContextPath()%>/jquery/autoComplete",
				data:{"key":$(e.target).val()},
				success:data => {
					console.log(data);//문자열로 넘어옴.					
					let keys=data.split(",");
					$("#data").html("");
					for(let i=0;i<keys.length;i++){
						$("#data").append($("<option>").html(keys[i]));
					},
					error:function(request){
						console.log(request);
					}
				}				
			});
		});
	</script>
	
</body>
</html>