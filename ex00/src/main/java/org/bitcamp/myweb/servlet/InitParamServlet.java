package org.bitcamp.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javadoc.ThrowsTag;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

//@WebServlet(
//		urlPatterns = { "/InitParam" }, 
//		initParams = { 
//				@WebInitParam(name = "userid", value = "useridValue"), 
//				@WebInitParam(name = "dirPath", value = "dirPathValue")
//		})
public class InitParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String dirPath;
	private String userid;
    
	
	@PostConstruct
	public void postConstruct() {
//		this.dirPath = getInitParameter("dirPath");
//		this.userid = getInitParameter("userid");
//		
//		log.info("/y dirPath : " + this.dirPath);
//		log.info("/y userid : " + this.userid);
		
	}//postConstruct
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.debug("init(config) invoked.");
		
		//web.xml 에 등록한 이 서블릿의 초기화 파라미터를 얻어서, 필드에 저장하는 코드
		this.dirPath = config.getInitParameter("dirPath");
		this.userid = config.getInitParameter("userid");
		
		log.info("/y dirPath : " + this.dirPath);
		log.info("/y userid : " + this.userid);
	}//init

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		res.setContentType("text/http; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		try(out;) {
//			req.setCharacterEncoding("UTF-8");
			
			out.print("<html><body>");
			out.print("디렉터리경로: " + dirPath +"<br>");
			out.print("아이디 값: " + userid +"<br>");
			out.print("</body></html>");
			
			out.flush();
		} catch (Exception e) {
			throw new ServletException(e);
		}//try-catch-resources
		
	}//service

}//end class
