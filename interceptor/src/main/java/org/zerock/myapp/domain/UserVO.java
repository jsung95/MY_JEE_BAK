package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class UserVO {	//Session Scope에 유지할 사용자 정보객체(로그인 성공시)
	
	private String userid;
	private String userpw; // Bcrypt : 해쉬값을 저장 
	private String uname;
	private Integer upoint;
	
}
