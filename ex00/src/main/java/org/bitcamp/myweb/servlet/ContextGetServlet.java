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


@NoArgsConstructor
@Log4j2

@WebServlet("/ContextGet")
public class ContextGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		log.debug("service(req, res) invoked.");
		
		
		try {
			// 1. 요청에 대한 처리 
			String name = (String)this.getServletContext().getAttribute("name");
			int age = (Integer)this.getServletContext().getAttribute("age");
			
			// 2. 응답문서의 생성 
			res.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = res.getWriter();
			try(out;) {
			
				//응답문서 생성 
				out.print("<html><body>");
				out.print("이름 : " + name + "<br>");
				out.print("나이 : " + age + "<br>");
				out.print("</body></html>");
				
				out.flush();
			} //try-with-resources
			
		} catch (Exception e) {
			throw new ServletException(e);
		} //try-catch
		
	}//service

}//end class
