package org.zerock.myapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/sample4/")
@RestController
public class PathVariableController {
	//HTTP method에 따라, 요청내용이 바뀌는 것임 :
	// 1) GET : READ
	// 2) PUT: CREATE
	// 3) DELETE : DELETE
	// 4) POST: UPDATE
	
	//SAMPLE URI : /sample4/product/book/777
	
	@GetMapping(
			path="product/{category}/{productId}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public String[] getPathVariables(
			@PathVariable("category") String category,
			@PathVariable("productId")Integer productId	) {
		
		log.debug("getPathVariables({}, {}) invoked.", category, productId);
		
		String[] product = { "category : " + category, "productId : " + productId};
		
		return product;
	}
	
	
}
