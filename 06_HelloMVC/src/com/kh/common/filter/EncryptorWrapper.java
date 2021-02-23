package com.kh.common.filter;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptorWrapper extends HttpServletRequestWrapper {

	public EncryptorWrapper(HttpServletRequest request) {
		super(request);
	}
	
	//getParameter로 클라이언트가 보낸값을 받아올때 password인 값은 암호화처리함.
	
	@Override
	public String getParameter(String name) {
		//password가 name값으로 왔을때
		//아닌것들
		String result="";
		String ori=super.getParameter(name);
		if(name.equals("password")||name.equals("new_password")) {
			//암호화 처리한 값을 전송함.
			System.out.println("암호화전 : "+ori);
			result=getSha512(ori);
			System.out.println("암호화 후  : "+result);
		}else {
			//원본값보내기
			result=ori;
		}
		return result;
	}
	
	private String getSha512(String password) {
		//java에서 제공하는 MessageDigest객체를 이용해서 단방향암호화를 함.
		//기본적으로 java에서 암호화알고리즘을 제공함. (md5, sha128, sha512)
		String encrypt="";
		MessageDigest md=null;
		try {
			//암호화알고리즘 을 세팅하기
			md=MessageDigest.getInstance("SHA-512");
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//암호화알고리즘을 적용하여 문자열을 암호화하기 위해서는 
		// 바이트로 변경해서 적용해야함.
		byte[] bytes=password.getBytes(Charset.forName("UTF-8"));
		//sha512을 이용해서 암호화처리
		md.update(bytes);
		
		//바이트로 되어 있는것을 문자열로 재변환해야지 DB에 저정하고 사용이 가능함.
		//바이트를 문자열로 변경해주는 Encoder를 사용 Base64인코더
		encrypt=Base64.getEncoder().encodeToString(md.digest());
		return encrypt;
		
	}
	
}










