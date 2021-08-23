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

@WebServlet("/ContextSet")
public class ContextSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		log.debug("service() invoked.");
		
		try {
			String name = "홍길동";
			int age = 20;
			
			this.getServletContext().setAttribute("name", name);
			this.getServletContext().setAttribute("age", age);
			
			PrintWriter out = res.getWriter();
			try(out;){
				
				out.flush();
			}//try-with-resources
			
		} catch (Exception e) {
			throw new ServletException(e); 
		}//try-catch
		
	}


}
