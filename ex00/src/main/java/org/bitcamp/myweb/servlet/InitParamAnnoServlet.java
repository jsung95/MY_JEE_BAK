package org.bitcamp.myweb.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2
@WebServlet(
		urlPatterns = { "/InitParamAnno" }, 
		initParams = { 
				@WebInitParam(name = "name1", value = "value1"), 
				@WebInitParam(name = "name2", value = "value2")
		})
public class InitParamAnnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       

	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		log.debug("service(req, res) invoked.");
		
		log.info(this.getInitParameter("name1"));
		log.info(this.getInitParameter("name2"));
		
	}//service





}//end class
