package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardDTO {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Timestamp insert_ts;
	private Timestamp update_ts;
}
