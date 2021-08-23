package org.bitcamp.myweb.urlencoding;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


//======================================
// JUnit test FrameWork -  위에서 구동될 테스트 클래스는
// 아래와 같은 규칙을 가지고 작성한다 :
//
//  1) POJO
//  2) 기본 생성자를 반드시 가져야 한다.
//  3) 테스트를 수행할 메소드의 이름은 반드시 testXXXX 형태로 지어야 한다.
//  4) 아래의 3개의 어노테이션을 이용해서 메소드를 선언한다.
//		가. @Before -----> 메소드 이름 : 관레상 setup()으로 한다.		: 테스트 수행 전 처리작업 
//		나. @Test   -----> 메소드 이름 : 반드시 testXXXX()으로 한다.	: 테스트 수행 
//		다. @After  -----> 메소드 이름 : 관례상 tearDown()으로 한다.	: 테스트 수행 후, 사후 처리 
//======================================


// POJO : Plain Old Java Object


@Log4j2
@NoArgsConstructor
public class URLEncodingTests {

	@Before
	public void setup() {					//테스트 전, 전처리 작업 수행 
		log.debug("setup() invoked.");
	}//setup
	
	@Test
	public void testURLEncodeing() 
			throws UnsupportedEncodingException {					//지정된 테스트 수행 
		log.debug("testURLEncodeing() invoked.");
		
		String name = "한글";
//		String encodingResult = URLEncoder.encode(name);	//Deprecated
		String encodingResult = URLEncoder.encode(name, "UTF-8");
		
		log.info(encodingResult);
	}//testURLEncodeing
	
	@Test(timeout=1000)		//이 테스트 메소드가 1초(1000ms)안에 종료되어야 함을 지정할 수 있다!
	public void testURLDecodeing() 
			throws UnsupportedEncodingException {					//지정된 테스트 수행 
		log.debug("testURLDecodeing() invoked.");
		
		String name = "%ED%95%9C%EA%B8%80";
//		String decodingResult = URLDecoder.decode(name);	//Deprecated
		String decodingResult = URLDecoder.decode(name, "UTF-8");
		
		log.info(decodingResult);
	}//testURLDecodeing
	
	@After
	public void tearDown() {				//테스트 후, 사후처리 작업수행 
		log.debug("tearDown() invoked.");
	}//tearDown
	
} // end class
