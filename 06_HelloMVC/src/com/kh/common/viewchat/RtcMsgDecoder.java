package com.kh.common.viewchat;

import javax.websocket.DecodeException;
import javax.websocket.Decoder.Text;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RtcMsgDecoder implements Text<RtcMessage> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public RtcMessage decode(String arg0) throws DecodeException {
		// TODO Auto-generated method stub
		ObjectMapper mapper=new ObjectMapper();
		RtcMessage msg=null;
		try {
			msg=mapper.readValue(arg0, RtcMessage.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public boolean willDecode(String arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
