package org.zerock.myapp.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.myapp.domain.UserVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
//역할:로그인/로그아웃 처리에 특화 된 인터셉터
@Component
public class LoginInterceptor 
   implements HandlerInterceptor {
   
   public static final String loginKey ="__LOGIN__";
   public static final String rememberMeKey ="__REMEMBER_ME__";
   
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
         throws Exception {
      log.debug("==================================================");
      log.debug("1. preHandle(request, response, {}) invoked.", handler);
      log.debug("==================================================");
      

      //사용자가 로그인 성공한 상태인지 체크 ---- XX : 여기서하면 안된다!!
      //다른 Interceptor에서 해야만 하는일임
      HttpSession session = request.getSession();
      UserVO user = (UserVO) session.getAttribute(LoginInterceptor.loginKey);
      if(user != null) {   //로그인 성공했다면..
         session.removeAttribute(LoginInterceptor.loginKey);
         log.info("\t+ 기존에 바인딩된 UserVO 객체를 Session Scope에서 삭제완료.");
      }//if

      return true;

   }//preHandle

   //뒤 컨트롤러 메소드가 호출이 완료 된 후 그리고 VIEW 가 호출되기전에 수행.
   //단 컨트롤러 메소드 수행에서 예외가 발생되지 말아야함
   //컨트롤러 메솓 ㅡ수행에서 예외가 발생되면 이 메소드는 수행되지 않음.
   
   
   
   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
         ModelAndView modelAndView) throws Exception {

      log.debug("==================================================");
      log.debug("2. postHandle(request, response, {}, {}) invoked.", handler, modelAndView);
      log.debug("==================================================");
      
      //Session Scope에 UserVO 객체를 바인딩하는 작업
      HttpSession session = request.getSession();
      
      UserVO user = (UserVO)modelAndView.getModelMap().get(loginKey);
      if(user != null) {   //로그인 성공했다면
         
  		//=============================================//
  		// 1. Session Scope에 로그인 정보로, UserVO 객체를 바인딩  
  		//=============================================//
         session.setAttribute(LoginInterceptor.loginKey, user);
         log.info("\t+ 기존에 바인딩된 UserVO 객체를 Session Scope에서 바인딩 완료.");
         
 		//=============================================//
 		// 2. 원래 사용자의 Request URI를 복구하여, 이동시킴  
 		//=============================================//
         String originalRequestURI = (String)session.getAttribute(AuthInterceptor.requestURIKey);
         String originalQueryString = (String)session.getAttribute(AuthInterceptor.queryStringKey);
         
         if(originalRequestURI != null) { // 원래의 요청URI가 없었다면 .. (**GET방식만 고려**)
        	 response.sendRedirect(originalRequestURI + 
        			 				(originalQueryString != null && !"".equals(originalQueryString) ? "?"+originalQueryString : "") 
        	 );
        	 
        	 log.info("\t+ 2. 원래의 요청 URI({})로 강제이동시킴.", originalRequestURI);
        	 
         } else { //처음부터 사용자가 로그인 창에서 로그인 수행했다면 ...
        	 
        	 log.info("\t + 2. 사용자가 로그인부터 수행해서 들어옴.");
        	 
         } //if-else
         
         
        //=============================================//
  		// 3. REMEBER ME 기능 처리   
  		//=============================================//
         // 조건 - 1) 로그인 성공 (이미 이 조건은 만족 )
         // 	- 2) REMEMBER ME 옵션을 체크
         
         //전송파라미터 rememberMe를 얻어온다 
         String rememberMe = request.getParameter("rememberMe");
         
         if(rememberMe != null) { // Remember Me 기능처리 해야 함 
        	 
        	 log.info("\t+ 3. Remember Me 기능 처리...");
        	 // (1) 새로운 Cookie를 사용해야함.
        	 // (2) Response문서의 헤더에 저장해서 응답으로 웹브라우저에 전송
        	 // (3) 웹브라우저는 응답으로 받는 쿠키들은 모두 파일로 보관
        	 // (4) 다시 동일 웹사이트로 새로운 Request 보낼 때마다, 해당 웹사이트와 연관된
        	 //     쿠키를 Request의 헤더에 담아서 항상 전송하게 되어있다.!!!!(****)
        	 
        	 String sessionId = session.getId(); //웹브라우저에 할당된 이름(= 세션 ID)
        	 Cookie rememberMeCookie = new Cookie(LoginInterceptor.rememberMeKey, sessionId);
        	 
        	 //우리가 만든 데이터 조각인 쿠키에 대한 설정을 수행
			 rememberMeCookie.setMaxAge(60*60*24*7);//초단위 쿠키의 유효기간 설정 (7일)
			 rememberMeCookie.setPath("/");
			 
			 response.addCookie(rememberMeCookie);

			 log.info("\t\t+ rememberMeCookie: " + rememberMeCookie);
			 log.info("\t\t+ 응답문서 헤더에 rememberMe 쿠키설정 완료.");
        	 
        	 // 이 기능은 쿠키(Cookie)를 사용해야 함.
        	 	// 쿠키란 -> 아주작은 데이터 조각(문자열)
        	 	// 이 쿠키는 Request와 Response 메시지의 Header에 보관되어서, 전송/수신 됨.
        	 	// 쿠키는 웹브라우저의 PC에 파일형태로 저장(보안위협)
        	    // (세션 - WAS의 메모리에 저장)
        	 	// 웹브라우저는 수신한 쿠키를 파일형태로 보관(3번)하다가 새로운 요청을 동일 웹사이트에
        	 		//보낼때, Request 메시지의 Header에 담아서 보냄 
         }//if
         
         
         
      } else { //로그인에 실패했다면 ..
    	  // postHandle 메소드가 수행되는 지점과 싯점을 잘 기억하셔야 합니다 
    	  response.sendRedirect("/user/login");
    	  
    	  log.info("\t+ 1. 로그인 실패 - 다시 로그인 창으로 되돌림.");
      }// if-else
      
   }//postHandle

   
   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
         throws Exception {

      log.debug("==================================================");
      log.debug("3. afterCompletion(request, response, {}, {}) invoked.", handler, e);
      log.debug("==================================================");
      
      //컨트롤러에 대한 예외처리
      //(1) DB에 발생 예외의 로그를 남김
      //(2) 에러페이지로 이동
      
   }//afterCompletion

   
}//end class