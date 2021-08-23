package org.zerock.myapp.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

//페이징 처리를 위해 기준값을 저장하는 용도의 클래스 
// 그리고 이 기준 클래스를 앞으로 만들 DTO 클래스에서 사용하자!!!!!
@Log4j2
@Data
public class Criteria {
	
	private int currPage = 1;		//현재 보고자 하는 페이지 번호 
	private int amount = 10;		//한 페이지당 보여줄 레코드 건수
	private int pagesPerPage = 10;  //각 페이지 아래에 보여줄 페이지번호의 갯수  
	
	//검색조건 필드 
	private String type;		//검색 유형 
	private String keyword;		//검색어
	
	
	public String getPagingUri() {
	      log.debug("getPagingUri() invoked.");
	      
	      UriComponentsBuilder builder = UriComponentsBuilder.fromPath("");
	      
	      builder.queryParam("currPage",this.currPage);
	      builder.queryParam("amount",this.amount);
	      builder.queryParam("pagesPerPage",this.pagesPerPage);
	      builder.queryParam("type",this.type);
	      builder.queryParam("keyword",this.keyword);
	      
	      log.info("\t+ pagingUri : " + builder.toUriString());
	      
	      return builder.toUriString();
	}
}//end class
