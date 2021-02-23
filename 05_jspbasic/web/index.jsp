<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 지시자 사용하기 -->
<%!  
	//선언문 : class 내부에 작성할 수 있는 코드를 작성
	// 클래스의 멤버변수, 멤버메소드
	public String name="윤진영";
	public int calculator(int a, int b){
		return a+b;
	}
	/* if(name.equals("윤진영")){	
	} */
	
	/* 자바코드 쓰는 영역 */
%>

<%
	//스트립 트릿 : 매소드 내부에 작성할 수 있는 자바코드 작성
	int age=29;//지역변수
	//private double weight=65.5;
	/* public void printTest(){} */
	if(age>20){}
	String[] names={"윤진영","김도영","성상은","서준원","권혁진","현세환","원성택"};
	for(String n:names){
		System.out.println(n);
	}
	calculator(age,20);
%>



<%@ include file="views/common/header.jsp"  %>
	<session>
		<h1>Welcome to jsp world</h1>
		<p>tic-toc</p>
		<p>your name is <%=name  %> ,and your age is  <%=age  %></p>
		<p>The value is? <%=calculator(20,30) %></p>
		
		<%if(age>20) {%>
			<p>You are an elder<p>
		<%} %>
		
		<ul>
		<%for(String n : names){%>
			<li><%=n  %></li>
		<%}%>
		</ul>
		<a href="views/test.jsp">에러페이지 이동</a>
		<a href="views/test11.jsp">없는 페이지 이동</a>
		<h1>header에 선언된 데이터 이용</h1>
		<table>
		<%for(String key:map.keySet()){%>
		<tr>
			<td><%=key  %> </td>
			<td><%=map.get(key)  %></td>
		</tr>
		<%}%>
		</table>
	</session>
<%@ include file="views/common/footer.jsp" %>
