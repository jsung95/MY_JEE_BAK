package org.zerock.myapp.persistence;

import java.util.Date;

import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;

public interface UserDAO {
	
	public abstract UserVO selectUser(LoginDTO dto) throws Exception;
	
	//사용자 테이블에 Remember-Me와 관련된 2개 컬럼 업데이트 수행
	public abstract int updateUserWithRememberMe(String userid, String rememberMe, Date rememberMeMaxAge) throws Exception;
	
	//rememberMe 갑승로 조회해서, 
	public abstract UserVO selectUserWithRememberMe(String rememberMe) throws Exception;
}
