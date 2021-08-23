package org.zerock.myapp.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class Criteria {
	private int currPage = 1;
	private int amount = 5;
	private int pagesPerPage = 2;
	
	private String type;
	private String keyword;
}
