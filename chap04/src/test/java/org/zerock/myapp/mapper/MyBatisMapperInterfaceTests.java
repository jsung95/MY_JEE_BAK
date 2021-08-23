package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MyBatisMapperInterfaceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private TTT ttt;

	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		
		assertNotNull(this.ttt);
		log.info("\t+ ttt : " + ttt);
		
	}
	
	@Test
	public void testGetMyName() {
		log.debug("setup() invoked.");
		
		
		String myName = ttt.getMyName();
		log.info("myname : " + myName);
	}
	
	
	
	@Test
	public void testGetSalary() {
		log.debug("setup() invoked.");
		
		
		double myName = ttt.getSalary(100, "SKING");
		log.info("\t+ salary : " + myName);
	}
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
	}
	
	
	
}
