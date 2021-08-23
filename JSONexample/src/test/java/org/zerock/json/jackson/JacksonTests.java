package org.zerock.json.jackson;

import org.junit.Test;
import org.zerock.json.domain.Foo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class JacksonTests {
	
	
	@Test
	public void testSerialize() throws JsonProcessingException {
		log.debug("testSerialize() invoked.");
		
		Foo foo = new Foo();
		foo.setId(1);
		foo.setName("Yoseph");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(foo);
		
		log.info("\t+ json: {}", json);
	} // testSerialize
	
	
	@Test
	public void testDeserialize() throws JsonMappingException, JsonProcessingException {
		log.debug("testDeserialize() invoked.");
		
		String json = "{\"id\":1,\"name\":\"Yoseph\"}";
		
		ObjectMapper mapper = new ObjectMapper();
		Foo foo = mapper.<Foo>readValue(json, Foo.class);
		
		log.info("\t+ foo: {}", foo);
		log.info("\t\t+ id: {}", foo.getId());
		log.info("\t\t+ name: {}", foo.getName());
	} // testDeserialize

} // end class
