package org.zerock.myapp.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

// 1. JUnit test framework를 이용한 테스트 클래스는 아래와 같이 작성한다.
//    가. 기본 생성자를 반드시 가져야한다.
//    나. 실제 테스트를 수행하는 메소드는 반드시 testXXX( 형태로 메소드 이름을 가져야 한다.
//    다. JUnit이 제공하는 @Before, @Test, @After 3개 어노테이션을 이용하여,
//       - @Before : 테스트 수행 전 사전작업 메소드의 구현에 사용
//		 - @Test : 실제 테스트 로직을 구현한 testXXX() 메소드 구현에 사용
//		 - @After : 테스트 수행 전, 사후작업 메소드 구현에 사용

@Log4j
@NoArgsConstructor

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		
})
public class DIBySetterTests {
	
	@Autowired
	private Restaurant restaurant;
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		//필드에 의존성 주입이 성공적으로 되었는지 확인1.
		assertNotNull(this.restaurant); 	// JUnit이 제공하는 메소드, 검증하는 메소드임. 
											// Null인지 아닌지를 체크 
											// Null이 아니라면 정상적으로 의존성 주입이 되었다는 의미.
		
		//필드에 의존성 주입이 성공적으로 되었는지 확인2.
//		Objects.requireNonNull(this.restaurant);	// Objects 헬퍼 클래스에 있는 
		
		//필드에 의존성 주입이 성공적으로 되었는지 확인 방법3.
//		assert this.restaurant != null;
		
		log.info("\t+ restaurant : " + this.restaurant);
	}
	
	@Test
	public void testDI() {
		log.debug("testDI() invoked.");
		
		Chef chef = this.restaurant.getChef();
		log.info("\t+ chef : " + chef);
		
//		Chef chef = new Chef();
//		Restaurant rest = new Restaurant(chef);
		
		
	}
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
	}
	
} // end class
