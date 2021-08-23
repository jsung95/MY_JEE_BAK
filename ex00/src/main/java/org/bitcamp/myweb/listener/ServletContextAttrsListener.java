package org.bitcamp.myweb.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
//@WebListener
public class ServletContextAttrsListener 
	implements ServletContextAttributeListener {

	
	@Override
    public void attributeAdded(ServletContextAttributeEvent event)  {	//Callback 
		log.debug("================================"); 
		log.debug("attributeAdded(event) invoked.");
        log.debug("================================");
         
         String formattedString = 
        		 String.format("\t + name: %s, value: %s, source: %s", event.getName(), event.getValue(), event.getSource());
         
         log.info(formattedString);
    }//attributeAdded

	
	
	@Override
    public void attributeRemoved(ServletContextAttributeEvent event)  {  //Callback
		log.debug("================================");
		log.debug("attributeRemoved(event) invoked.");
		log.debug("================================");
		
		 String formattedString = 
        		 String.format("\t + name: %s, value: %s, source: %s", event.getName(), event.getValue(), event.getSource());
         
         log.info(formattedString);
    }//attributeRemoved

	
	
	@Override
    public void attributeReplaced(ServletContextAttributeEvent event)  {  //Callback
		log.debug("================================");
		log.debug("attributeReplaced(event) invoked.");
		log.debug("================================");
		
		 String formattedString = 
        		 String.format("\t + name: %s, value: %s, source: %s", event.getName(), event.getValue(), event.getSource());
         
         log.info(formattedString);
    }//attribueReplaced
	
	
}//end class 
