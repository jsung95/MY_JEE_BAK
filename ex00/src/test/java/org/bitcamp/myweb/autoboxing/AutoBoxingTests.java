package org.bitcamp.myweb.autoboxing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class AutoBoxingTests {
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
	}//setup
	
	@Test
	public void testAutoBoxing1() {
		log.debug("testAutoBoxing1() invoked.");
		
		Object obj = new Object();	//지역변수 정의
		log.info("1. obj : " + obj.getClass().getName());
		
		obj = 100;			//ok
		log.info("2. obj : " + obj.getClass().getName());
		
		obj = 3.141592;		//ok
		log.info("3. obj : " + obj.getClass().getName());
		
		obj = true;			//ok
		log.info("4. obj : " + obj.getClass().getName());
		
		
	}//testXXX
	
	@Test
	public void testAutoBoxing2() {
		log.debug("testAutoBoxing2() invoked.");
		
		Integer integer = 100;			// Auto-Boxing
		log.info("1. integer : " + integer);
		log.info("\t+ type : " + integer.getClass().getName());

		int number = integer;			// Auto-Unboxing
		log.info("2. integer : " + integer);
		
		
		
		Double doubleVar = 3.141592;	// Auto-Boxing
		double doubleValue = doubleVar; // Auto-unboxing
		
		
		Byte byteVar = 10;
		byte byteValue = byteVar;
		
		
		Short shortVar = 100;
		short shortValue = shortVar;
		
		
		Character charVar = 65;
		char charValue = charVar;
		
		
		Long longVar = 100000000l;
		long longValue = longVar;
		
		
		Boolean boolVar = true;
		boolean boolValue = boolVar;
		
		
	}//testAutoBoxing2
	
	
	@Test
	public void testAutoUnBoxing() {
		
	}
	
	
	@After
	public void tearDown() {
		log.debug("\t:tearDown() invoked.");
	}//tearDown
	
	
	
} // end class
