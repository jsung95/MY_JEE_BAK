package org.zerock.myapp.service;

import java.sql.SQLException;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.persistence.BoardDAO;

public class DeleteService {
	public void execute(BoardDTO dto) throws SQLException {
		BoardDAO dao = new BoardDAO();
		
		dao.delete(dto);
		
	}
}
