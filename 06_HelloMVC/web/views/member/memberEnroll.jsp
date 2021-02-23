<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<section id=enroll-container>
        <h2>회원 가입 정보 입력</h2>
        <form action="<%=request.getContextPath() %>/memberEnrollEnd.do" method="post" >
        <table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" placeholder="4글자이상" name="userId" id="userId_" required>
					<input type="button" value="중복검사" onclick="fn_checkIdDuplicate();">
				</td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" name="password" id="password_" required><br>
					
				</td>
			</tr>
			<tr>
				<th>패스워드확인</th>
				<td>	
					<input type="password" id="password_2" required><br>
				</td>
			</tr>  
			<tr>
				<th>이름</th>
				<td>	
				<input type="text"  name="userName" id="userName" required><br>
				</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>	
				<input type="number" name="age" id="age"><br>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email"><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required><br>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address"><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M" checked>
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F">
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동"><label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산"><label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서"><label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임"><label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행"><label for="hobby4">여행</label><br />
				</td>
			</tr>
		</table>
		<input type="submit" value="가입" onclick="return invalidate();">
		<input type="reset" value="취소">
        </form>
        
        <form action="" name="checkIdDuplicate">
        	<input type="hidden" name="userId">
        </form>
    </section>

    <script>
    	/*아이지 중복확인 함수 */
    	function fn_checkIdDuplicate(){
    		let userId=$("#userId_").val();
    		if(userId.trim().length<4){
    			alert("아이디는 4글자 이상 입력하세요");
    			return;
    		}
    		//새창을 열고 checkIdDuplicate form을 이용하여 데이터 전송
    		//새창에 대한 설정
    		const url="<%=request.getContextPath()%>/checkIdDuplicate";
    		const title="checkIdDuplicate";
    		const status="left=500px,top=100px, width=300px, height=200px";
    		//새창띄우기
    		open("",title,status);
    		
    		//form태그에 접근할때 form의 name속성이 있으면 name값으로 태그를 가져올 수 있음
    		console.log(checkIdDuplicate);
    		//checkIdDuplicate.target
    		checkIdDuplicate.target=title;
    		checkIdDuplicate.action=url;
    		checkIdDuplicate.method="post";
    		checkIdDuplicate.userId.value=userId;
    		//form전송하기
    		checkIdDuplicate.submit();
    	}
    
    
    
    	function invalidate(){
    		let userId=$("#userId_").val();
    		if(userId.trim().length<4){
    			alert("아이디를 4글자 이상 입력하세요");
    			return false;
    		}
    	}
    	$("#password_2").blur(e => {
    		let pw=$("#password_").val();
    		if(pw.trim()!=$(e.target).val().trim()){
    			alert("비밀번호가 일치하지 않습니다!");
    			$("#password_").val("");
    			$(e.target).val("");
    			$("#password_").focus();
    		}
    	});
    </script>

<%@ include file="/views/common/footer.jsp"%>












