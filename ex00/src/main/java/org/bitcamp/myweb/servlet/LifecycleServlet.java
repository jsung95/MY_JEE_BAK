package org.bitcamp.myweb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;


@NoArgsConstructor
@Log4j

@WebServlet("/Lifecycle")
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	// Servlet Container가 실행시키는, Servlet 객체의 생명주기를 확인
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
	}//service
	

	@Override
	public void destroy() {
		log.debug("destroy() invoked.");
		
	}//destroy
	
	@Override
	public void init() throws ServletException {
		log.debug("init() invoked.");
		
	}//doGet
	
	
}//end class
