package org.zerock.servlet.service;

import java.sql.SQLException;
import java.util.List;

import org.zerock.myweb.domain.EmpDTO;
import org.zerock.myweb.domain.EmpVO;
import org.zerock.myweb.persistence.EmpDAO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SelectService {
	
	public List<EmpVO> executeService(EmpDTO dto) throws SQLException {
		
		
		EmpDAO dao = new EmpDAO();
		
		List<EmpVO> board = dao.selectSQL(dto);
		board.forEach(log::info);
		return board;
		
	}

}
