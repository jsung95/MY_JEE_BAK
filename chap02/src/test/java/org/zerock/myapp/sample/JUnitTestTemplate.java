package org.zerock.myapp.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@NoArgsConstructor

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/webapp/WEB-INF/spring/root-context.xml"})
public class JUnitTestTemplate {
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
	}
	
	@Test(timeout = 1000)
	public void testJUnit1() {
		log.debug("testJUnit1() invoked.");
	}
	
	
	@Test
	public void testJUnit2() {
		log.debug("testJUnit2() invoked.");
	}
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
	} 
	
}
