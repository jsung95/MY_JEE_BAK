package org.zerock.myapp.service;

import java.sql.SQLException;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.persistence.BoardDAO;

public class InsertService {
	
	public void execute(BoardDTO dto) throws SQLException {
		BoardDAO dao = new BoardDAO();
		dao.write(dto);
	}//execute
	
}//end class
