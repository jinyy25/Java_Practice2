<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본 채팅 구성하기</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<style>
div#msgContainer{
padding:20px;
width:400px;
}
</style>
</head>
<body>
   <h1>내가 만든 첫 번째 실시간 채팅</h1>
   <input type="text" id="nickname" size="5" placeholder="사용할 별명">
   <input type="text" id="msg"><button id="sendMsg">전송</button>
   <input type="text" id="receiver" size="5" placeholder="보낼 사람">
   <div id="msgContainer"></div>
   
   <script>
   //클라이언트 단
      let socket;
      //1.Websocket객체를 만들어서 소켓서버와 연결함
      socket=new WebSocket("ws://localhost:9090<%=request.getContextPath()%>/chatting");
      //객체생성되면서 서버와 바로 연결됨
      //서버에 접속 요청을 함
      
      //소켓서버에 접속되면 자동으로 실행되는 함수
      //페이지 콘솔창에 찍힘
      socket.onopen=function(e){
         console.log(e);
      }
      
      //서버에서 sendText()메소드 실행하면
      //socket.onmessage에 있는 함수가 실행됨
      socket.onmessage=function(e){
         console.log(e);
         console.log(e.data);				//문자열 값을 가져옴
         //객체형식의 String으로 넘어온 것을 JSON.parse()함수를 이용해서 javascript객체로 만들어줌
         console.log(JSON.parse(e.data));
         //객체로 변환한 내용을 변수에 저장
         let msg=JSON.parse(e.data);
         
         
         
         //$("#msgContainer").html(e.data);	//div에 출력
         //출력 시에 메세지 기준에 의해 메세지를 구분함
         
         // let messageHandler=e.data.split(",");//파싱처리
         //0 : 보낸 사람, 1: 받는 사람, 2 : 메세지
         //console.log(messageHandler);        
         //if(messageHandler[0]=='admin') {
        if(msg["sender"]=="admin"){	 
            //string형식으로 출력
        	//$("#msgContainer").append("<h4 style='color:green;'>"+messageHandler[2]+"</h4>");
        	//객체형식으로 출력
        	$("#msgContainer").append("<h4 style='color:green;'>"+msg["msg"]+"</h4>");
         //} else if(messageHandler[1]!==" "&&messageHandler[0]==$("#nickname").val()) {//문자열형식
          } else if(msg["receiver"]==" " && msg["sender"]==$("#nickname").val()) { 		//객체형식
        	 let container = $("<p>").css({
               "clear":"both",//float 겹쳐서 뜨는 거 띄우기
               "float":"right",
               "backgroundColor" : "yellow"
            //}).html(messageHandler[0]+" : "+messageHandler[2]);//문자열형식
        	 }).html(msg["sender"]+" : "+msg["msg"]);//객체형식
            $("#msgContainer").append(container);
         } else {
        	 //if(messageHandler[1]!=" "
        	 //		 &&
        			 //($("#nickname").val().trim()==messageHandler[0]
        	 		 //||messageHandler[1]==$("#nickname").val().trim())
        		 	//){
        		 const sender=$("#nickname").val();
        		 if(msg["receiver"]!=" "
        			 &&sender==msg["sender"]||sender==msg["receiver"])
        			 ){        			 
        		      let container = $("<p>").css({
        		          "clear":"both",
        		          "float":sender==msg["sender"]?"right":"left",
        		          "backgroundColor":"red"
        		      //}).html("<귓속말>"+messageHandler[0]+" : "+messageHandler[2]);
        		      }).html("<귓속말>"+msg["sender"]+" : "+msg["msg"]);
        		      $("#msgContainer").append(container);        		 
        }
        	else{
            let container = $("<p>").css({
             	"clear":"both",
                "float":"left",
                "backgroundColor":"green"
            //}).html(messageHandler[0]+" : "+messageHandler[2]);
            }).html("<전체>"+msg["sender"]+" : "+msg["msg"]);
            $("#msgContainer").append(container);
        }
     }
  };
      
      //메세지 핸들링
      //메세지를 구분할 수 있게 메세지를 구성해보자
      //보내는 id,받는 id,메세지 ,로 구분 => 받는 아이디가 없으면 전체!
      //서로 맞춰야 함
      
      //서버에 메세지 보내기
      //socket.send()함수 이용해서 메세지 전송
      $("#sendMsg").click(function(e){
         //socket.send("내가 접속함~");
         if($("#nickname").val().length==0){
            alert("사용할 별명을 입력하세요");
            return;
         }
         let id=$("#nickname").val();
         let receiver=$("#receiver").val().length==0?" ":$("#receiver").val();
         //socket.send(id+","+receiver+","+$("#msg").val());//input태그에 입력된 값 넘기기
         //객체로 전송
         //String형식으로 JSON을 전송
         socket.send(JSON.stringify(new Message(id,receiver,$("#msg").val())));         
         //alert("서버에 메세지 전송"); //서버의 @OnMessage 실행
      });
      
      //객체로 메세지 핸들링하기
      //javascript객체를 websocket서버에 전송하고 websocket서버는 javascript와 대응되는
      //vo를 만들어서 통신하기
        
      //데이터 보관용    
      	function Message(sender,receiver,msg){
    		this.sender=sender;
    		this.receiver=receiver;
    		this.msg=msg;	
      	}
      
   </script>
   
</body>
</html>