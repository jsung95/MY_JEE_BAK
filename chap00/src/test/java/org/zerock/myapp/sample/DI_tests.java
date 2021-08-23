package org.zerock.myapp.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class DI_tests {
	
	@Autowired
	private Restaurant res;
	
	@Before
	public void setup() {
		log.debug("before() invoked.");
	}
	
	@Test
	public void testDI() {
		log.debug("test() invoked.");
	}
	
	@After
	public void tearDown() {
		log.debug("after() invoked.");
	}

}
