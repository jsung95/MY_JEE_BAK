package org.zerock.myapp.service;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.persistence.UserDAO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service
public class UserServiceImpl implements UserService {
    @Setter(onMethod_=@Autowired)
    private UserDAO dao;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
	      log.debug("login({}) invoked.", dto);
	      
	      Objects.requireNonNull(this.dao);
	      
	      log.info("\t+ dao: {}", this.dao);
	      
	      return this.dao.selectUser(dto);
	}

	@Override
	public int modifyUserWithRememberMe(String userId, String rememberMe, Date rememberMeAgeMaxAge) throws Exception {
		log.debug("modifyUserWithRememberMe({}, {}, {}) invoked.", userId, rememberMe, rememberMeAgeMaxAge);
        Objects.requireNonNull(this.dao);
      
        log.info("\t+ dao: {}", this.dao);
		
		return this.dao.updateUserWithRememberMe(userId, rememberMe, rememberMeAgeMaxAge);
	}

	@Override
	public UserVO findUserByRememberMe(String rememberMe) throws Exception {
		log.debug("findUserByRememberMe({}) invoked.", rememberMe);
        Objects.requireNonNull(this.dao);
      
        log.info("\t+ dao: {}", this.dao);
		
		return this.dao.selectUserWithRememberMe(rememberMe);
	}
   


}//end class