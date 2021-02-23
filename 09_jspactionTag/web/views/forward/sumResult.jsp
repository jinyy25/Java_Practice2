<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<% 
	int su1=Integer.parseInt(request.getParameter("su1"));
	int su2=Integer.parseInt(request.getParameter("su2"));
	
	int total=0;
	for(int i=su1;i<su2;i++){
		total+=i;
	}
	request.setAttribute("total", total);
	request.setAttribute("su1", su1);
	request.setAttribute("su2", su2);	
%>

<!-- 파라미터 태그로 데이터를 전송할 수 있음 -->
<jsp:forward page="sum.jsp">
	<jsp:param name="age" value="19"/>
</jsp:forward>
