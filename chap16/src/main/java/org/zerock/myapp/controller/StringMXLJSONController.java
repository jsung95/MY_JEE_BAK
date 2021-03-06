package org.zerock.myapp.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.SampleVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor


@RequestMapping("/sample/")
@RestController
public class StringMXLJSONController {
	
	@RequestMapping(
			path = "getText",
			//아래의 이 컨트롤러 메소드가 어떤 유형의 문서를 생성할지를 지정.
			//지정할 문서의 유형은 MediaType안에 상수로 정의되어 있다.
			produces = {
//					MediaType.TEXT_PLAIN_VALUE
					MediaType.TEXT_PLAIN_VALUE + "; charset=utf8"
			}
	)
	public String getText() {
		
		log.debug("getText() invoked.");
		
		
		String responseData = "안녕하세요 반갑습니다.";
		
		return responseData;
	}//getText
	
	
	
	@GetMapping(
			path = "getSample",
			produces = {
//				MediaType.APPLICATION_JSON_VALUE,
//				MediaType.APPLICATION_JSON_VALUE + "; charset=utf8",
					
				MediaType.APPLICATION_XML_VALUE,
//				MediaType.APPLICATION_XML_VALUE + "; charset=utf8",
			}
			
	)
	public SampleVO getSample() {
		log.debug("getSample() invoked.");
		
		SampleVO sample = new SampleVO(1, "성", "이름");
		
		return sample;
	}//getSample
	
	
	@GetMapping(
			path = "getSampleByJSON",
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			}
	)
	public SampleVO getSampleByJSON() {
		log.debug("getSampleByJSON() invoked.");
		
		SampleVO sample = new SampleVO(2, "성", "이름");
		
		return sample;
	}//getSampleByJSON
	
	
	@GetMapping(
			path = "getSampleByXML",
			produces = {
					MediaType.APPLICATION_XML_VALUE
			}
	)
	public SampleVO getSampleByXML() {
		log.debug("getSampleByXML() invoked.");
		
		SampleVO sample = new SampleVO(3, "성", "이름");
		
		return sample;
	}//getSampleByJSON
	
	
	
	@GetMapping(
			path = "getSampleList",
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			}
	)
	public List<SampleVO> getSampleList() {
		log.debug("getSampleList() invoked.");
		
		List<SampleVO> list = new ArrayList<>();
		
		for(int i=0; i<10; i++) {

			SampleVO sample = new SampleVO(i, "성", "이름");
			list.add(sample);
		}
		
		list.forEach(log::info);
		
		return list;
	}//getSampleByJSON
	
	
	
	@GetMapping(
			path = "getSampleMap",
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			}
	)
	public Map<String, SampleVO> getSampleMap() {
		log.debug("getSampleMap() invoked.");
		
		Map<String, SampleVO> map = new HashMap<>();
		
		for(int i=0; i<10; i++) {

			SampleVO sample = new SampleVO(i, "성", "이름");
			map.put("Sample_"+i, sample);
		}
		
		map.forEach(log::info);
		
		return map;
	}//getSampleByJSON
	
	
	

	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource; // 클라우드 데이터베이스 접근을 위해, 필요한 빈 객체를 의존성주입.
	
	@GetMapping(
			path = "getBoardList",
			produces = {
					MediaType.APPLICATION_JSON_VALUE
			}
	)
	public List<BoardVO> getBoardList() throws Exception {
		log.debug("getBoardList() invoked.");
		
		List<BoardVO> list = new ArrayList<>();
		
		//Hikari DataSource를 이용해서, tbl_board 테이블의 모든 레코드를 조회해서,
		//List에 담아서 리턴
		
		try {
			Connection conn = dataSource.getConnection();
			
			String sql = "SELECT /*+ INDEX_DESC(tbl_board)*/ * FROM tbl_board";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			try(conn; pstmt; rs) {
				list = new ArrayList<>();
				
				while(rs.next()) {
					Integer bno = rs.getInt("bno");
					String title = rs.getString("title");
					String content = rs.getString("content");
					String writer = rs.getString("writer");
					Timestamp insert_ts = rs.getTimestamp("insert_ts");
					Timestamp update_ts = rs.getTimestamp("update_ts");
					
					BoardVO board = new BoardVO(bno, title, content, writer, insert_ts, update_ts);
					
					list.add(board);
				}//while
				
				list.forEach(log::info);
				
			}//try-with-resources
			
		} catch (Exception e) {
			throw e;
		}//try-catch
		
		
		return list;
	}//getSampleByJSON
	
	
	
	
	
}
