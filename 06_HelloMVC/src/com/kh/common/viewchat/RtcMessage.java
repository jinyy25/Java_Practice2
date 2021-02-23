package com.kh.common.viewchat;

import java.util.List;
import java.util.Map;

public class RtcMessage {
	private String token;//접속아이디
	private String receiver;//상대방
	private String type;//메세지핸들링기준값
	private List<String> members;//접속회원정보
	private Sdp sdp;
	private Map<String,String> candidate;
	
	public RtcMessage() {
		// TODO Auto-generated constructor stub
	}
	
	public RtcMessage(String token, String receiver, String type, List<String> members, Sdp sdp,
			Map<String, String> candidate) {
		super();
		this.token = token;
		this.receiver = receiver;
		this.type = type;
		this.members = members;
		this.sdp = sdp;
		this.candidate = candidate;
	}
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}

	public Sdp getSdp() {
		return sdp;
	}

	public void setSdp(Sdp sdp) {
		this.sdp = sdp;
	}

	public Map<String, String> getCandidate() {
		return candidate;
	}

	public void setCandidate(Map<String, String> candidate) {
		this.candidate = candidate;
	}

	@Override
	public String toString() {
		return "RtcMessage [token=" + token + ", receiver=" + receiver + ", type=" + type + ", members=" + members
				+ ", sdp=" + sdp + ", candidate=" + candidate + "]";
	}
	
	
	
}
