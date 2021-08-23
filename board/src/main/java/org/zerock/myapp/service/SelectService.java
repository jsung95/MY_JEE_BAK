package org.zerock.myapp.service;

import java.sql.SQLException;
import java.util.List;

import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.persistence.BoardDAO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SelectService {
	
	public List<BoardVO> execute() throws SQLException {
		
		BoardDAO dao = new BoardDAO();
//		List<BoardVO> board = dao.list();
		List<BoardVO> list = dao.list();
		list.forEach(log::info);
		return dao.list();
	}
	
}
