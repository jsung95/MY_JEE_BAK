package org.zerock.myapp.domain;

import lombok.Data;

@Data
public class AttachFileDTO {
	private Integer fid;		//	파일ID
	private String frename;		//	첨부파일변경명칭
	private String fpath;		//	첨부자료 저장위치
	private String forname;		//	첨부파일원파일명

}
