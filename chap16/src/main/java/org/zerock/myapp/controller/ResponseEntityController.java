package org.zerock.myapp.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.SampleVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RestController
@RequestMapping("/sample2/")
public class ResponseEntityController {

	
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	
	
	
	@GetMapping(
			path="check", 
			params= {"height", "weight"}, 
			produces=MediaType.APPLICATION_JSON_VALUE
		)
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		log.debug("check({}, {}) invoked.", height, weight);
		
		ResponseEntity<SampleVO> result = null;
		BodyBuilder bodyBuilder = null;
		
		if( height < 50 ) { //성인 기준 키가 50 보다 작게 들어오면..
			bodyBuilder = ResponseEntity.status(HttpStatus.BAD_REQUEST);
		} else {
			bodyBuilder = ResponseEntity.status(HttpStatus.OK);
		}
		
		SampleVO sample = new SampleVO(1, "성", "이름");
		result = bodyBuilder.<SampleVO>body(sample);
		
		return result;
	}
	
	
}
