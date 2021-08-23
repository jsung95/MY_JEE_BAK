package org.bitcamp.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@WebServlet("/ContextParam")
public class ContextParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String name1;
	private String name2;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.debug("init(config) invoked.");
		
		ServletContext ctx = config.getServletContext();
		log.info("\t+ ctx : " + ctx);
		
		this.name1 = ctx.getInitParameter("name1");
		this.name2 = ctx.getInitParameter("name2");

		//----------------------------------------------------
		// ServletContext 객체 --> 곧 공유데이터를 저장할  수 있는 Application Scope을 제공한다.
		//-----------------------------------------------------
//		ctx.setAttribute("공유데이터의 이름", "공유할 값");
		ctx.setAttribute("name", "Yoseph"); //매개값(String, Object)
		ctx.setAttribute("integer", 23);
		ctx.setAttribute("boolean", true);
		ctx.setAttribute("double", 3.141592);
		
		
	}//init
	
	
	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		log.debug("service(req, res) invoked.");
		
		log.info("\t+ this : " + this);
		
		try {
			//------------------------------------------
			// init 메소드에서 파라미터 값을 얻어오기 떄문에 
			// service 메소드에서 파라미터 값을 얻어올 필요가 없다!
			// 따라서 주석처리함 !
			//-------------------------------------------
			
//			ServletContext ctx = this.getServletContext();
//			log.info("\t+ ctx : " + ctx);
//			
//			//컨텍스트 파라미터 얻기 --> 반드시 this 키워드로 '소속'을 밝히고 값을 얻어올 것!!
//			String name1 = ctx.getInitParameter("name1");
//			String name2 = ctx.getInitParameter("name2");
			
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			try(out;){
				out.print("<html><body>");
				out.print("값1 : " + name1 + "<br>");
				out.print("값1 : " + name2 + "<br>");
				out.print("</body></html>");
				
				out.flush();
			}//try-with-resouces
			
		} catch(Exception e) {
			throw new ServletException(e);
		}//try-catch
		
	}//service

}//end class
