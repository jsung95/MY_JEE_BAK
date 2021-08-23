package org.zerock.myapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.myapp.domain.SampleDTO;

import com.google.gson.Gson;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/return/")
@Controller
public class ReturnTypesController {

	
	//--------------------------------------------
	// 1. void return type : Request URI 가 곧 View의 이름이 된다.
	//--------------------------------------------
	@GetMapping("void")
	public void returnVoid() {
		log.debug("returnVoid() invoked.");
		
		// => /WEB-INF/views/ + return/void + .jsp
		
	}//returnVoid
	
	
	
	
	//--------------------------------------------
	// 2. String return type 
	//--------------------------------------------

	
	//--------------------------------------------
	// 2-1. String return type : "redirect:" Keyword
	//--------------------------------------------
	@PostMapping("/redirect")
	public String returnRedirect() {
		log.debug("returnRedirect() invoked.");
		
		return "redirect:/return/void";
		
		
	}//returnRedirect
	
	
	//--------------------------------------------
	// 2-2. String return type : "forward:" Keyword
	//--------------------------------------------
	@GetMapping("/forward")
	public String returnForward() {
		log.debug("returnForward() invoked.");
		
		
//		return "forward:/WEB-INF/views/return/void.jsp";
//      return "forward:/return/void";
//      return "forward:void";
		
		//Redirection은 완전히 다른 사이트(즉, 다른 서버)로 요청을 다시보내는게가능했지만,
		//Forwarding은 완전히 다른 사이트로 나머지 처리를 위임시킬 수 없다! 
      return "forward:http://localhost:8007/";
		
	}//returnForward
	
	
	//-----------------------------//
    // 3. Object return type Using //
    //----------------------------//
	// =====================================
	// To return JSON format document
	// =====================================
	@PostMapping("/ResponseBody")
	public @ResponseBody SampleDTO returnResponseBody(@NonNull @RequestParam("name") String NAME,
													  @NonNull @RequestParam("age") Integer AGE) {
		log.debug("returnResponseBody() invoked.");
		
		SampleDTO dto = new SampleDTO();
		dto.setName(NAME);
		dto.setAge(AGE);
		
		log.info("\t+ dto : " + dto);
		
		return dto;
	}
	
	
	// To return JSON format document including User-defined HEADER and BODY, HTTP status
	@PostMapping("/ResponseEntity")
	public ResponseEntity<String> returnResponseEntity() {
		log.debug("returnResponseEntity() invoked.");
		
//		String json = "{ \"name\": \"LEE\", \"age\": 23 }";
		
		
		//USING Gson library
		SampleDTO dto = new SampleDTO();
		dto.setName("LEE");
		dto.setAge(23);
		
		Gson gson = new Gson();
		String json = gson.toJson(dto);
		
		log.info("\t+ json : " + json);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf8");
		
		return new ResponseEntity<>(json, headers, HttpStatus.OK); 
	}
	
	
}//end class
