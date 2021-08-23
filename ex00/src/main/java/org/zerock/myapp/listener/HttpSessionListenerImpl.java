package org.zerock.myapp.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    

	@Override
    public void sessionCreated(HttpSessionEvent event)  {
		log.debug("sessionCreated(event) invoked.");
		
		log.info("--------------------------");
		
		HttpSession session = event.getSession();
		
		log.info("\t+ session: "+session);
		log.info("\t+ session id: " + session.getId());
		
    } // sessionCreated


	@Override
    public void sessionDestroyed(HttpSessionEvent event)  { 
		log.debug("sessionDestroyed(event) invoked.");
		
		log.info("--------------------------");
		
		HttpSession session = event.getSession();
		
		log.info("\t+ session: "+session);
		log.info("\t+ session id: " + session.getId());
		
    } // sessionDestroyed
	
} // end class
