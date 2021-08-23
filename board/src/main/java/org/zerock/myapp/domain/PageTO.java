package org.zerock.myapp.domain;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PageTO {
	@Getter @Setter List<BoardVO> list; //목록 리스트 저장 
	@Getter @Setter int curPage; //현재 페이지 번호
	@Getter int perPage = 5; //페이지당 보여줄 개수 
	@Getter @Setter int totalCount; //전체 레코드 개수 
	
}
