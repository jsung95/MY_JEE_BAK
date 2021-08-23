package org.zerock.myapp.listener;

import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebListener
public class ContextListenerImpl 
	implements ServletContextListener {


    // WAS를 따라, 현재 웹 어플리케이션이 초기화 될 때 : WAS 올릴 때
    @Override
    public void contextInitialized(ServletContextEvent sce) {  
    	log.debug("contextInitialized(sce) invoked.");
    	
    	ServletContext sc = sce.getServletContext();
    	
    	Objects.requireNonNull(sc);
    	log.info("\t+ sc: " + sc);
    	
    	//------------------------------------//
    	// Application scope 에 속성 바인딩
    	//------------------------------------//
    	sc.setAttribute("SHARED", 1000);
    	
         
    } // contextInitialized

	
	// 현재 웹어플리케이션이 WAS를 따라 같이, 파괴될 때: WAS 내릴때
    @Override
    public void contextDestroyed(ServletContextEvent sce) { 
    	log.debug("contextDestroyed(sce) invoked.");
    	
    	ServletContext sc = sce.getServletContext();
    	
    	Objects.requireNonNull(sc);
    	log.info("\t+ sc: " + sc);
    	
    	//------------------------------------//
    	// Application scope 에 속성 언바인딩
    	//------------------------------------//
    	sc.removeAttribute("SHARED");
         
    } // contextDestroyed
	
} // end class
