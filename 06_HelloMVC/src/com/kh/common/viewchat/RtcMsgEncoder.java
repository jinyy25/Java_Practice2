package com.kh.common.viewchat;

import javax.websocket.EncodeException;
import javax.websocket.Encoder.Text;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class RtcMsgEncoder implements Text<RtcMessage> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub

	}

	@Override
	public String encode(RtcMessage arg0) throws EncodeException {
		// TODO Auto-generated method stub
		/*
		 * ObjectMapper mapper=new ObjectMapper(); String value=""; try {
		 * value=mapper.writeValueAsString(arg0); }catch(Exception e) {
		 * e.printStackTrace(); }
		 */
		return new Gson().toJson(arg0);
	}

}
