package org.zerock.myweb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myweb.domain.EmpDTO;
import org.zerock.myweb.domain.EmpVO;
import org.zerock.servlet.service.InsertService;
import org.zerock.servlet.service.SelectService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		log.debug("service({}, {}) invoked.", req, res);
		
		String uri = req.getRequestURI();
		
		
		try {
		
			switch (uri) {
			case "/insert.do":
				
				break;
			case "/boardWrite.do":
				String author = req.getParameter("author");
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				
				EmpDTO dto = new EmpDTO();
				dto.setAuthor(author);
				dto.setTitle(title);
				dto.setContent(content);
				
				
				
				
				
				InsertService is = new InsertService();
				is.executeService(dto);
				

				SelectService ss = new SelectService();
				List<EmpVO> board = ss.executeService(dto);
				
				
				req.setAttribute("__BOARD__", board);
				
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/result.jsp");
				dispatcher.forward(req, res);
				
				
				
				
				break;
			case "/select.do":
				
				
				
				
				
				break;
			}
			
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		
		
		
	}//service

}//end class
