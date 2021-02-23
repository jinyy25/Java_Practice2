package com.websocket.controller;

import javax.websocket.DecodeException;
import javax.websocket.Decoder.Text;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class MessageDecoder implements Text<Message> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
		
	}
	
	//javascript형식의 문자열을 java객체에 대입
	@Override
	public Message decode(String arg0) throws DecodeException {		
		return new Gson().fromJson(arg0,Message.class);
	}
	
	//willDecode 매소드는 반드시 true;
	@Override
	public boolean willDecode(String s) {
		// TODO Auto-generated method stub
		return true;
	}

}
