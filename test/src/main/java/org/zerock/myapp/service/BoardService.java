package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;

public interface BoardService {
	public abstract List<BoardVO> readAll();
	public abstract BoardVO readOne(BoardDTO dto);
	public abstract boolean register(BoardDTO dto);
	public abstract boolean modify(BoardDTO dto);
	public abstract boolean remove(BoardDTO dto);
}
