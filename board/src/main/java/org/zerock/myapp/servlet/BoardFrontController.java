package org.zerock.myapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.PageTO;
import org.zerock.myapp.domain.SearchDTO;
import org.zerock.myapp.persistence.BoardDAO;
import org.zerock.myapp.service.DeleteService;
import org.zerock.myapp.service.InsertService;
import org.zerock.myapp.service.ReplyService;
import org.zerock.myapp.service.ReplyuiService;
import org.zerock.myapp.service.RetrieveService;
import org.zerock.myapp.service.SearchService;
import org.zerock.myapp.service.SelectBoardService;
import org.zerock.myapp.service.SelectService;
import org.zerock.myapp.service.UpdateService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		
		try {
			
			String uri = req.getRequestURI();
			String disPage = null;
			
			String num = null;
			String author = null;
			String title = null;
			String content = null;
			String readcnt = null;
			String writeday = null;
			String repRoot = null;
			String repStep = null;
			String repIndent = null;
			
			String searchName = null;
			String searchValue = null;
			
			BoardDTO dto = new BoardDTO();
			
			switch (uri) {
				case "/select0.do":
					log.debug("select invoked.");
					SelectService ss = new SelectService();
					List<BoardVO> list = ss.execute();
					
					req.setAttribute("__BOARD__", list);
					
					disPage = "boardlist.jsp"; 
					
					break;
				case "/select.do":
					log.debug("select invoked.");
					
					int curPage = 1; //기본 페이지
					
					if(req.getParameter("curPage") != null) {
						curPage = Integer.parseInt(req.getParameter("curPage"));
					}
					BoardDAO dao = new BoardDAO();
					PageTO page_list = dao.page(curPage);
					
					req.setAttribute("__PAGELIST__", page_list.getList());
					req.setAttribute("__PAGE__", page_list);
					
					disPage = "listPage.jsp";
					
					
					break;
				case "/write.do":
					log.debug("write invoked.");
					title = req.getParameter("title");
					author = req.getParameter("author");
					content = req.getParameter("content");
					
					
					
					dto.setTitle(title);
					dto.setAuthor(author);
					dto.setContent(content);
					
					InsertService is = new InsertService();
					is.execute(dto);
					
					disPage = "select_board.do";
					
					break;
//					===========================
				case "/select_board.do":
					log.debug("select_board invoked.");
					
					SelectBoardService ss2 = new SelectBoardService();
					List<BoardVO> sel_list = ss2.execute();
					
					sel_list.forEach(t->log.info(sel_list));
					log.info("\t + @@@@ : " + sel_list.get(0).getNum());
					
					
					req.setAttribute("__BOARD__", sel_list);
					disPage = "retrieve.do?num="+sel_list.get(0).getNum(); 
					break;
//					============================
				case "/retrieve.do":
					log.debug("retrieve invoked.");
					
					num = req.getParameter("num");
					
					dto.setNum(Integer.parseInt(num));
					
					RetrieveService rs = new RetrieveService();
					List<BoardVO> retList = rs.execute(dto);
	
					req.setAttribute("__RETRIEVE__", retList);
					disPage = "retrieve.jsp";
					
					break;
				case "/update.do":
					log.debug("update invoked.");
					title = req.getParameter("title");
					author = req.getParameter("author");
					content = req.getParameter("content");
					num = req.getParameter("num");
					
					dto.setTitle(title);
					dto.setAuthor(author);
					dto.setContent(content);
					dto.setNum(Integer.parseInt(num));
					
					UpdateService us = new UpdateService();
					us.execute(dto);
					
					disPage = "retrieve.do?num="+num;
					
					break;
					
				case "/delete.do":
					log.debug("delete invoked.");
					num = req.getParameter("num");
					
					dto.setNum(Integer.parseInt(num));
					
					DeleteService remove = new DeleteService();
					remove.execute(dto);
					
					disPage = "select.do";
					
					break;
				case "/search.do":
					log.debug("search invoked.");
					
					searchName = req.getParameter("searchName");
					searchValue = req.getParameter("searchValue");
					
					SearchDTO search_dto = new SearchDTO();
					search_dto.setSearchName(searchName);
					search_dto.setSearchValue(searchValue);
					
					SearchService searchService = new SearchService();
					List<BoardVO> searchList = searchService.execute(search_dto);
					
					req.setAttribute("__BOARD__", searchList);
					
					disPage = "boardlist.jsp";
					break;
				case "/replyui.do":
					log.debug("replyui invoked.");
					
					num = req.getParameter("num");
					
					dto.setNum(Integer.parseInt(num));
					
					ReplyuiService replyuiService = new ReplyuiService();
					List<BoardVO> replyuiList = replyuiService.execute(dto);
					
					req.setAttribute("__REPLYUI__", replyuiList);
					
					disPage = "reply.jsp";
					
					break;
				
				case "/reply.do":
					log.debug("reply invoked.");
					
					num = req.getParameter("num");
					title = req.getParameter("title");
					author = req.getParameter("author");
					content = req.getParameter("content");
					repRoot = req.getParameter("repRoot");
					repStep = req.getParameter("repStep");
					repIndent = req.getParameter("repIndent");
					
					dto.setNum(Integer.parseInt(num));
					dto.setTitle(title);
					dto.setAuthor(author);
					dto.setContent(content);
					dto.setRepRoot(Integer.parseInt(repRoot));
					dto.setRepStep(Integer.parseInt(repStep));
					dto.setRepIndent(Integer.parseInt(repIndent));
					
					ReplyService replyService = new ReplyService();
					replyService.execute(dto);
					
					
					disPage = "select.do";
					
					break;
					
			}
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(disPage);
			dispatcher.forward(req, res);
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}//service

}//end class
