<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId=request.getParameter("userId");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>패스워드변경</title>
 <style>
    div#updatePassword-container{
        background:red;
    }
    div#updatePassword-container table {
        margin:0 auto;
        border-spacing: 20px;
    }
    div#updatePassword-container table tr:last-of-type td {
        text-align:center;
    }
  </style>
</head>
<body>
    <div id="updatePassword-container">
		<form name="updatePwdFrm" action="<%=request.getContextPath() %>/member/updatePassowrdEnd" method="post" >
			<table>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<th>변경할 비밀번호</th>
					<td>
						<input type="password" name="new_password" id="password">
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>	
						<input type="password" id="pwck">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit"  value="변경" onclick="return fn_validate();"/>&nbsp;
						<input type="button"  value="닫기" onclick="self.close();"/>						
					</td>
				</tr>
			</table>
			<input type="hidden" value="<%=userId %>" name="userId">
		</form>
	</div>
	<script>
		function fn_validate(){
			let pw=document.getElementById("password").value;
			let pwck=document.getElementById("pwck").value;
			if(pw.trim()!=pwck.trim()){
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
		}
	</script>
</body>
</html>







