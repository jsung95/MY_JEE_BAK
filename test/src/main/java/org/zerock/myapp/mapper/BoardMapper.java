package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;

public interface BoardMapper {
	public abstract List<BoardVO> getList();
	public abstract BoardVO readOne(BoardDTO dto);
	public abstract boolean insert(BoardDTO dto);
	public abstract boolean update(BoardDTO dto);
	public abstract boolean delete(BoardDTO dto);
}
