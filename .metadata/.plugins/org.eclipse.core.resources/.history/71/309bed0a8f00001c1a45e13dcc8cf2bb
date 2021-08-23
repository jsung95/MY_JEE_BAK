package org.zerock.myapp.gson;

import org.junit.Test;
import org.zerock.myapp.domain.Ticket;

import com.google.gson.Gson;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor


public class GsonTests {
	
	
	@Test
	public void testSerializeByGson() {
		log.debug("testSerializeByGson() invoked.");
		
		Gson gson = new Gson();
		
		//Serialize : Java Object ==> JSON 변환 작업 
		//De-Serialize : JSON ==> Java Object 변환 작업
		
		Ticket ticket = new Ticket(77, "lee", "A");
		String json = gson.toJson(ticket); //serialize
		
		log.info("");
	}
	
}
