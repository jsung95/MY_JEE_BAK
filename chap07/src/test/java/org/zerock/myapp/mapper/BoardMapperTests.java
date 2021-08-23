package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.BoardVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardMapperTests  {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;		//DI
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
//		Objects.requireNonNull(this.mapper);
//		assert this.mapper != null;
		assertNotNull(this.mapper);
		
		log.info("\t+ mapper : " + this.mapper);
		log.info("\t+ mapper type: " + this.mapper.getClass().getName());
		
	}//setup
	
	@Test
	public void testGetList() {
		log.debug("testXXX() invoked.");	
		
		List<BoardVO> list = this.mapper.getList();
		
		assert list != null;
		
		log.info("\t + list : " + list);
		
	}//test
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
	}//tearDown
	
}
