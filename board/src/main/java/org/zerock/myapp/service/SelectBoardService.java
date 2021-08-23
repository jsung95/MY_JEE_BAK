package org.zerock.myapp.service;

import java.sql.SQLException;
import java.util.List;

import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.persistence.BoardDAO;

public class SelectBoardService {
	public List<BoardVO> execute() throws SQLException {
		
		BoardDAO dao = new BoardDAO();
//		List<BoardVO> board = dao.list();
		List<BoardVO> list = dao.readRecent();
		
		return list;
	}
}
