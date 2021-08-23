package org.zerock.myapp.domain;

import lombok.Value;

@Value
public class BoardVO {

	private Integer num;
	private String author;
	private String title;
	private String content;
	private Integer readcnt;
	private String writeday;
	private Integer repRoot;
	private Integer repStep;
	private Integer repIndent;
}
