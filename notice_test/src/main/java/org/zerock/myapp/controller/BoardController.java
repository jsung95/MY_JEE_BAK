package org.zerock.myapp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.domain.AttachFileVO;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.PageDTO;
import org.zerock.myapp.domain.ReplyDTO;
import org.zerock.myapp.domain.ReplyVO;
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
		List<BoardVO> list_notice = this.service.readNotice();
		List<BoardVO> list = this.service.readAll();
		
		model.addAttribute("list_notice", list_notice);
		model.addAttribute("list", list);
	}
	
	
	@GetMapping("listPerPage")
	public String getListPerPage(@ModelAttribute("cri") Criteria cri, Model model) {
		List<BoardVO> list_notice = this.service.readNotice();
		List<BoardVO> list = this.service.getListPerPage(cri);
		
		PageDTO pageDTO = new PageDTO(cri, this.service.getTotal(cri));
		model.addAttribute("pageMaker", pageDTO);
		
		
		model.addAttribute("list_notice", list_notice);
		model.addAttribute("list", list);
		
		return "/board/list";
	}
	
	
	
	
	
	@GetMapping({"get", "modify", "answer"})
	public void get(ReplyDTO re_dto, BoardDTO dto, Model model, @ModelAttribute("cri") Criteria cri) {
		
		BoardVO board = this.service.readOne(dto);
		
		List<ReplyVO> reply = this.service.getReply(re_dto);
		
		model.addAttribute("board", board);
		model.addAttribute("reply", reply);
	}
	
	@PostMapping("modify")
	public String modify(@ModelAttribute("cri") Criteria cri, BoardDTO dto, RedirectAttributes rttrs) {
		
		this.service.modify(dto);
		
		
		rttrs.addAttribute("result", "success");
		rttrs.addAttribute("currPage", cri.getCurrPage());
		rttrs.addAttribute("amount", cri.getAmount());
		rttrs.addAttribute("pagesPerPage", cri.getPagesPerPage());
		
		
		return "redirect:/board/listPerPage";
	}
	
	@GetMapping("register")
	public void register(@ModelAttribute("cri") Criteria cri) {
		
	}
	
	@PostMapping("register")
	public String register(@ModelAttribute("cri") Criteria cri, BoardDTO dto, RedirectAttributes rttrs, @RequestParam("file") List<MultipartFile> file) {
		log.debug("register_controller invoked.");
		
		this.service.register(dto);
		
		rttrs.addAttribute("result", "success");
		rttrs.addAttribute("currPage", cri.getCurrPage());
		rttrs.addAttribute("amount", cri.getAmount());
		rttrs.addAttribute("pagesPerPage", cri.getPagesPerPage());
		
		
		AttachFileDTO attachDTO = new AttachFileDTO();
		
		
		String uploadDir = "/Users/jinsung/Desktop/temp/upload/";
		file.forEach(f -> {
			if(f.getSize() > 0) {
				try {
					
					String uuidFile = UUID.randomUUID().toString();
					
					byte[] fileData = f.getBytes();
					FileOutputStream fos = new FileOutputStream(uploadDir + uuidFile);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					
					try(fos; bos;) {
						attachDTO.setFpath(uploadDir);
						attachDTO.setForname(f.getOriginalFilename());
						attachDTO.setFrename(uuidFile);
						
						bos.write(fileData);
						
						this.service.uploadFile(attachDTO);
						this.service.addFileId(dto);
					}//try-with-resources
					
				} catch (Exception e) {
					// TODO: handle exception
				}//try-catch
			}//if
		});//forEach
		
		return "redirect:/board/listPerPage";
	}//register
	
	
	@GetMapping("load_img")
	public void load_img(BoardDTO dto, HttpServletResponse response) {
		
		AttachFileVO file = this.service.getFileById(dto);
		
		String path = file.getFpath();
		String fileReName = file.getFrename();
		
		File img = new File(path + fileReName);
		
		
		try {
			
			FileInputStream fis = new FileInputStream(img);
			OutputStream out = response.getOutputStream();
			
			
			byte[] buffer = new byte[1024];
			
			try(out; fis;) {
				
				int data;
				while ( (data = fis.read(buffer)) != -1) {
					out.write(buffer, 0, data);
				}//while
				
			}//try-with-resource
			
		} catch (Exception e) {
			
		}//try-catch
		
	}//load_img
	

	@PostMapping("remove")
	public String remove(@ModelAttribute("cri") Criteria cri, BoardDTO dto, RedirectAttributes rttrs) {
		this.service.remove(dto);
		
		rttrs.addAttribute("currPage", cri.getCurrPage());
		rttrs.addAttribute("amount", cri.getAmount());
		rttrs.addAttribute("pagesPerPage", cri.getPagesPerPage());
		
		return "redirect:/board/listPerPage";
	}
	
	
	
	@PostMapping("writeReply")
	public String writeReply(@ModelAttribute("cri") Criteria cri, ReplyDTO dto, RedirectAttributes rttrs) {
		
		this.service.writeReply(dto);
		
		rttrs.addAttribute("currPage", cri.getCurrPage());
		rttrs.addAttribute("amount", cri.getAmount());
		rttrs.addAttribute("pagesPerPage", cri.getPagesPerPage());
		
		return "redirect:/board/get?bno=" + dto.getBno();
	}
	
	@PostMapping("removeReply")
	public String removeReplay(@ModelAttribute("cri") Criteria cri, ReplyDTO dto, RedirectAttributes rttrs) {

		this.service.removeReply(dto);
		
		rttrs.addAttribute("currPage", cri.getCurrPage());
		rttrs.addAttribute("amount", cri.getAmount());
		rttrs.addAttribute("pagesPerPage", cri.getPagesPerPage());
		
		return "redirect:/board/get?bno=" + dto.getBno();
	}
	
	
	@PostMapping("answer")
	public String answer(@ModelAttribute("cri") Criteria cri, BoardDTO dto, RedirectAttributes rttrs) {
		this.service.writeAnswer(dto);
		rttrs.addAttribute("currPage", cri.getCurrPage());
		rttrs.addAttribute("amount", cri.getAmount());
		rttrs.addAttribute("pagesPerPage", cri.getPagesPerPage());
		
		return "redirect:/board/listPerPage";
	}
	
	
}
