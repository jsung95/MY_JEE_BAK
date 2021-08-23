package org.bitcamp.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@NoArgsConstructor
@Log4j

//@WebServlet("/Hello4")
public class HelloServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		out.print("hello 4 ");
	}

}
