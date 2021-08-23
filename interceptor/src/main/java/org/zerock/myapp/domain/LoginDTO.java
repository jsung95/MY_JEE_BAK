package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class LoginDTO { ///자바빈즈 규약을 따르는 자바빈즈 객체 
	
	private String userid;
	private String userpw;
	
	private boolean rememberMe;
	
}//end class
