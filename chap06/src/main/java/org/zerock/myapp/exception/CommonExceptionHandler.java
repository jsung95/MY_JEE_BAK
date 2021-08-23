package org.zerock.myapp.exception;

import java.net.BindException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j2;

//@ControllerAdvice(
//		basePackages = { "org.zerock.myapp.controller" }
//)
//@ControllerAdvice("org.zerock.myapp.controller")
@ControllerAdvice // "모든 패키지에 소속된 컨트롤러에서 발생하는 예외를 담당하는 Exception 클래스이다." 라는 의미

@Log4j2
public class CommonExceptionHandler {

	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler( { NoHandlerFoundException.class } )
	public String handleNoHandlerFoundException(Exception e, Model model) {
		log.debug("handleNoHandlerFoundException(e, model) invoked.");
		
		
		log.error("1. Exception Type : " + e.getClass().getName());
		log.error("2. Exception Messgae : " + e.getMessage());
		
		model.addAttribute("exception", e);
		
		return "errorPage";
	}
	
	@ExceptionHandler(BindException.class)
	public String handleBindException(Exception e, Model model) {
		log.debug("handleBindException(e, model) invoked.");
		
		
		log.error("1. Exception Type : " + e.getClass().getName());
		log.error("2. Exception Messgae : " + e.getMessage());
		
		model.addAttribute("exception", e);
		
		return "errorPage";
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public String handleIllegalStateException(Exception e, Model model) {
		log.debug("handleIllegalStateException(e, model) invoked.");
		
		
		log.error("1. Exception Type : " + e.getClass().getName());
		log.error("2. Exception Messgae : " + e.getMessage());
		
		model.addAttribute("exception", e);
		
		return "errorPage";
	}
	
	
}//end class
