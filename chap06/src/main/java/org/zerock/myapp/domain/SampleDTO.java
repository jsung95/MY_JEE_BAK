package org.zerock.myapp.domain;

import lombok.Data;

// DTO 패턴 : 웹 3계층에서 앞쪽계층(Presentation, 즉 화면)에서 요청문서안에 
//			 포함시킨 전송파라미터(Request Parameter)들을 수신하고,
//			 웹3계층의 뒷게층으로 전달하는 목적의 객체

@Data
public class SampleDTO {
	
	private String name;
	private Integer age;
	
}//end class 
