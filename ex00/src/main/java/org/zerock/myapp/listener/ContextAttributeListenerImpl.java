package org.zerock.myapp.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

//@WebListener
public class ContextAttributeListenerImpl
	implements ServletContextAttributeListener {

    
	@Override
	public void attributeAdded(ServletContextAttributeEvent event)  { 
         log.debug("attributeAdded(event) invoked.");
         
         log.info("\t+ event: " + event);
         
         log.info("\t+ ---------------------");
         log.info("\t+ source: " + event.getSource());
         log.info("\t+ name: " + event.getName());
         log.info("\t+ value: " + event.getValue());
         
    } // attributeAdded

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event)  { 
        log.debug("attributeRemoved(event) invoked."); 

        log.info("\t+ event: " + event);
        
        log.info("\t+ ---------------------");
        log.info("\t+ source: " + event.getSource());
        log.info("\t+ name: " + event.getName());
        log.info("\t+ value: " + event.getValue());
    } // attributeRemoved

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event)  { 
        log.debug("attributeReplaced(event) invoked."); 

        log.info("\t+ event: " + event);
        
        log.info("\t+ ---------------------");
        log.info("\t+ source: " + event.getSource());
        log.info("\t+ name: " + event.getName());
        log.info("\t+ value: " + event.getValue());
    } // attributeReplaced
	
} // end class
