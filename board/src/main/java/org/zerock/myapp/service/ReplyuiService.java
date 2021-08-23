package org.zerock.myapp.service;

import java.sql.SQLException;
import java.util.List;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.persistence.BoardDAO;

public class ReplyuiService {
	public List<BoardVO> execute(BoardDTO dto) throws SQLException {
		BoardDAO dao = new BoardDAO();
		List<BoardVO> list = dao.replyui(dto);
		return list;
	}
}
