package org.zerock.myapp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
public class URLEncoderTests {
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
	}
	
	@Test
	public void testURLEncoder() throws UnsupportedEncodingException {
		log.debug("test() invoked.");
		
		// RFC7230, RFC3986표준에 맞게 문자열이 인코딩 되어야 함
		String queryString = "?list[0].name=NAME_1&list[0].age=1&list[1].name=NAME_2&list[1].age=2";
		
		String urlEncodedString = URLEncoder.encode(queryString, "UTF-8");
		
		// list%5B0%5D.name%3DNAME_1%26list%5B0%5D.age%3D1%26list%5B1%5D.name%3DNAME_2%26list%5B1%5D.age%3D2
		
		// 대괄호 ' [ '  ==> %5B0
		// 대괄호 ' ] '  ==> %5D
		// 이퀄 ' = ' ==> %3D
		// 엔퍼센드 ' & ' ==> %26
		
		log.info("\t+ urlEncodedString : " + urlEncodedString);
	}
	
	
	@Test
	public void testURLDecoder() throws UnsupportedEncodingException {
		log.debug("test() invoked.");
		
		String queryString = "?list[0].name=NAME_1&list[0].age=1&list[1].name=NAME_2&list[1].age=2";
		
		String urlEncodedString = URLEncoder.encode(queryString, "UTF-8");
		
		
		log.info("\t+ Encoding : " + urlEncodedString);
		
		String urlDecoding = URLDecoder.decode(urlEncodedString);
		
		log.info("\t+ Decoding : " + urlDecoding);
	}
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
	}
	
	
}
