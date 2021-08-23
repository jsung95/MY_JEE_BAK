package org.bitcamp.myweb.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2
//@WebListener
public class HttpSessionOverallListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionIdListener {

	@Override
    public void sessionCreated(HttpSessionEvent event)  {
		log.debug("=======================================");
    	log.debug("sessionCreated(event) invoked.");
		log.debug("=======================================");
		
		log.info("\t+ session : " + event.getSession().getId());
    	
    }//sessionCreated
    
	
	@Override
    public void sessionDestroyed(HttpSessionEvent event)  {
		log.debug("=======================================");
    	log.debug("sessionDestroyed(event) invoked.");
		log.debug("=======================================");
		
		log.info("\t+ session : " + event.getSession().getId());
    }//sessionDestroyed
	
	
	
	
	
	@Override
    public void sessionIdChanged(HttpSessionEvent event, String oldSessionId)  {
		log.debug("=======================================");
    	log.debug("sessionIdChanged(event) invoked.");
		log.debug("=======================================");
		
		log.info(String.format("\t+ AS-IS : %s, TO-BE : %s", oldSessionId, event.getSession().getId()));
    }//sessionIdChanged
    
	
	
	
	
	
	@Override
    public void attributeAdded(HttpSessionBindingEvent event)  {
		log.debug("=======================================");
    	log.debug("attributeAdded(event) invoked.");
		log.debug("=======================================");
	
		String formattedLog = String.format("\t+ name : %s, vlaue : %s, session : %s", 
										event.getName(), event.getValue(), event.getSession().getId());
		log.info(formattedLog);
    }//attributeAdded
	
	
	

	


	
	@Override
    public void attributeRemoved(HttpSessionBindingEvent event)  {
		log.debug("=======================================");
    	log.debug("attributeRemoved(event) invoked.");
		log.debug("=======================================");
		
		String formattedLog = String.format("\t+ name : %s, vlaue : %s, session : %s", 
				event.getName(), event.getValue(), event.getSession().getId());
		
		log.info(formattedLog);
    }//attributeRemoved

	
	@Override
    public void attributeReplaced(HttpSessionBindingEvent event)  {
		log.debug("=======================================");
    	log.debug("attributeReplaced(event) invoked.");
		log.debug("=======================================");

		String formattedLog = String.format("\t+ name : %s, vlaue : %s, session : %s", 
				event.getName(), event.getValue(), event.getSession().getId());
		
		log.info(formattedLog);
    }//attributeReplaced
	
    
}//end class
