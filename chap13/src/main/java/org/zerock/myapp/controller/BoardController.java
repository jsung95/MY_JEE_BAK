package org.zerock.myapp.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.service.BoardService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Controller
@RequestMapping("/board/")
public class BoardController implements InitializingBean {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	
	
	//============================================
	// Request Mapping table설
	//============================================
	// 1) /board/get		GET			void get(bno, model)	  ---> get 화면으로 이동 
	// 2) /board/list		GET			void list(model)		  ---> list 화면으로 이동 
	// 3) /board/modify		POST		modify()
	// 4) /board/register	POST		register(BoardVO board, RedirectAttributes rttrs) ---> redirect:/board/list 
	// 5) /board/remove		POST		String remove(bno, rttrs) ---> redirect:/board/list
	// 6) /board/register	GET			register()
	
	@PostMapping("modify")
	public String modify(BoardVO board, RedirectAttributes rttrs) {
		log.debug("modify({}, {}) invoked.", board, rttrs);
		
		boolean isModified = this.service.modify(board);
		
		if(isModified) {
			rttrs.addAttribute("result", "success");
		}
		
		return "redirect:/board/list";
	}//modify
	
	
	
	
	@PostMapping("register") 
	public String register(BoardVO board, RedirectAttributes rttrs) {
		log.debug("register({}, {}) invoked.", board, rttrs);
		
		//둘다 페이지 이동이 가능하긴하지만.. 
		//Forward를 안쓰고 Redirect를 쓰는 이유는 게시글을 입력했을때 back버튼(뒤로가기)를 수행하면
		//submit 했던 양식이 불러와지게되어서 게시판 도배가되는 불상사가 발생할 수도 있다.
		//따라서 redirection을 해주는게 낫다 .
		
		boolean isRegistered = this.service.register(board);
		
		if(isRegistered) {
			rttrs.addAttribute("result", board.getBno());
		}
		
		
		return "redirect:/board/list";
	}//register 
	
	
	@GetMapping("register")
	public void registerGet() {
		log.debug("register() invoked.");
	}
	
	
	@GetMapping("list")
	public void list(Model model) {
		log.debug("list({}) invoked.", model);
		
		List<BoardVO> boards = this.service.getList();
		
		Objects.requireNonNull(boards);
		boards.forEach(log::info);
		
		model.addAttribute("list", boards);
		
	}//list
	
	@GetMapping("listPerPage")
	public String listPerPage(Criteria cri, Model model) {
		log.debug("listPerPage({}) invoked.", model);
		
		List<BoardVO> boards = this.service.getListPerPage(cri);
		
		Objects.requireNonNull(boards);
		boards.forEach(log::info);
		
		model.addAttribute("list", boards);
		
		return "/board/list";
	}
	
	
	
	
	@GetMapping( {"get", "modify"})
	public void get(@RequestParam("bno") Integer bno, Model model) {
		log.debug("get({}, {}) invoked.", bno, model);
		
		BoardVO board = this.service.get(bno);
		
		Objects.requireNonNull(board);
		log.info("\t board: {}", board);
		
		model.addAttribute("board", board);
		
	}

	
	@PostMapping("remove")
	public String remove(@RequestParam("bno") Integer bno, RedirectAttributes rttrs) {
		log.debug("get({}, {}) invoked.", bno, rttrs);
		
		boolean isRemoved = this.service.remove(bno);
		if(isRemoved) { //삭제 성공 시  
			rttrs.addAttribute("result", "success");
		} 
		
		return "redirect:/board/list";
	}//remove
	
	
	
	
	
	
	

	//-------------------------------------------------------//
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("afterPropertiesSet() invoked.");
		
		Objects.requireNonNull(this.service);
		
		log.info("\t+ mapper : " + this.service);
		
	}//end afterPropertiesSet
	//-------------------------------------------------------//
	
	
}//end class
