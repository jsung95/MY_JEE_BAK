package org.bitcamp.myweb.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2
//@WebListener
public class HttpRequestOverallListener implements ServletRequestListener, ServletRequestAttributeListener {

	@Override
	public void requestInitialized(ServletRequestEvent event)  {
		log.debug("=======================================");
    	log.debug("requestInitialized() invoked.");
    	log.debug("=======================================");
    	
    }//requestInitialized
	
	@Override
    public void requestDestroyed(ServletRequestEvent event)  {
    	log.debug("=======================================");
    	log.debug("requestDestroyed() invoked.");
    	log.debug("=======================================");
    	
    }//requestDestroyed
    
    
    
	@Override
    public void attributeAdded(ServletRequestAttributeEvent event)  {
    	log.debug("=======================================");
    	log.debug("attributeAdded() invoked.");
    	log.debug("=======================================");
    	
    	log.info(String.format("\t+ name : %s, value : %s", 
    					event.getName(), event.getValue()));
    	
    }//attributeAdded

	@Override
    public void attributeRemoved(ServletRequestAttributeEvent event)  {
    	log.debug("=======================================");
    	log.debug("attributeRemoved() invoked.");
    	log.debug("=======================================");
    	
    	log.info(String.format("\t+ name : %s, value : %s", 
				event.getName(), event.getValue()));

    }//attributeRemoved

	@Override
    public void attributeReplaced(ServletRequestAttributeEvent event)  {
    	log.debug("=======================================");
    	log.debug("attributeReplaced() invoked.");
    	log.debug("=======================================");
    	
    	log.info(String.format("\t+ name : %s, value : %s", 
				event.getName(), event.getValue()));

    }//attributeReplaced
	
    
}//end class
