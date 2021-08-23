package org.zerock.myapp.sample;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;


//@NoArgsConstructor
@Log4j
//@ToString

@Component("chef")
public class Chef implements InitializingBean, DisposableBean { //POJO
	
	public Chef() {
		log.debug("default constructor invoked.");
	}//default constructor

	
	
	@Override
	public void destroy() throws Exception {
		log.debug("destroy() invoked.");
		
	}//destroy
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
	}//afterPropertiesSet

	
	
} //end class
