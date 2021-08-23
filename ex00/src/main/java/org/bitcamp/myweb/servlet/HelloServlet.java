package org.bitcamp.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet( {"/hello", "/hello01", "/hello02", "/hello03" })
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		log.debug("servlet(req, res) invoked.");
		
//		PrintWriter out = res.getWriter();
//		
//		out.println("Hello World!");
//		out.println("Welcome to JAVA 202.");
//		
//		out.flush();
//		out.close();
		
		//-------------------------------------------
		// Application Scope에 저장된 공유데이터를 참조(=사용) 
		//-------------------------------------------
		ServletContext ctx = this.getServletContext();
		
		String shared0 = (String) ctx.getAttribute("name");
		Integer shared1 = (int) ctx.getAttribute("integer");
		Boolean shared2 = (boolean) ctx.getAttribute("boolean");
		Double shared3 = (double) ctx.getAttribute("double");
		
		log.info("\t + shared 0 : " + shared0);
		log.info("\t + shared 1 : " + shared1);
		log.info("\t + shared 2 : " + shared2);
		log.info("\t + shared 3 : " + shared3);
		
		
	} // service

} // end class
