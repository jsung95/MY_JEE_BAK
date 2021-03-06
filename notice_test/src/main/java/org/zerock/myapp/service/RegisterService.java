package org.zerock.myapp.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.domain.MemberDTO;

public interface RegisterService {
	
	public abstract int checkCBNO(MemberDTO dto);
	
	public abstract Double homeTaxCBNO(MemberDTO dto) throws ClientProtocolException, IOException;
	
	public abstract void sendMail(MemberDTO dto, HttpSession session);
	
	public abstract boolean emailCertifiation(HttpSession session, String inputCode);
	
	//회원가입
	public abstract void register(MemberDTO dto) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	//해쉬 다이제스트 단방향 암호화 알고리즘
	public abstract String digest(MemberDTO dto) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
	//FILE
	public void uploadFile(AttachFileDTO dto);
}
