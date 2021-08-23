package org.zerock.myapp.persistence;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDAOTests {

	@Setter(onMethod_ = @Autowired)
	private UserDAO dao;
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		Objects.requireNonNull(this.dao);
		log.info("\t+ dao : {}", dao);
	}
	
	@Test
	public void test() throws Exception {
		log.debug("test() invoked.");
		
		LoginDTO dto = new LoginDTO();
		dto.setUserid("USER_3");
		dto.setUserpw("PASS_3");
		dto.setRememberMe(false);
		
		UserVO userVO = this.dao.selectUser(dto);
		Objects.requireNonNull(userVO);
		log.info("\t+ userVO : {}", userVO);
		
	}
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
	}
	
	
	
}
