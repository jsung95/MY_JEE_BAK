package org.zerock.myapp.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.domain.MemberDTO;
import org.zerock.myapp.service.RegisterService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Controller
@RequestMapping("/register/")
public class RegisterController {

	
	@Setter(onMethod_ = @Autowired)
	private RegisterService service; 
	
	
	@GetMapping("checkCBNO")
	public void checkCBNO() {
		log.debug("checkCBNO() invoked.");
	}
	
	@GetMapping("register")
	public void register() {
		log.debug("register() invoked.");
	}
	
	@PostMapping("register")
	public String register(MemberDTO dto, @RequestParam("file") List<MultipartFile> file) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		this.service.register(dto);
		
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
					}//try-with-resources
					
				} catch (Exception e) {
					// TODO: handle exception
				}//try-catch
			}//if
		});//forEach
		
		return "redirect:/register/checkCBNO";
	}
	
	
	
	//????????????????????? DB????????????
	@ResponseBody
	@PostMapping("doubleCheckCBNO")
	public int doubleCheckCBNO(MemberDTO dto) {
		log.info("!@#@#@! > cbno : " + dto.getCbno());
		int check = this.service.checkCBNO(dto);
		
		if(check == 0) {
			return 1;
		} else {
			return 0;
		}
		
	}
	
	//????????????????????? API ????????? ??????
	@ResponseBody
	@PostMapping("homeTaxCBNO")
	public String homeTaxCBNO(MemberDTO dto) throws ClientProtocolException, IOException {
		
		Double tmp = this.service.homeTaxCBNO(dto);
		log.info("API ?????? ?????? : " + tmp);
		if(tmp != null) { //??????????????? 1 
			return "ok";
		} else { //????????? ????????? 0 
			return "fail";
		}
	}
	
	
	@ResponseBody
	@PostMapping("sendMail")
	public void register(MemberDTO dto, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		this.service.sendMail(dto, session);
		
	}
	
	@ResponseBody
	@PostMapping("emailCertification")
	public String emailCertification(HttpServletRequest request, String inputCode) {
		
		HttpSession session = request.getSession();
		boolean result = this.service.emailCertifiation(session, inputCode);
		
		log.info("(**) Controller result : " + result);
		log.info("(**) Controller InputCODE : " + inputCode);
		
		if(result == true) {
			return "ok"; 
		} else {
			return "fail";
		}
		
	}//emailCertification
	
	@ResponseBody
	@PostMapping("removeSession")
	public String removeSession(HttpServletRequest request, String inputCode) {
		
		HttpSession session = request.getSession();
		
		/*
		 ?????? remove ?????? emailCertification ?????? ?????? null??? ??????????????? NullPointerException ??????,
		 ????????? ?????? remove??? ?????? ??????????????? ????????? ???????????????...
		 */
//		session.removeAttribute("auth");
		
		String randomString = UUID.randomUUID().toString();
		session.setAttribute("auth", randomString);
		log.info("%%% value = " + session.getAttribute("auth"));
		if((String)session.getAttribute("auth") != inputCode) {
			return "ok";
		} else {
			return "fail";
		}
	}//?????? ?????? - ?????? ??????
	
}
