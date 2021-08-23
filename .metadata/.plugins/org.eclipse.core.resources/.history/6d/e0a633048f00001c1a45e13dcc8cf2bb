package org.zerock.myapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.Ticket;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/sample3/")
@RestController
public class RequestBodyController {
	
	
	//======================================//
	// @RequestBody annotation
	//======================================//
	//Request Body에 들어있는 JSON 문서를 받아서,
	//다시 Ticket 객체로 De-serialize한 후에,
	//응답으로 다시 JSON으로 요청자한테 되돌려 주자! 
	@PostMapping(path="ticket", produces=MediaType.APPLICATION_JSON_VALUE)
	public Ticket convertToTicket(@RequestBody Ticket ticket) {
		log.debug("convertToTicket() invoked.");
		log.info("\t+ ticket : {}", ticket);
		return ticket;
	}
}
