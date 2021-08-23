package org.zerock.json.gson;

import org.junit.Test;
import org.zerock.json.domain.Foo;

import com.google.gson.Gson;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class GsonTests {
	
	
	@Test
	public void testSerialize() {
		log.debug("testSerialize() invoked.");
		
		Foo foo = new Foo();
		foo.setId(100);
		foo.setName("Trinity");
		
		Gson gson = new Gson();
		String json = gson.toJson(foo);
		
		log.info("\t+ json: {}", json);
	} // testSerialize
	
	
	@Test
	public void testDeserialize() {
		log.debug("testDeserialize() invoked.");
		
		String json = "{\"id\":100,\"name\":\"Trinity\"}";
		
		Gson gson = new Gson();
		Foo foo = gson.<Foo>fromJson(json, Foo.class);
		
		log.info("\t+ foo: {}", foo);
		log.info("\t\t+ id: {}", foo.getId());
		log.info("\t\t+ name: {}", foo.getName());
	} // testDeserialize

} // end class
