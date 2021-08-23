package org.zerock.myapp.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@Controller
@RequestMapping("/fileupload/")
public class FileUploadController {
	
	@GetMapping("page")
	public void page() {
		log.debug("page() invoked.");
		
		
		
	}//page
	
	@PostMapping("doit")
//	public void doit( ArrayList<MultipartFile> files ) {
	public void doit( @RequestParam("ck") List<MultipartFile> files ) {
		log.debug("doit() invoked.");
		
		String uploadDir = "/Users/jinsung/Desktop/temp/upload/";
		
		
		files.forEach( f -> {
				log.info("\t+ contentType : " + f.getContentType());
				log.info("\t+ filename : " + f.getOriginalFilename());
				log.info("\t+ filesize : " + f.getSize());
				log.info("\t==============================================");
				
				if(f.getSize() > 0) {
					try {
						byte[] fileData = f.getBytes();
						FileOutputStream fos = new FileOutputStream(uploadDir + f.getOriginalFilename());
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						
						bos.write(fileData);
						
						bos.flush();
						bos.close();
						
						fos.close();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		} ); //forEach
		
		
		
	}
	
}//end class
