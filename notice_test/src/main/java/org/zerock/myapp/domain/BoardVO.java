package org.zerock.myapp.domain;

import java.sql.Timestamp;

import lombok.Value;


@Value
public class BoardVO {
	private Integer bno;
	private Integer reproot;
	private Integer repstep;
	private Integer repindent;
	private String title;
	private String content;
	private String memberid;	
	private Timestamp insert_ts;
	private Timestamp update_ts;
	private Integer readcnt;
	private String bname;
	private Integer fid;
	private String del_tf;
	private String notice_tf;
	private String public_tf;
	private String reply_tf;
}//end class
