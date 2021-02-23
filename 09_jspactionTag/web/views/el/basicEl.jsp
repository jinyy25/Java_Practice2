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


	<h3>기본적으로 EL을 사용해보자</h3>
	<p>내장객체에 들어가 있는 값을 가져올때 사용함 -> setAttribute로 저장되어 있는 값을 의미 </p>
	<%@ page import="java.util.ArrayList, com.jspaction.model.vo.Person" %>
	<% 
		String name="유병승";	
		request.setAttribute("name", name);
		
		ArrayList<String> list=new ArrayList();
		list.add("유병승");
		list.add("홍성빈");
		list.add("윤병현");
		list.add("강수정");
		request.setAttribute("list", list);	
		
		//객체 넣어보자
		request.setAttribute("p",new Person("김세민",22,"여"));
		
		//객체배열
		ArrayList<Person> persons=new ArrayList();
		persons.add(new Person("조현",29,"남"));
		persons.add(new Person("김정민",27,"남"));
		persons.add(new Person("이요셉",26,"남"));
		persons.add(new Person("인하준",25,"남"));
		
		request.setAttribute("ps", persons);
	%>

	<!-- setAttribute의 키값 ("")-->
	<p><%=name %> </p>	
	<p>${name} </p>
	
	<!-- request.getAttribute("list") -->
	<p>${list} </p>
	
	<!-- list값에서 1번 인덱스 -->
	<p>${list.get(1)} </p>
	<p>${p} ${p.name} </p> <!-- p.getName() -->
	<p>${p.age} </p>
	<p>${p["gender"]} </p>
	<p>${ps} </p>
	<p> ${ps.get(0).name} </p>
	<p> ${ps.get(0)["name"]} </p>
	<p>${"호호호"} </p>
	
	<h2>EL연산 알아보기</h2>
	<%
		request.setAttribute("su1", 10);
		request.setAttribute("su2", 20);
	%>
	
	<h3>산술연산하기</h3>
	<p>+ : ${su1+su2}</p>
	<p>- : ${su1-su2}</p>
	<p>* : ${su1*su2}</p>
	<p>/ : ${su1/su2}</p>
	<p>div : ${su1 div su2}</p>
	<p>% : ${su1%su2} </p>
	<p>mod : ${su1 mod su2}</p>
	<p>${su1 + 50} </p>
	<p>${su1*20+su2/40 mod su2}</p>
	
	<h3>비교/논리연산하기</h3>
	<p> su1 &lt; su2 : ${su1 < su2} </p> <!-- less than -->
	<p> su1 &gt; su2 : ${su1 > su2} </p> <!-- greater than -->
	<p> su1 &lt; su2 : ${su1 lt su2} </p>
	<p> su1 &gt; su2 : ${su1 gt su2} </p>
	
	<p>su1==su2 : ${su1==su2}</p>
	<p>su1==su2 : ${su1 eq su2}</p>
	<p>su1!=su2 : ${su1!=su2}</p>	
	<p>su1!=su2 : ${su1 ne su2}</p>
	<%
		ArrayList list1=new ArrayList();
		request.setAttribute("list1", list1);
	%>
	<p> list1 널이니? ${list1 == null} </p> 
	<p> list가 널이니? ${list == null} </p> 
	
	<p> list1 크기는 0이지? ${list1.size() == 0} </p>
	<p> list1이 값이 있지? ${list1.size()>0 }</p>
	
	<p> list1 값이 없니? ${empty list1} </p>
	<p> list가 값이 없니? ${empty list} </p>
	
	<p> list1 값이 비어있지 않지? ${not empty list1}</p>
	<p> list가 값이 비어있지 않지? ${not empty list}</p>
	
	<h3>논리연산 사용하기</h3>
	<p>${not empty list && su1>5} </p> 	
	<p>${not empty list and su1>5} </p> 	
	<p>${empty list || su2>10} </p>
	<p>${empty list or su2<10} </p>
	
	<h3>삼항연산자</h3>
	<p>${su1>5? "5보다 크다":"5보다 작다"} </p> 
	<input type="radio" value="10" ${su1>5?"checked":""}> su1이 5보다 크니? 크면 체크
	<input type="radio" value="3" ${su1>5?"":"checked"}> su1이 5보다 크니? 크지 않으면 체크
	
	<h3>서버에서 저장한 데이터 가져오기</h3>
	<a href="${pageContext.request.contextPath}/dataEl.do">서버에서 데이터 저장하기</a>
	
	
	
	
	
	
	
	<h3>파라미터로 전송하는 값 el로 출력하기(resultEl.jsp로 보냄)</h3>
	<form action="${pageContext.request.contextPath}/dataEl.do">
		<input type="text" name="pname" placeholder="제품명"/>
		<input type="number" name="pcount" placeholder="수량"/>
		<input type="text" name="option" placeholder="옵션1"/>
		<input type="text" name="option" placeholder="옵션2"/>
		<input type="submit" value="전송"/>
	</form>	
	
	
	
	
	<h3>El은 내장객체에 저장된 값을 자동으로 순회해서 찾아옴</h3>
	<!-- page(작은범위 부터 값을 보냄) -->
	<p>page &lt; request &lt; session &lt; application (범위) </p>
	<a href="${pageContext.request.contextPath}/dataEl.do">객체탐색테스트</a>
	
	<h3>pageContext/cookie/header 내장객체를 이용해서 데이터 받기 (직접접근으로 가져옴)</h3>
	<p>${pageContext.request}  </p> <!-- ==request.getContextPath() -->	
	<a href="${pageContext.request.contextPath}/dataEl.do">기타내장객체이용</a>
	
	
		
</body>
</html>