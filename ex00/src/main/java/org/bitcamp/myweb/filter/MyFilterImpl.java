package org.bitcamp.myweb.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

//아래의 @WebFilter 어노테이션이 있어야, 자동으로 DD(배치설명자, 즉, web.xml)에 필터로 등록이 된다.
//어노테이션 속성값으로는, 필터를 수행할 URL 패턴을 지정한다.
//아래와 같은경우는, '모든 URL에 대하여'의 의미를 갖는다.
//@WebFilter("/*")

// 중요1: 필터 클래스는 규약에 따라, 반드시 Filter 인터페이스를 구현해야 한다.
// 중요2: 필터 클래스의 객체는, Servlet Container가 생성하므로, 기본(=매개변수없는) 생성자를 가져야한다.  
public class MyFilterImpl implements Filter {

	// 1. Filter LifeCycle 메소드 : 필터객체 생성 직후에 호출된다.  
	@Override	
	public void init(FilterConfig fConfig) throws ServletException {
		log.debug("init(fConfig) invoked.");
		
		try {
			
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			//반드시 수행시켜야 할 
		} //try-catch-finally
		
	}//init
	
	
	// 2. Filter LifeCycle 메소드 : 필터객체 파괴 직전에 호출된다. 
	@Override
	public void destroy() {
		log.debug("destory() invoked.");
		
		//필터객체 파괴 직전에 수행해야 할 코드를 구현한다.
		//주로 자원해제 코드를 여기에 구현한다. 
		
	}//destroy

	
	// 3. Filter LifeCycle 메소드 : 매번, 웹브라우저에서 request문서가 올때마다, 호출됩니다.
	//	이 메소드 역시, Filter API 규약에 따라, 실행블록에서 오류 발생시, 반드시 ServletException 또는 IOException 을 던져야(throw)합니다. 
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
		throws IOException, ServletException {
		
		log.debug("doFilter(req, res, chain) invoked.");

		
		try {
			// 1) 요청필터(Request Filter) 로직 구현
			//		- 매개변수로 전달된 Request 객체를 통해, 요청헤더나 바닥에 필수 정보가 들어있는지 확인하거나
			//		- 회원제 사이트일 경우, 로그인 여부를 확인합니다.(Session scope 공유영역을 확인하여, 
			//		  로그인되어있다 라는 정보를 확인하여, 없으면 로그인 창으로 redirection 처리)
			
			// 2) pass the request along the filter chain
			//		- request를 필터체인의 다음 필터로 넘기거나(필터가 여러개 있다면...)
			//		- 이 요청을 처리할 Servlet 으로 요청을 넘긴다.
			//		- (**중요**) 아래의 메소드가 호출되지 않으면, 다음 필터나 서블릿으로 요청이 넘겨지지 않는다.
			req.setCharacterEncoding("UTF-8");
			
			String name = req.getParameter("name");
			log.info("\t + name : " + name);
			
			chain.doFilter(req, res);
			
			// 3) 응답필터(Response Filter) 로직 구현
			//		- 웹브라우저로 나가는 응답문서(response)를 검사하여, 필수헤더정보 누락등을 확인하고
			//		- 누락시, 다른 응답을 만들어 보낼 수도 있습니다.
			
		} catch (Exception e) {
			throw new ServletException(e); 
		} finally {
			// 자원해제 코드
			
		}//try-catch-finally
		
		
		
	}//doFilter
	


}//end class
