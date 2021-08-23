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

@WebServlet("/Response")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		log.debug("service(req, res) invoked.");
		
		//-----------------------------------//
		// 응답문서 만들때의 코드 				 //
		//-----------------------------------//
		
		
		// MIME 타입 설정 
//		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		//JAVA I/O
		PrintWriter out = res.getWriter();
		
		//html작성 및 출력 
		out.print("<html><body><h1>");
		out.print("ResponseServlet 요청성공");
		out.print("</h1></body></html>");
		
		//-----------------------------------//
		// 자원해제 코드 ----> 메모리 누수방지 		 //
		//-----------------------------------//
		out.flush();
		out.close();
		
		
	}//service

}//end class
