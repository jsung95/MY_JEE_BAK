package org.zerock.myapp.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

//@NoArgsConstructor
@AllArgsConstructor
@Log4j2

@Service
public class BoardServiceImpl implements BoardService, InitializingBean, DisposableBean {
	
//	@Setter(onMethod_ = {@Autowired})
	private BoardMapper mapper;

	// Spring v4.3 이후부터는, 주입받을 필드가 1개이고, 이 필드를 생성자의 매개변수로 가지는 
	// 생성자를 선언하면, 의존성주입(DI) signal( "@Setter(onMethod_ = {@Autowired})" )을 보내지 않아도, 
	// 스프링이 자동으로 주입을 해준다. 	
	// @AllArgsConstructor 하나만 붙여주면 된다.
	
	@Override
	public List<BoardVO> getList() {
		log.debug("getList() invoked.");
		
		Objects.requireNonNull(this.mapper);
		
		
		return mapper.getList();
	} //getList

	
	@Override
	public List<BoardVO> getListPerPage(Criteria cri) {
		log.debug("getListPerPage() invoked.");
		
		log.info("\t+ Criteria : " + cri);
		Objects.requireNonNull(this.mapper);
		
		return mapper.getListWithPaging(cri);
	}
	
	
	@Override
	public int getTotal(Criteria cri) {
		log.debug("getTotal() invoked.");
		
		return mapper.getTotalCount(cri);
	}
	
	
	
	@Override
	public boolean register(BoardVO board) {
		log.debug("register({}) invoked.", board);
		
		Objects.requireNonNull(this.mapper);
		
//		int affectedLines = this.mapper.insert(board);
		int affectedLines = this.mapper.insertSelectKey(board);
		
		return (affectedLines == 1);
		
	} //register

	
	
	@Override
	public boolean modify(BoardVO board) {
		log.debug("modify({}) invoked.", board);
		
		Objects.requireNonNull(this.mapper);
		
		
		return (this.mapper.update(board) == 1);
	} //modify

	
	
	@Override
	public boolean remove(Integer bno) {
		log.debug("remove({}) invoked.", bno);
		
		Objects.requireNonNull(this.mapper);
		
		
		return (this.mapper.delete(bno) == 1);
	}

	
	
	@Override
	public BoardVO get(Integer bno) {
		log.debug("get({}) invoked.", bno);
		
		Objects.requireNonNull(this.mapper);
		
		return this.mapper.read(bno);
	}



	@Override
	public void destroy() throws Exception {
		log.info("destroy() invoked.");
		
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("afterPropertiesSet() invoked.");
		
		Objects.requireNonNull(this.mapper);
		
		log.info("\t+ mapper : " + this.mapper);
		
	}







	

}
