<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>    
<%
	List<Member> list=(List)request.getAttribute("list"); 
	String type=request.getParameter("searchType");
	String key=request.getParameter("searchKeyword");
	int numPerPage;
	try{
		numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
	}catch(NumberFormatException e){
		numPerPage=5;
	}
%>
<%@ include file="/views/common/header.jsp"%>
 <style type="text/css">
    section#memberList-container {text-align:center;}
    
    section#memberList-container table#tbl-member {width:100%; border:1px solid gray; border-collapse:collapse;}
    section#memberList-container table#tbl-member th, table#tbl-member td {border:1px solid gray; padding:10px; }
    div#search-container {margin:0 0 10px 0; padding:3px; background-color: rgba(0, 188, 212, 0.3);}
    div#search-userId{display:inline-block;}
    div#search-userName{display:none;}
    div#search-gender{display:none;}
    div#numPerPage-container{float:right;}
    form#numPerPageFrm{display:inline;}
 </style>
    <section id="memberList-container">
        <h2>회원관리</h2>
        <div id="search-container">
			검색타입 : 
			<select id="searchType">
				<option value="userId" <%=type!=null&&type.equals("userId")?"selected":"" %>>아이디</option>
				<option value="userName" <%=type!=null&&type.equals("userName")?"selected":"" %>>회원명</option>
				<option value="gender" <%=type!=null&&type.equals("gender")?"selected":"" %>>성별</option>
			</select>
			<div id="search-userId">
				<form action="<%=request.getContextPath() %>/admin/searchMember">
					<input type="hidden" name="searchType" value="userId">
					<input type="text" name="searchKeyword" placeholder="아이디검색" size="25"
					value="<%=key!=null&&type!=null&&type.equals("userId")?key:""%>">
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="search-userName">
				<form action="<%=request.getContextPath() %>/admin/searchMember">
					<input type="hidden" name="searchType" value="userName">
					<input type="text" name="searchKeyword" placeholder="이름검색" size="25"
					value="<%=key!=null&&type!=null&&type.equals("userName")?key:""%>">
					<button type="submit">검색</button>
				</form>
			</div>
			<div id="search-gender">
				<form action="<%=request.getContextPath() %>/admin/searchMember">
					<input type="hidden" name="searchType" value="gender">
					<input type="radio" name="searchKeyword" value="M" id="gender0"
					<%=key!=null&&type!=null&&type.equals("gender")&&key.equals("M")?"checked":""%>><label for="gender0">남</label>
					<input type="radio" name="searchKeyword" value="F" id="gender1"
					<%=key!=null&&type!=null&&type.equals("gender")&&key.equals("F")?"checked":""%>><label for="gender1">여</label>
					<button type="submit">검색</button>
				</form>
			</div>        
        </div>
        <div id="numPerPage-container">페이지당 회원수 :
        	<form id="numPerPageFrm" name="numPerPageFrm" action="">
	        	<select name="numPerPage" id="numPerPage">
	        		<option value="10" <%=numPerPage==10?"selected":""%>>10</option>
	        		<option value="5" <%=numPerPage==5?"selected":"" %>>5</option>
	        		<option value="3" <%=numPerPage==3?"selected":"" %>>3</option>
	        	</select>
        	</form> 
        </div>
        <table id="tbl-member">
            <thead>
                <tr>
                    <th>아이디</th>
				    <th>이름</th>
				    <th>성별</th>
				    <th>나이</th>
				    <th>이메일</th>
				    <th>전화번호</th>
				    <th>주소</th>
				    <th>취미</th>
				    <th>가입날짜</th>
                </tr>
            </thead>
            <tbody>
            <%if(list.isEmpty()) {%>
            	<tr>
            		<td colspan="9">조회되 회원이 없습니다!</td>
            	</tr>
            <%}else{ 
            	for(Member m : list){
            %>
				<tr>
					<td><%=m.getUserId() %></td>
					<td><%=m.getUserName() %></td>
					<td><%=m.getGender() %></td>
					<td><%=m.getAge() %></td>
					<td><%=m.getEmail() %></td>
					<td><%=m.getPhone() %></td>
					<td><%=m.getAddress() %></td>
					<td><%=m.getHobby() %></td>
					<td><%=m.getEnrollDate() %></td>
				</tr>
			<%} 
			}%>
            </tbody>
        </table>
        <div id="pageBar">
        	<%=request.getAttribute("pageBar") %>
        </div>
        <script>
        	$(function(){
				let userId=$("#search-userId");
				let userName=$("#search-userName");
				let gender=$("#search-gender");
				
				$("#searchType").change(e => {
					userId.css("display","none");
					userName.css("display","none");
					gender.css("display","none");
					let v=$(e.target).val();
					$("#search-"+v).css("display","inline-block");
				});
				$("#searchType").change();
        	});
        	$("#numPerPage").change(e => {
        		let cPage=<%=request.getParameter("cPage")%>;
        		let numPerPage=$(e.target).val();
        		location.replace("<%=request.getContextPath()%>/admin/memberList?cPage="+cPage+"&numPerPage="+numPerPage);
        	});
        	
        </script>
        
        
    </section>
<%@ include file="/views/common/footer.jsp"%>




















