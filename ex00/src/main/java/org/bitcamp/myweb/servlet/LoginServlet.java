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
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		log.debug("service() invoked.");
		
		String userid = req.getParameter("userid");
		String passwd = req.getParameter("passwd");
		
		res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		out.print("아이디값: " + userid + "<br>");
		out.print("비밀번호값: " + passwd + "<br>");
		out.print("</body></html>");
		
		log.info("userid : " + userid);
		log.info("userid : " + passwd);
	}

	

	@Override
	public void init() throws ServletException {
		log.debug("init() invoked.");
	}
	
	
	@Override
	public void destroy() {
		log.debug("destroy() invoked.");
	}


}
