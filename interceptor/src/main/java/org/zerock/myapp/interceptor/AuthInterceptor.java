package org.zerock.myapp.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.service.UserService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Component
public class AuthInterceptor 
	implements HandlerInterceptor {
	
	public static final String loginKey = "__LOGIN__";
	public static final String requestURIKey = "__REQUEST_URI__";
	public static final String queryStringKey = "__QUERYSTRING__";
	
	
	@Setter(onMethod_ = @Autowired)
	private UserService service;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("===============================================================");
		log.debug("1. preHandle(request, response, {}) invoked.", handler.getClass().getName());
		log.debug("===============================================================");
		
		
		//사용자가 로그인 성공한 상태인지 체크를 preHandle에서 하면 안된다!!
		//다른 Intercepter에서 해야만한다
		
		//이미 로그인에 성공한 유저인지 아닌지를 판단하겠다. ===> 인증(Authentication)
		HttpSession session = request.getSession();
		UserVO user= (UserVO) session.getAttribute(AuthInterceptor.loginKey);
		
		
		if(user != null) { //이전에 이미 로그인한 사용자이구나!
			log.info("Authnticated....OK!");
			
			return true;
		} else { //이전에 아직 로그인한 사용자가 아니구나 !  
			
	  		//=============================================//
			// 1. Original Request URI + 전송파라미터도 함께 저장(Session Scope)  
	  		//=============================================//
			String originalRequestURI = request.getRequestURI(); //원래의 요청 URI 획득
			String originalQueryString = request.getQueryString(); //원래의 query string을 획득
			
			session.setAttribute(requestURIKey, originalRequestURI);
			session.setAttribute(queryStringKey, originalQueryString);
			
			
			//=============================================//
	  		// 2. To authenticate a user through Remember-Me feature.  
	  		//=============================================//
			Cookie rememberMeCookie = WebUtils.getCookie(request, LoginInterceptor.rememberMeKey);
			
			if(rememberMeCookie != null) {
				//이번 요청을 보내기 이전에, 이미 로그인에 성공했ㅎ고, Remember-Me 옵션도 on 시킨 사용자이다!
				String rememberMe = rememberMeCookie.getValue();
				log.info("\t+ rememberMe: {}", rememberMe);
				
				//현재의 Session ID로 유지되는 Session Scope에 UserVO 객체를 생성해서 바인딩 등록 
				// UserService 객체의 findeUserByRememberMe() 메소드 이전 Session ID로 
				// 사용자 정보를 select해서, UserVO 객체를 얻고, 이 UserVO 객체를, SessionScope에
				// 바인딩해서, 마치 로그인 창에서 로그인한 것과 동일한 결과를 생성해낸다.
				
				user = this.service.findUserByRememberMe(rememberMe);
				if(user != null) {
					//마치 사용자가 정상로그인한 것처럼, Session Scope에 UserVO(신분증)을 넣어주자.
					session.setAttribute("loginKey", user);
					
					return true;
				}//if : rememberMeCookie에 해당하는 사용자를 찾아 냈을 때 
				
			}//if : rememberMeCookie가 Request문서에 들어왔을 때
			
			
			
	  		//=============================================//
	  		// N. To Redirect To login form (request uri : /user/login  
	  		//=============================================//
			response.sendRedirect("/user/login");
			log.info("Redirected to /user/login");
			
			return false; //현재 요청이 컨트롤러로 가지 못한다.
		}//if-else
		
	}//preHandle

	// 뒤의 컨트롤러 메소드 호출이 완료된 후, 그리고 View가 호출되기 전에 수행된다.
	// 단, 컨트롤러 메소드 수행에서 예외가 발생되지  말아야 한다.
	// 컨트롤러 메소드 수행에서 예외가 발생되면, 이 메소드는 수행되지 않는다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		log.debug("===============================================================");
		log.debug("2. postHandle(request, response, {}, {}) invoked.", handler.getClass().getName(), modelAndView);
		log.debug("===============================================================");
		
		//Session Scope에 UserVO 객체를 바인딩하는 작업
		HttpSession session = request.getSession();
		UserVO user = (UserVO)modelAndView.getModelMap().get(loginKey);
		if(user != null) {
			session.setAttribute(AuthInterceptor.loginKey, user);
			log.info("\t+ UserVO객체를 Session Scope에 바인딩 완료!");
		}
		
	}//postHandle

	
	// 뒤의 컨트롤러 메소드 수행시, 예외의 발생여부와 상관없이ㅡ View까지 호출된 후에 수행된다 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		log.debug("===============================================================");
		log.debug("3. afterCompletion(request, response, {}, {}) invoked.", handler.getClass().getName(), ex);
		log.debug("===============================================================");
		
		// 컨트롤러에 대한 예외 처리
		//    -> DB에 대한 로그를 남긴다 
		//    -> 에러페이지로 이동
		
		
	}//afterCompletion

	
}//end class
