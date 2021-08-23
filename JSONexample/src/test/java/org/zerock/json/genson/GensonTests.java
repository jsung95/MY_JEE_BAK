package org.zerock.json.genson;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.zerock.json.domain.Foo;

import com.owlike.genson.Genson;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class GensonTests {
	
	
	
	@Test
	public void testSerialize() {
		log.debug("testSerialize() invoked.");
		
		Foo foo = new Foo();
		foo.setId(999);
		foo.setName("Cherryneo");
		
		Genson genson = new Genson();
		
		String json = genson.serialize(foo);
		log.info("\t+ json: {}", json);
	} // testSerialize
	
	
	@Test
	public void testDeserialize() {
		log.debug("testDeserialize() invoked.");
		
		String json = "{\"id\":999,\"name\":\"Cherryneo\"}";
		
		Genson genson = new Genson();
		
		Foo foo = genson.deserialize(json, Foo.class);
		
		log.info("\t+ foo: {}", foo);
		log.info("\t\t+ id: {}", foo.getId());
		log.info("\t\t+ name: {}", foo.getName());
	} // testDeserialize

	
	
	private final String json = "[{\"implYy\":\"2021\",\"implSeq\":1,\"qualgbCd\":\"C\",\"qualgbNm\":\"과정평가형자격\",\"description\":\"2021년 정기 과정평가형자격 1회\",\"docRegStartDt\":\"20210201\",\"docRegEndDt\":\"20210205\",\"docExamStartDt\":\"20210222\",\"docExamEndDt\":\"20210303\",\"docPassDt\":\"20210312\",\"pracRegStartDt\":\"20210201\",\"pracRegEndDt\":\"20210205\",\"pracExamStartDt\":\"20210222\",\"pracExamEndDt\":\"20210303\",\"pracPassDt\":\"20210312\"},{\"implYy\":\"2021\",\"implSeq\":2,\"qualgbCd\":\"C\",\"qualgbNm\":\"과정평가형자격\",\"description\":\"2021년 정기 과정평가형자격 2회\",\"docRegStartDt\":\"20210419\",\"docRegEndDt\":\"20210423\",\"docExamStartDt\":\"20210510\",\"docExamEndDt\":\"20210514\",\"docPassDt\":\"20210528\",\"pracRegStartDt\":\"20210419\",\"pracRegEndDt\":\"20210423\",\"pracExamStartDt\":\"20210510\",\"pracExamEndDt\":\"20210514\",\"pracPassDt\":\"20210528\"},{\"implYy\":\"2021\",\"implSeq\":3,\"qualgbCd\":\"C\",\"qualgbNm\":\"과정평가형자격\",\"description\":\"2021년 정기 과정평가형자격 3회\",\"docRegStartDt\":\"20210621\",\"docRegEndDt\":\"20210625\",\"docExamStartDt\":\"20210712\",\"docExamEndDt\":\"20210716\",\"docPassDt\":\"20210730\",\"pracRegStartDt\":\"20210621\",\"pracRegEndDt\":\"20210625\",\"pracExamStartDt\":\"20210712\",\"pracExamEndDt\":\"20210716\",\"pracPassDt\":\"20210730\"},{\"implYy\":\"2021\",\"implSeq\":4,\"qualgbCd\":\"C\",\"qualgbNm\":\"과정평가형자격\",\"description\":\"2021년 정기 과정평가형자격 4회\",\"docRegStartDt\":\"20210817\",\"docRegEndDt\":\"20210823\",\"docExamStartDt\":\"20210906\",\"docExamEndDt\":\"20210910\",\"docPassDt\":\"20210929\",\"pracRegStartDt\":\"20210817\",\"pracRegEndDt\":\"20210823\",\"pracExamStartDt\":\"20210906\",\"pracExamEndDt\":\"20210910\",\"pracPassDt\":\"20210929\"},{\"implYy\":\"2021\",\"implSeq\":5,\"qualgbCd\":\"C\",\"qualgbNm\":\"과정평가형자격\",\"description\":\"2021년 정기 과정평가형자격 5회\",\"docRegStartDt\":\"20210927\",\"docRegEndDt\":\"20211001\",\"docExamStartDt\":\"20211018\",\"docExamEndDt\":\"20211022\",\"docPassDt\":\"20211105\",\"pracRegStartDt\":\"20210927\",\"pracRegEndDt\":\"20211001\",\"pracExamStartDt\":\"20211018\",\"pracExamEndDt\":\"20211022\",\"pracPassDt\":\"20211105\"},{\"implYy\":\"2021\",\"implSeq\":6,\"qualgbCd\":\"C\",\"qualgbNm\":\"과정평가형자격\",\"description\":\"2021년 정기 과정평가형자격 6회\",\"docRegStartDt\":\"20211115\",\"docRegEndDt\":\"20211119\",\"docExamStartDt\":\"20211206\",\"docExamEndDt\":\"20211210\",\"docPassDt\":\"20211224\",\"pracRegStartDt\":\"20211115\",\"pracRegEndDt\":\"20211119\",\"pracExamStartDt\":\"20211206\",\"pracExamEndDt\":\"20211210\",\"pracPassDt\":\"20211224\"}],\"numOfRows\":10,\"pageNo\":1,\"totalCount\":6}}";	
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSerializeAndDeserialize() {
		log.debug("testSerializeAndDeserialize() invoked.");
		
		Genson genson = new Genson();
		
		//--- De-serialize -----------------------------------//
		
		List<Object> list = genson.deserialize(json, List.class);
		
		for(Object obj : list) {
			Map<String, Object> openData = (Map<String, Object>) obj;
			
//			log.info("\t+ openData: {}", openData);
//			log.info("\t+ Type: {}", openData.getClass().getName());
			
			log.info("\t\t+ implYy: {}", openData.get("implYy"));
			log.info("\t\t+ implSeq: {}", openData.get("implSeq"));
			log.info("\t\t+ qualgbCd: {}", openData.get("qualgbCd"));
			log.info("\t\t+ qualgbNm: {}", openData.get("qualgbNm"));
			log.info("\t\t+ description: {}", openData.get("description"));
			log.info("\t\t+ docRegStartDt: {}", openData.get("docRegStartDt"));
			log.info("\t\t+ docRegEndDt: {}", openData.get("docRegEndDt"));
			log.info("\t\t+ docExamStartDt: {}", openData.get("docExamStartDt"));
			log.info("\t\t+ docExamEndDt: {}", openData.get("docExamEndDt"));
			log.info("\t\t+ docPassDt: {}", openData.get("docPassDt"));
			log.info("\t\t+ pracRegStartDt: {}", openData.get("pracRegStartDt"));
			log.info("\t\t+ pracRegEndDt: {}", openData.get("pracRegEndDt"));
			log.info("\t\t+ pracExamStartDt: {}", openData.get("pracExamStartDt"));
			log.info("\t\t+ pracExamEndDt: {}", openData.get("pracExamEndDt"));
			log.info("\t\t+ pracPassDt: {}", openData.get("pracPassDt"));
		} // enhanced for
	} // testSerializeAndDeserialize

} // end class
