<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바스크립트 ajax처리하기</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

	<h2>자바스크립트를 이용한 ajax처리하기</h2>
	<button id="btn">javascript Ajax테스드 -get-</button> <br>
	<button id="btn2">javascript Ajax테스드 -post-</button> <br>

	<input type="text" id="name" size="5" placeholder="이름">
	<input type="text" id="age" size="5" placeholder="나이작성">
	<div id="result"></div>

	아이디 조회하기<input type="text" id="userId"><button id="btn3">조회</button>
	<div id="searchResult"></div>
	
	
	
	<h2>제이쿼리 방식으로 ajax이용하기</h2>
	<p>$.get()/$.post()함수 이용하기 </p>
	<button id="btn4">jquery -get-</button>	
	<button id="btn5">jquery -post-</button>
	
	
	<div id="jqueryResult"></div>
	<script>
		$("#btn5").on("click", (e) => {
	        $.post(
	          "<%=request.getContextPath()%>/ajaxTest",
	          { name: $("#name").val(), age: $("#age").val() }, //name 태그의 값을 가져옴
	          //응답함수 (callback함수)
	          postHandler
	        );
	      });

	      function postHandler(data, status) {		//data와 status를 배개변수로 가져옴
	        console.log(data);
	        console.log(status);
	        let str = $("#jqueryResult").html() + "<br>";
	        $("#jqueryResult").html(str + data);
	      }
	
		$("#btn4").click(e=>{
			$.get("<%=request.getContextPath()%>/ajaxTest?name="+$('#name').val()+"&age="+$('#age').val(),
					function(data,status){
				console.log(data);
				console.log(status);
				$("#jqueryResult").html(data);
				//callback함수
			});
		});
		
		//jquery를 이용한 ajax로 
		//회원아이디로 조회 구현하기
	</script>


<script>
	let btn3=document.querySelector("#btn3");
	
		btn3.addEventListener("click",e=>{
			const request=new XMLHttpRequest();
			const id=document.querySelector("#userId").value;
			request.open("get","<%=request.getContextPath()%>/memberSearch?id="+id);
		
		request.onreadystatechange=() => {
			if(request.readyState==4){
				if(request.status==200){
					document.querySelector("#searchResult").innerHTML+="<p>"+request.responseText+"</p>";			
				}
			}
		}		
		request.send();
	});
	
	

	let btn2=document.querySelector("#btn2");
	btn2.addEventListener("click",e=>{
		//ajaxpost방식으로 전송하기
		let request=new XMLHttpRequest();
		
		request.open("post","<%=request.getContextPath()%>/ajaxTest");
		request.onreadystatechange=()=>{
			if(request.readyState==4){
				if(request.status==200){
					console.log(request.responseText);			
				}else if(request.status==404){
					
				}
			}
		}
		
		//post방식으로 보낼때는 content-type값을 설정
		request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		const name=document.querySelector("#name").value;
		const age=document.querySelector("#age").value;
		request.send("name="+name+"&age="+age);
		//post방식으로 데이터를 넣을때는 send에 매개변수로 넣음
	});
	
	
	
	let btn=document.getElementById("btn");
	btn.addEventListener("click",e =>{
		//ajax로 서버와 통신하기
		//1. XMLHttpRequest객체 생성
		let request=new XMLHttpRequest();
		
		const name=document.getElementById("name").value;
		const age=document.getElementById("age").value;
		
		//2. 전송요청을 위한 값을 세팅
		// 2-1 open함수를 이용해서 전송방식과 요청할 서비스 선택
		request.open("get","<%=request.getContextPath()%>/ajaxTest?name="+name+"&age="+age);
		
		// 2-2 응답이 왔을때 그 응답을 처리할 함수를 설정
		// onreadystatechange속성을 이용, onreadystatechange속성에 실행될 함수를 대입
		request.onreadystatechange=()=>{
			//readyState속성의 값이 상태에 따라 0 ~ 4변경이 되는데
			// 변경될때마다 실행되는 함수
			// 요청처리가 완료됐을때 상태는 4번 그래서 4번을 가지고 분기처리하면 됨
			if(request.readyState==4){
				//요청처리가 완료
				//요청처리가 완료되면 서버에서 응답코드를 전달해주는데
				//이는 status속성에 대입//400 200 ...에러페이지
				if(request.status==200){
					//정상적으로 응답이 왔을때
					//alert("요청/응답처리 끝!");
					//서버에서 응답한 데이터 출력하기
					//서버에서 보낸 데이터는 request XMLHttpRequest 객체의 responseText속성에 저장
					console.log(request.responseText);
					let msg=request.responseText;
					document.getElementById("result").innerHTML+='<h1>'+msg+'</h1>';					
					
				}else if(request.status=404){
					//alert("없는 서비스입니다!");
				}				
			}
		}		
		//설정한대로 요청 전송하기
		//send()함수를 이용해서 전송을 실행
		request.send();		
	});
</script>


</body>
</html>