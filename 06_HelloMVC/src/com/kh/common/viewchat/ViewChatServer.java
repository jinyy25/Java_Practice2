package com.kh.common.viewchat;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(
		value="/viewChatServer",
		encoders= {RtcMsgEncoder.class},
		decoders= {RtcMsgDecoder.class}
)
public class ViewChatServer {
	
	
	
	@OnMessage
	public void onMessage(Session session,RtcMessage msg) {
		
		//session데이터 유지하기
		session.getUserProperties().put("msg", msg);
		//접속자현황을 보내주는 로직
		RtcMessage adminMsg=new RtcMessage();
		adminMsg.setMembers(memberList(session));
		adminMsg.setToken("manager");
		adminMsg.setType("members");
		adminBoardCast(session,adminMsg);//전체접속자에게 전송!
		///
		System.out.println(msg);
		//데이터 전송
		try {
			for(Session s : session.getOpenSessions()) {
				if(s!=session) {
					RtcMessage m=(RtcMessage)s.getUserProperties().get("msg");
					if(msg.getReceiver()!=null 
						&& msg.getReceiver().equals(m.getToken())
						) {
						s.getBasicRemote().sendObject(msg);
					}
					else {
						s.getBasicRemote().sendObject(msg);
					}
				}
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private List<String> memberList(Session session){
		List<String> members=new ArrayList();
		for(Session s : session.getOpenSessions()) {
			if(s!=null) {
				RtcMessage msg=(RtcMessage)s.getUserProperties().get("msg");
				if(msg!=null && msg.getToken()!=null) {
					members.add(msg.getToken());
				}
			}
		}
		return members;
	}
	//접속한 session 전체에게 보내는 관리자 메세지
	private void adminBoardCast(Session session,RtcMessage msg) {
		try {
			for(Session s : session.getOpenSessions()) {
				s.getBasicRemote().sendObject(msg);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
