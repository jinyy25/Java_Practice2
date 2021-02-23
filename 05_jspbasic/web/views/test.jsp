<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%><%-- import="java.util.List,java.util.ArrayList" --%>

<%@ page import="java.util.List,java.util.ArrayList"%>
<%@ page import="java.util.GregorianCalendar,java.util.Date" %>

<%
	List<String> list=new ArrayList();
	GregorianCalendar today=new GregorianCalendar();
	/* 에러페이지 등록하기 */
	//list.get(0);//NullpointerException
%>

<%@ include file="/views/common/header.jsp/" %>
	<section>
		<h1>오늘의 날짜</h1>
		<p>오늘날 : <%=new Date(today.getTimeInMillis())%> </p>
	</section>
<%@ include file="/views/common/footer.jsp/" %>