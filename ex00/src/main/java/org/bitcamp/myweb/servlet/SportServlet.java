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

@WebServlet("/Sport")
public class SportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		log.debug("service(req, res) invoked.");
		

		//setContentType은 반드시 out 객체를 생성하기 전에 수행되어야 한다. 
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		
		try(out;){
			//--------------------------------------
			//1.전송파라미터 값 획득
			//--------------------------------------
			
			//setCharacterEncoding은 반드시,
			//req.getXXXX() 메소드로 전송파라미터의 값을 획득하기 전에 만드시 먼저 수행해야 한다 
//			req.setCharacterEncoding("UTF-8");
			
			
			String[] sports = req.getParameterValues("sports");
			String sex = req.getParameter("sex");
			
			
			//--------------------------------------
			//2.응답문서의 생성 및 웹브라우저로 전송 
			//--------------------------------------
			out.print("<html><body>");
			
			for(String sport : sports) {
				out.print("종아하는 운동 : " + sport + "<br>");
			}//enhanced for
			
			out.print("성별 : " + sex + "<br>");
			out.print("</body></html>");
			
			out.flush();
		} catch(Exception e) {
			throw new ServletException(e);
			
		}//try-with-resources
		
		
	}//service

}//end class
