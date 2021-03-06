package org.zerock.myapp.controller;

import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.service.UserService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Controller
@RequestMapping("/user/")
public class UserController {
	
	@Setter(onMethod_ = @Autowired)
	private UserService service;
	
	private static final String loginKey = "__LOGIN__";
	
	@PostMapping("loginPost")
	public void loginPost(LoginDTO dto, Model model, HttpSession session) throws Exception {
		log.debug("loginPost() invoked.");
		
		Objects.requireNonNull(this.service);
		//-------------------------------------------------
		// 1. 전송파라미터에 해당되는 사용자가 있는지 확인 
		//-------------------------------------------------
		UserVO user = this.service.login(dto);
		
		if(user != null) { //로그인이 성공했다면 
			model.addAttribute(UserController.loginKey, user);
			
			//-------------------------------------------------
			// 2. Remember-Me 기능을 위한 로직 처리
			// 사용자 테이블에, RememberMeCookie 값과 그 유효기간을 설정
			//-------------------------------------------------
			if(dto.isRememberMe() == true) {
				int maxAge = 1000 * 60 * 60 * 24 * 7; // 7일
				
				String userid = dto.getUserid();
				String rememberMe = session.getId();
				
				Date rememberMeAgeMaxAge = new Date(System.currentTimeMillis() + maxAge);
				
				//사용자테이블에 해당하는 사용자의 rememberMe 관련 2개 컬럼 업데이트 수
				//그런데, Service객체에 업데이트 메소드가 없네요?! 만듭시다! 
				int affectedLines = this.service.modifyUserWithRememberMe(userid, rememberMe, rememberMeAgeMaxAge);
	            log.info("\t+ affectedLines : {} ", affectedLines);
				
			}// if : Remember-Me 옵션이 on일 때 ..
			
			
		}//if : 로그인 성공했다면 ..
		
	}//loginPost
	
//	@ResponseStatus(code=HttpStatus.OK)
	@GetMapping("logout")
	public String logout(HttpSession session) {
		log.debug("logout({}) invoked.", session.getId());
		
		
		//=============================================//
		// 1. 로그아웃을 수행한 사용자 정보를 출력 
		//=============================================//
		UserVO user = (UserVO) session.getAttribute(UserController.loginKey);
		log.info("\t+ 1. user : {}", user);
		
		
		//=============================================//
		// 2. 세션 객체 무효화 
		//=============================================//
		session.invalidate(); //현재의 세션을 무효화 시킴! 즉, 로그아웃 처리. 
		log.debug("\t+ 2. current session destroyed.");
		
		
		//=============================================//
		// 3. 다시 로그인 창으로 이동 
		//=============================================//
		
		
		
		return "redirect:/user/login";
	}
	
}//end class
