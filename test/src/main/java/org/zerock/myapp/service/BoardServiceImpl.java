package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BoardServiceImpl implements BoardService{

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Override
	public List<BoardVO> readAll() {
		
		return mapper.getList();
	}

	@Override
	public BoardVO readOne(BoardDTO dto) {
		
		return mapper.readOne(dto);
	}

	@Override
	public boolean register(BoardDTO dto) {
		log.debug("register_service invoked.");
		boolean isSuccess = this.mapper.insert(dto);
		return isSuccess;
	}

	@Override
	public boolean modify(BoardDTO dto) {
		
		boolean isSuccess = this.mapper.update(dto);
		
		return isSuccess;
	}

	@Override
	public boolean remove(BoardDTO dto) {
		
		boolean isSuccess = this.mapper.delete(dto);
		
		return isSuccess;
	}
	
	
}
