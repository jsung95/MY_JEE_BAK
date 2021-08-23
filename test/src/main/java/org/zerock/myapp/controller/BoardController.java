package org.zerock.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.service.BoardService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	BoardDTO dto = new BoardDTO();
	
	@GetMapping("list")
	public void getList(Model model) {
		List<BoardVO> list = this.service.readAll();
		
		model.addAttribute("list", list);
	}
	
	@GetMapping({"get", "modify"})
	public void get(BoardDTO dto, Model model) {
		
		BoardVO board = this.service.readOne(dto);
		
		model.addAttribute("board", board);
	}
	
	@GetMapping("register")
	public void register() {
		
	}
	
	@PostMapping("register")
	public String register(BoardDTO dto, RedirectAttributes rttrs) {
		log.debug("register_controller invoked.");
		this.service.register(dto);
		rttrs.addAttribute("result", "success");
		return "redirect:/board/list";
	}
	
//	@GetMapping("modify")
//	public void modify(BoardDTO dto) {
//		log.info("\t\t+@@@@@ >> : " + dto.getBno());
//	}
	
	@PostMapping("modify")
	public String modify(BoardDTO dto, RedirectAttributes rttrs) {
		
		this.service.modify(dto);
		
		
		rttrs.addAttribute("result", "success");
		
		return "redirect:/board/list";
	}
	
	@PostMapping("remove")
	public String remove(BoardDTO dto, RedirectAttributes rttrs) {
		this.service.remove(dto);
		
		rttrs.addAttribute("result", "success");
		
		return "redirect:/board/list";
	}
	
	
	@GetMapping("session")
	public void session(HttpSession session) {
		session.setAttribute("data", "value");
		
	}
	@GetMapping("session2")
	public void session2(@SessionAttribute("data") String value, Model model) {
		
		
		model.addAttribute("data", value);
	}
	
}
