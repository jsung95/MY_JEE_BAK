package org.bitcamp.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/Member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		log.debug("service(res, req) invoked.");
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		try(out;){
			
//			req.setCharacterEncoding("UTF-8");
			
			Enumeration<String> enu = req.getParameterNames();
			
			out.print("<html><body>");
			while(enu.hasMoreElements()) {
				String name = enu.nextElement();
				String value = req.getParameter(name);
				out.print(name + " : " + value + "<br>");
			}//while
			out.print("</body></html>");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}//try-with-resources
		
		
	}//service

}//end class
