package org.zerock.myapp.service;

import java.util.Objects;

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
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SampleServiceTests {

	
	@Setter(onMethod_ = {@Autowired})
	private SampleService service;
	
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		Objects.requireNonNull(service);
		log.info("\t+ service : " + service);
		log.info("\t+ Type : " + service.getClass().getName());
	}
	
	@Test
	public void test() throws Exception {
		log.debug("testDoAdd() invoked.");
		
		int result = this.service.doAdd("100", "200");
		log.info("\t+ result = " + result);
	}
	
	@Test
	public void testWithError() throws Exception {
		log.debug("testWithError() invoked.");
		
		int result = this.service.doAdd("10asd0", "200");
		log.info("\t+ result = " + result);
	}
	
	@Test
	public void testMethod2() throws Exception {
		log.debug("testMethod2() invoked.");
		
		this.service.method2("lee", 23);
	}
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
	}
	
}
