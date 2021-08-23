package org.zerock.json.domain;

import lombok.Data;


// (주의) Java Object 의 Serialize(Object -> JSON)와 De-serialize(JSON -> Object)을 위해서는
// 기본생성자와 각 필드에 대한 Getter/Setter 메소드가 있어야 하므로, @Value 어노테이션을 사용하면 안됨!!!

@Data
public class Foo {
	
	private Integer id;
	private String name;
	
	

} // end class
