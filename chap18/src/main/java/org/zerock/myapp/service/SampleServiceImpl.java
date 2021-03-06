package org.zerock.myapp.service;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

//핵심로직(=비지니스로직)을 수행하는 Target 객체를 생성 
@Service
public class SampleServiceImpl implements SampleService {

	
	// AOP : 핵심로직을 수행하는 Target 객체의 JoinPoint
	@Override
	public Integer doAdd(String s1, String s2) throws Exception {
		
		log.debug("doAdd({}, {}) invoked.", s1, s2);
		
		return Integer.parseInt(s1) + Integer.parseInt(s2);
	}//doAdd

	
	// AOP : 핵심로직을 수행하는 Target 객체의 JoinPoint
	@Override
	public String method2(String name, int age) throws Exception {
		log.debug("\t+ method2({}, {}) invoked.", name, age);

		
		return name + age;
	}//method2

}//end class
