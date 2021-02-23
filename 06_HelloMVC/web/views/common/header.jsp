<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member,com.kh.common.listener.SessionCountListener" %>    
<%
	//로그인인 된 상태면 loginMember에 값이 있다.
	//로그인이 안된상태면 loginMember가 null값이 됨.
	Member loginMember=(Member)session.getAttribute("loginMember");

	Cookie[] cookies=request.getCookies();
	//아이디저장하기
	String saveId=null;
	if(cookies!=null){
		for(Cookie c : cookies){
			if(c.getName().equals("saveId")){
				saveId=c.getValue();
			}
		}
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>
<!-- css와 js불러오기 -->
<link rel="stylesheet" type="text/css" 
href="<%=request.getContextPath() %>/css/style.css">
<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
</head>
<body>
	<div id="container">
		<header>
			<h1>Hello MVC</h1>
			<div class="login-container">
				<%if(loginMember==null) {%>
					<form id="loginFrm" action="<%=request.getContextPath() %>/login.do" method="post">
						<table>
							<tr>
								<td>
									<input type="text" name="userId" id="userId" placeholder="아이디"
									value='<%=saveId!=null?saveId:"" %>'>
								</td>
								<td></td>
							</tr>
							<tr>
								<td>
									<input type="password" name="password" id="password" placeholder="비밀번호">
								</td>
								<td>
									<input type="submit" value="로그인">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="checkbox" name="saveId" id="saveId"
									<%=saveId!=null?"checked":"" %>>
									<label for="saveId">아이디저장</label>&nbsp;&nbsp;
									<input type="button" value="회원가입" 
									onclick="location.replace('<%=request.getContextPath()%>/memberEnroll.do')">
								</td>
							</tr>
						</table>
					</form>
				<%}else{ %>
					<table id="logged-in">
						<tr>
							<td colspan="2">
								<%=loginMember.getUserName() %>님, 환영합니다.<br>
								현재접속자수 : <%=SessionCountListener.getClientCount() %>
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" value="내정보보기" 
								onclick="location.replace('<%=request.getContextPath()%>/memberView?userId=<%=loginMember.getUserId()%>')">
							</td>
							<td>
								<input type="button" value="로그아웃" 
								onclick="location.replace('<%=request.getContextPath()%>/logout.do')">
							</td>
							<td>
								<input type="button" value="1:1화상채팅"
								onclick="open('${pageContext.request.contextPath}/viewChatting',
										'_blank','width=800,height=500');">
							</td>
						</tr>
					</table>
				<%} %>
			</div>
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="#">Home</a></li>
					<li id="notice"><a href="<%=request.getContextPath()%>/notice/noticeList">공지사항</a></li>
					<li id="board"><a href="<%=request.getContextPath()%>/board/boardList">게시판</a></li>
					<%if(loginMember!=null&&loginMember.getUserId().equals("admin")){ %>
						<li id="admin-member">
							<a href="<%=request.getContextPath() %>/admin/memberList">회원관리</a>
						</li>
					<%} %>
				</ul>
			</nav>
		</header>	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		