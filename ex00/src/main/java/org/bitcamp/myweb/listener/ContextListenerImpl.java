package org.bitcamp.myweb.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebListener
public class ContextListenerImpl implements ServletContextListener {

	@Override
    public void contextDestroyed(ServletContextEvent event)  { 
         log.debug("웹어플리케이션제거");
         
         log.info("\t+ event : " + event);
         
    }//contextDestroyed


	@Override
    public void contextInitialized(ServletContextEvent event)  { 
         log.debug("웹어플리케이션초기화 ");
         
         log.info("\t+ event : " + event);
         
    }//contextInitialized
	
}
