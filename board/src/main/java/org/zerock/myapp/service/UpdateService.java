package org.zerock.myapp.service;

import java.sql.SQLException;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.persistence.BoardDAO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class UpdateService {
	
	public void execute(BoardDTO dto) throws SQLException {
		
		BoardDAO dao = new BoardDAO();
		dao.update(dto);
	}
}
