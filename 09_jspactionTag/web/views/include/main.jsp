<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- %@ include file="header.jsp" %> --%>
 
<% 
	request.setCharacterEncoding("utf-8");
%>

<jsp:include page="header.jsp">
	<jsp:param name="path" value="/views/useBeanResult.jsp"/>
	<jsp:param name="t" value="본문내용"/>
</jsp:include>
	
<section>
	<h3>메인화면 본문</h3>
	<%-- <h4><%=title %></h4> --%>	
</section>

</body>
</html>