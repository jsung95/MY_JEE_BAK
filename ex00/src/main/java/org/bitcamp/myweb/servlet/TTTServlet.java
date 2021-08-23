package org.bitcamp.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("*.do")
public class TTTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		log.debug("service(req, res) invoked.");
		
		try {
			
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			
			try(out) {
				out.println("TTTServlet");
				out.flush();
				
			}//try-with-resources
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		
	}//service

}//end class
