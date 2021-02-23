package com.websocket.controller;

import java.util.Map;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



//websocket 서버를 구성하는 방법
//class에 @ServerEndpoint 선언

//websocket서버에서 javascript가 보낸 객체를 받고 javascript객체 형식으로 보내려면
//encoder, decoder가 필요
//서버에 encoder,decode를 등록
//encoder와 decoder는 규약에 맞는 클래스로 작성 해주면 됨
//encoder : javascript객체를 java객체로 변환해주는 역할을 하는 클래스
//decoder : java객체를 javascript객체로 변환해주는 역할을 하는 클래스
//규약 : Encoder/Decoder.Text<vo객체>인터페이스를 구현한 클래스!

@ServerEndpoint(value="/chatting",encoders= {MessageEncoder.class},decoders= {MessageDecoder.class})
public class ChattingServer {
   
   @OnOpen
   public void onOpen(Session session,EndpointConfig config) {
      //클라이언트 요청이 오면 실행되는 함수
      //클라이언트에서 new WebSocket(적은 주소가 일치하면)를 실행하면 실행되는 메소드
      System.out.println("WebSocket서버 접속함");//바로 console에 출력됨
      
      //session은 클라이언트 구분자
      //시크릿창으로 하나 더 열어봄
      System.out.println(session.getId());
      //각각 다른 id값이 부여됨.절대 중복되지 않음
      
      //접속성공했다는 메세지를 클라이언트에게 전송하기
      //전송방법
      //session.getBasicRemote() : 데이터를 전송할 클라이언트 스트림을 가져옴
      //session.getBasicRemote().sendText() : 문자열을 전송하는 메소드
      //예외처리를 해줘야 함
      try {
          //session.getBasicRemote().sendText("admin, ,==== 채팅서버에 접속하신 것을 환영합니다.====");
    	  //객체 형식으로 보내려면 sendObject()매소드를 이용해서 처리
    	  session.getBasicRemote().sendObject(new Message("admin"," ","==== 채팅서버에 접속하신 것을 환영합니다.===="));
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   
   
   //클라이언트가 보낸 메세지 받기
   @OnMessage
   //public void message(Session session,String msg) {
    public void message(Session session,Message msg) { //decoder가 등록 되었을시
   	  //session은 보낸 사람의 session객체임. id값 들어있음
      //msg는 클라이언트가 send한 매개변수가 msg매개변수로 들어옴 send(내가 접속함~)
      //System.out.println(msg);
      
      //접속한 세션에 특정 아이디값을 매핑하여 저장
      //session.getUserProperties()메소드를 이용해서 key:value형식으로 클라이언트의 정보를 저장할 수 있음
      session.getUserProperties().put("data",msg); //세션과 매핑되는 아이디값을 가지고 있음. 세션이 중복되지 않게 계속 생성됨
      //순회해서 한 명씩 가져와야 함
      //이미 집어넣은 값
      
      //massage를 파싱하기
      //0:보낸 사람, 1: 받는 사람,2:메세지
      //String[] message=msg.split(","); //이미 구분가능
      
      
      
      //받은 메세지 전송하기. 전체 session에 전송
      //onmessage 실행
      //div 출력 내용이 내가 접속함~으로 바뀜
      try {
         //특정 사람에게만 전송하기
         Set<Session> clients=session.getOpenSessions();
         
         //if(!message[1].equals(" ")) {
         if(msg.getReceiver().equals(" ")) {
            for(Session client : clients) {
               Map<String,Object> clientData=client.getUserProperties();
               //반환형이 map
               //접속된 클라이언트의 정보를 가져옴
               //String data=(String)clientData.get("data");
               //String[] mh=data.split(",");//저장된 정보를 이용할 수 있게 파싱처리      
               //if(message[1].equals(mh[0])
            		  // ||message[0].equals(mh[0])) { //저장된 정보와 보낸 사람의 msg를 비교해서 보내는 처리하기
                  //보낸 사람이 받는 사람이랑 같으면 보내기
                  //client.getBasicRemote().sendText(msg);
                  //break; //1:1로할때만 사용
               	  
               //객체로 데이터 전송하기
               Message cm=(Message)clientData.get("data");
               if(msg.getReceiver().equals(cm.getSender())
            		   ||msg.getSender().equals(cm.getSender())) {
            	     client.getBasicRemote().sendObject(msg);
               	  }
               
               }
            
         }else{            
         //본인한테만 보내지 말고, 모든 접속한 session에 데이터 보내기!전체 채칭
         //접속한 session 객체 가져오기
         //sesion.getOpenSession()메소드 이용 
         //-> 현재 서버에 접속되어 있는session들을 set객체로 반환(중복되면 안되니까)
         // Set<Session> clients=session.getOpenSessions(); //접속한 클라이언트
         for(Session client : clients) {
        	//session.getBasicRemote().sendText(msg);//접속한 클라이언트에게 전체 전송
        	//client.getBasicRemote().send(msg);
        	 client.getBasicRemote().sendObject(msg);
         }                  
         }
      }catch(Exception e) {
         e.printStackTrace();
      }

   }
}