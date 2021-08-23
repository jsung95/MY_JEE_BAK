package org.bitcamp.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@WebServlet({ "/PostPre", "/PostPre2" })
public class PostPreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//@PostConstruct
	//1. 리턴타입은 void로 해야 한다.
	//2. throws 절로 예외를 던져서는 안된다!
	@PostConstruct
	public void postConstruct() {
		log.debug("postConstruct() invoked.");
	}//postConstruct
	
	//@PreDestroy
	//1. 리턴타입은 void로 해야 한다.
	//2. throws 절로 예외를 던져서는 안된다!
	@PreDestroy
	public void preDestroy() {
		log.debug("preDestroy() invoked.");
	}//preDestroy
	
	
	//Servlet Lifecycle method
	public void init(ServletConfig config) throws ServletException {
		log.debug("init(config) invoked.");
		
	}//init

	
	//Servlet Lifecycle method
	public void destroy() {
		log.debug("destroy() invoked.");
		
	}//destroy
	

	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		//-----------------------//
		//응답문서 생성 			 //
		//-----------------------//
		res.setContentType("text/html; charset=utf8");
		
		PrintWriter out = res.getWriter();
		
		out.println("<!DOCTYPE html>");
		
		out.println("<html lang='ko'>");
		
		out.println("<head>");
		out.println("	<meta charset='utf8'>");
		out.println("	<title>PostPreServlet</title>");
		out.println("</head>");
		
		out.println("<body>");
		out.println("	<h1>/PostPre, /PostPre2</h1>");
		out.println("</body>");
		
		out.println("</html>");
		
		out.flush();  //출력버퍼에 잔류하는 데이터를 강제로 방출 
		out.close();  //자원객체인 out을 해제(자원해제)
		
		
	}//service

}//end class
