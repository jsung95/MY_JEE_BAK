package org.zerock.myapp.service;

import java.util.Date;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;

public interface UserService {
	
	public abstract UserVO login(LoginDTO dto) throws Exception;
	public abstract int modifyUserWithRememberMe(String userId, String rememberMe, Date rememberMeAgeMaxAge) throws Exception;
	
	public abstract UserVO findUserByRememberMe(String rememberMe) throws Exception;
}
