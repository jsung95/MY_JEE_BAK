package org.zerock.myapp.service;

import java.sql.Connection;
import java.util.Objects;

import javax.sql.DataSource;

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
public class SampleTxServiceTests {

	
	@Setter(onMethod_ = {@Autowired})
	private SampleTxService service;
	

	
	@Test
	public void testAddData() throws Exception {
		log.debug("testAddData() invoked.");
		
		Objects.requireNonNull(service);
		
		String value = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
		this.service.addData(value);
		
	}//testAddData
	
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testXXX() throws SQLException {
		Connection conn = this.dataSource.getConnection();
		
		conn.setTransactionIsolation(0);
		conn.setAutoCommit(false);
		
		
	}

	
	
}
