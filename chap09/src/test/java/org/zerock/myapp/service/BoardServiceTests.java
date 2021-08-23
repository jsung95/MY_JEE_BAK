package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
	})

@Log4j2
@NoArgsConstructor
public class BoardServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardService service; //테스트할 대상 빈 객체 
	
	@Before
	public void setup() {	// 사전처리에서는 빈객체가 의존성 주입이 제대로 되었는지 확인 
		log.debug("setup() invoked.");
		Objects.requireNonNull(this.service);
		
		log.info("\t+ service : {}", this.service);
	}//setup
	
	
	@Test 
	public void testGetList() {
		log.debug("testGetList() invoked.");
		
		List<BoardVO> boards = this.service.getList();
		
		Objects.requireNonNull(boards);
		boards.forEach(log::info);
		
		
		//리스트 자원객체 해제코드(빨리 GC되도록 해줌 ...)
		boards.clear();
		boards = null;
		
	}//testGetList
	
	@Test
	public void testRegister() {
		log.debug("test() invoked.");
		
		BoardVO board = new BoardVO(null, "title99", "content99", "writer99", null, null); 
		boolean a = this.service.register(board);
		
		log.info("\t+ vo : " + board);
		
		if(a) {
			log.info("\t+ New board registered.");
			log.info("\t+ board: {}", board);
		} else {
			log.info("\t+ No board registered.");
		}
	}//testRegister
	
	
	@Test
	public void testModify() {
		log.debug("testModify() invoked.");
		
		BoardVO board = new BoardVO(1005, "title1005", "content1005", "writer1005", null, null); 
		boolean a = this.service.modify(board);
		
		log.info("\t+ vo : " + board);
		
		if(a) {
			log.info("\t+ New board modified.");
			log.info("\t+ board: {}", board);
		} else {
			log.info("\t+ No board modified.");
		}
		
	}//testModify
	
	
	@Test 
	public void testRemove() {
		log.debug("testRemove() invoked.");
		
		if(this.service.remove(1005)) {
			log.info("\t+ board removed.");
		} else {
			log.info("\t+ board remove failed.");
		}
		
	}//testRemove
	
	
	@Test
	public void testGet() {
		log.debug("testRemove() invoked.");
		
		BoardVO board = this.service.get(999);
		
		log.info("\t+ board : {}", board);
		
	}//testGet
	
	
	@After
	public void tearDown() {
		log.debug("teardown() invoked.");
	}//tearDown
	
	
}
