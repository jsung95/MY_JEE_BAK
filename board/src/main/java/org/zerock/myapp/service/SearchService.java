package org.zerock.myapp.service;

import java.sql.SQLException;
import java.util.List;

import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.SearchDTO;
import org.zerock.myapp.persistence.BoardDAO;

public class SearchService {
	public List<BoardVO> execute(SearchDTO s_dto) throws SQLException {
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list = dao.search(s_dto);
		
		return list;
	}
}
