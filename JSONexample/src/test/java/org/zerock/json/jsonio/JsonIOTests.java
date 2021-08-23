package org.zerock.json.jsonio;

import org.junit.Test;
import org.zerock.json.domain.Foo;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class JsonIOTests {
	
	
	@Test
	public void testSerialize() {
		log.debug("testSerialize() invoked.");
		
		Foo foo = new Foo();
		foo.setId(777);
		foo.setName("Pyramide");
		
		String json = JsonWriter.objectToJson(foo);
		log.info("\t+ json: " + json);
	} // testSerialize
	
	
	@Test
	public void testDeserialize() {
		log.debug("testDeserialize() invoked.");
		
		String json = "{\"@type\":\"org.zerock.json.domain.Foo\",\"id\":777,\"name\":\"Pyramide\"}";	// OK
//		String json = "{\"id\":777,\"name\":\"Pyramide\"}";		// XX
		
		Foo foo = (Foo) JsonReader.jsonToJava(json);
		
		log.info("\t+ foo: {}", foo);
		log.info("\t\t+ id: {}", foo.getId());
		log.info("\t\t+ name: {}", foo.getName());
	} // testDeserialize

} // end class
