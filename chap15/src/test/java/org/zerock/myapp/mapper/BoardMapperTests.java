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
import org.zerock.myapp.domain.Criteria;

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
		
		list.forEach(log::info);
		
	}//test
	
	
	
	
	@Test
	public void testGetListWithPaging() {
		log.debug("test() invoked.");
		
		
		Criteria cri = new Criteria();
		cri.setCurrPage(1);
		cri.setAmount(20);
		
		cri.setType("C");
		cri.setKeyword("q");
		
		
		List<BoardVO> list = this.mapper.getListWithPaging(cri);
		
		list.forEach(log::info);
		
		
	}
	
	
	
	
	@Test
	public void testRead() {
		log.debug("testXXX() invoked.");	
		
		BoardVO board = this.mapper.read(1003);
		
		assertNotNull(board);
		
		log.info(board);
		
		
	}//test
	
	@Test
	public void testDelete() {
		log.debug("testXXX() invoked.");	
		
 		int affectedLines = this.mapper.delete(1000);
		
		log.info("\t+ affectedLines : " + affectedLines);
		
		
	}//test
	
	
	@Test
	public void testInsert() {
		log.debug("testXXX() invoked.");	
		
		BoardVO newBoard = new BoardVO(null, "title_1001", "content_1001", "writer_1001", null, null);
 		int affectedLines = this.mapper.insert(newBoard);
		
		log.info("\t+ affectedLines : " + affectedLines);
		
		
	}//test
	
	@Test
	public void testInsertSelectKey() {
		log.debug("testXXX() invoked.");	
		
		BoardVO newBoard = new BoardVO(null, "title_1001", "content_1001", "writer_1001", null, null);
 		int affectedLines = this.mapper.insertSelectKey(newBoard);
		
		log.info("\t+ affectedLines : " + affectedLines);
		
		
	}//test
	
	
	@Test
	public void testUpdate() {
		log.debug("testXXX() invoked.");	
		
		BoardVO newBoard = new BoardVO(1001, "title2_1001", "content2_1001", "writer2_1001", null, null);
 		int affectedLines = this.mapper.update(newBoard);
		
		log.info("\t+ affectedLines : " + affectedLines);
		
		
	}//test
	
	@Test
	public void testGetTotalCount() {
		log.debug("testXXX() invoked.");	
		
		Criteria cri = new Criteria();
		cri.setCurrPage(3);
		cri.setAmount(10);
		
		int board = this.mapper.getTotalCount(cri);
		log.info("\t+ totalAmount: " + board);
		
	}
	

	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
	}//tearDown
	
}
