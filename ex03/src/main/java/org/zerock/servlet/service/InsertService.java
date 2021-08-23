package org.zerock.servlet.service;

import java.sql.SQLException;

import org.zerock.myweb.domain.EmpDTO;
import org.zerock.myweb.persistence.EmpDAO;

public class InsertService {

	
	public void executeService(EmpDTO dto) throws SQLException {
		
		EmpDAO dao = new EmpDAO();
		dao.insertBoard(dto);
		
	}
	
}
