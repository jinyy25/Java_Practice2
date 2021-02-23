<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 이미지 미리보기 구현</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<style>
	.profile{
		border-radius:50px;
		width:100px;
		height:100px;
	}
</style>
</head>
<body>
	<h1>업로드할 사진 파일 미리보기 구현하기</h1>
	<img class="profile" id="p">
	<input type="file" id="upload" multiple style="display:none;"><br>
	이름<input type="text" id="name"><br> 
	이메일<input type="email" id="email"> 	
	<div id="imgContainer"></div>
	
	<button id="submit">등록하기</button>
	<script>
	
	//ajax로 파일 업로드 처리하기
      $("#submit").click(e=>{
        //ajax로 파일을 전송하기 위해 js의 FormData객체를 이용함
        let form=new FormData();
        //서버에 보낼 데이터를 formData객체에 넣어주면 됨(key,value형식)
        form.append("name",$("#name").val());
        form.append("email",$("#email").val());
        //여러 개 파일 넣기
        /*$.each($("#upload")[0].files,(i,v)=>{
           form.append("test"+i,v); //키가 들어옴
        });*/
        for(let i=0;i<$("#upload")[0].files.length;i++){
           form.append("test"+i,$("#upload")[0].files[i]);
        }
        //form.append("upload",$("#upload")[0].files[0]);
        
        $.ajax({
            url:"<%=request.getContextPath()%>/upload/ajaxUpload",
            data:form,
            type:"post",
            processData:false, //어떤 데이터를 보낼때의 기본 값
            contentType:false, //둘다 false주는 건 기본 형식이 아닌 multipart보낸다는 뜻
            success:data=>{
                console.log("업로드 성공!");
            }
        });
   });
	
		
	$("#p").click(e=>{
		$("#upload").click();
	});
	
	$("#upload").change(e => {
       
		$("#imgContainer").html("");
        $.each(e.target.files,(i,v)=>{           
            let reader=new FileReader();
            reader.onload=e=>{
                // let img=$("<img>",{src:e.target.result,width:100,height:100});
                // $("#imgContainer").append(img);
                $("#p").attr("src",e.target.result);
            }
            reader.readAsDataURL(v);
       
		});
	
		//업로드된 파일을 불러와서 img태그에 넣으려면  FileReader객체 이용
        /*  let reader = new FileReader();
        console.log($(e.target)[0].files[0]);
        //readAsDateUrl이 실행될때 자동으로 실행
        reader.onload = (e) => {
          let img = $("<img>").attr({
            src: e.target.result,
            width: 100,
            height: 100,
          });
          $("#imgContainer").html("");
          $("#imgContainer").append(img);
        };
        reader.readAsDataURL($(e.target)[0].files[0]);  *///파일의 가상경로를 가져옴
      });
	
	</script>
	
</body>
</html>