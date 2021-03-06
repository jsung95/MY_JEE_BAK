package org.zerock.httpclient;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.gson.Gson;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor
public class OpenAPITests {
	
	private final String baseURI = "http://apis.data.go.kr/B490007/qualExamSchd/getQualExamSchdList";	// HTTP  protocol scheme
	private final String serviceKey = "EXlMlCQsfsgyPjJSxICHyAaU8tRGEtaXa9WM/2hK0aP5mrTJdZm836I7xtLKmtGSVXxCHOU6Drqw667fmqhQVw==";
	
	
	@Test
	public void test() {
		log.debug("test() invoked.");

		
		//=========================================================================//
		// 1. To create a HTTP client supporting "HTTP" protocol scheme.
		//=========================================================================//

		//-----------------------------------------------//
		// For HTTP protocol scheme #1
		//-----------------------------------------------//
//		CloseableHttpClient httpClient = HttpClientBuilder.create().build();	// Without Request Configuration.
//
//		log.info("1. httpClient: {}", httpClient);

		
		//-----------------------------------------------//
		// For HTTP protocol scheme #2
		//-----------------------------------------------//
//		int timeout = 2;				// 2ms
//		
//		RequestConfig requestConfig =
//			RequestConfig.
//				custom().
//				setConnectTimeout(1000 * timeout).				// To set Connection timeout to the target host.
//				setSocketTimeout(1000 * timeout).				// To set Socket idle timeout between two buckets.
//				setConnectionRequestTimeout(1000 * timeout).	// To set Connection request timeout from Connection Manager(Pool).
//				build();
//		
//		CloseableHttpClient httpClient = 
//			HttpClientBuilder.
//				create().
//				setDefaultRequestConfig(requestConfig).
//				build();														// With Request Configuration.
//
//		log.info("1. httpClient: {}", httpClient);

		
		//=========================================================================//
		// 2. To create a HTTP client supporting "HTTPS" protocol scheme.
		//=========================================================================//

		//-----------------------------------------------//
		// For HTTPS protocol scheme #1
		//-----------------------------------------------//
//		CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
//
//		log.info("1. httpClient: {}", httpClient);

		
		//-----------------------------------------------//
		// For HTTPS protocol scheme #2
		//-----------------------------------------------//
		CloseableHttpClient httpClient =
			HttpClients.
				custom().
				setSSLHostnameVerifier(new HostnameVerifier() {

					@Override
					public boolean verify(String host, SSLSession sslSession) {
						log.debug("verify({}, {}) invoked.", host, sslSession);
						
						return false;
					} // verify
					
				}).
				build();

		log.info("1. httpClient: {}", httpClient);
		
		
		try (httpClient) {

			//=========================================================================//
			// 3. To prepare all necessary request parameters and create a HTTP request.
			//=========================================================================//
			String numOfRows = "10";
			String pageNo = "1";
			String dataFormat = "json";
			String implYy = "2021";
			String qualgbCd = "C";
			

			//-----------------------------------------------//
			// 1st. HTTP GET method
			//-----------------------------------------------//
			
			HttpUriRequest httpRequest =
				RequestBuilder.
					get(baseURI).
					addParameter("serviceKey", serviceKey).
					addParameter("numOfRows", numOfRows).
					addParameter("pageNo", pageNo).
					addParameter("dataFormat", dataFormat).
					addParameter("implYy", implYy).
					addParameter("qualgbCd", qualgbCd).
					build();
			
			log.info("2. httpRequest: {}", httpRequest);

			
			//-----------------------------------------------//
			// 2nd. HTTP POST method #0
			//-----------------------------------------------//
			
//			HttpUriRequest httpRequest =
//				RequestBuilder.
//					post(baseURI).
//					addParameter("serviceKey", serviceKey).
//					addParameter("numOfRows", numOfRows).
//					addParameter("pageNo", pageNo).
//					addParameter("dataFormat", dataFormat).
//					addParameter("implYy", implYy).
//					addParameter("qualgbCd", qualgbCd).
//					build();
//			
//			log.info("2. httpRequest: {}", httpRequest);

			
			//-----------------------------------------------//
			// 2nd. HTTP POST method #1
			//-----------------------------------------------//
			
//			HttpPost httpRequest = new HttpPost(baseURI);
//			
//			List<NameValuePair> requestParams =
//					Arrays.<NameValuePair>asList(
//						new BasicNameValuePair("serviceKey", serviceKey),
//						new BasicNameValuePair("numOfRows", numOfRows),
//						new BasicNameValuePair("pageNo", pageNo),
//						new BasicNameValuePair("dataFormat", dataFormat),
//						new BasicNameValuePair("implYy", implYy),
//						new BasicNameValuePair("qualgbCd", qualgbCd)
//					);
//			
//			httpRequest.setEntity(new UrlEncodedFormEntity(requestParams, Consts.UTF_8));
//			
//			log.info("2. httpRequest: {}", httpRequest);

			
			//=========================================================================//
			// 4. To send a HTTP request and receive a HTTP response
			//=========================================================================//
			CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);
			
			try (httpResponse) {

				//=========================================================================//
				// 5. To get Some information from the HTTP Response.
				//=========================================================================//
				int httpStatusCode = httpResponse.getStatusLine().getStatusCode();
				HttpEntity httpEntity = httpResponse.getEntity();
				ContentType contentType = ContentType.getOrDefault(httpEntity);
				String mimeType = contentType.getMimeType();
				long contentLength = httpEntity.getContentLength();
				
				log.info("3. HTTP status code: {}", httpStatusCode);
				log.info("4. httpEntity: {}", httpEntity);
				log.info("5. ContentType: {}", contentType);
				log.info("6. mimeType: {}", mimeType);
				log.info("7. contentLength: {}", contentLength);
				
				
				//=========================================================================//
				// 6. To get the body as a string from the HTTP Response.
				//=========================================================================//

//				assert httpStatusCode == HttpStatus.SC_OK;
				
				String bodyAsString = EntityUtils.toString(httpEntity);
			
				assertNotNull(bodyAsString);
				log.info("6. bodyAsString: {}", bodyAsString);
				

				//=========================================================================//
				// 6. To output HTTP response body to the Standard Output.
				//=========================================================================//

//				assert httpStatusCode == HttpStatus.SC_OK;
				
//				httpEntity.writeTo(System.out);
				
			} // try-with-resources
			
		} catch(IOException e) {
			e.printStackTrace();
		} // try-catch
		
	} // test
	

	
//=====================================================================================================================

	
	private final String baseURI_My = "https://api.odcloud.kr/api/nts-businessman/v1/status";
	
	@Test
	public void testMyAPI() throws ClientProtocolException, IOException {
		log.debug("test() invoked.");
		
		
		//-----------------------------
		//Step.1 Http Client?????? 
		//-----------------------------
		// 1. HTTP protocol ?????? client
//		HttpClientBuilder builder = HttpClientBuilder.create();
//		CloseableHttpClient httpClient = builder.build();  
		
		// 2. HTTPS protocol ?????? client
		HttpClientBuilder builder = HttpClientBuilder.create().setSSLHostnameVerifier(new NoopHostnameVerifier());
		CloseableHttpClient httpClient = builder.build();  
		
		
		
		
		//-----------------------------
		//Step.2 Request ?????? ??????  
		//-----------------------------
		
		//????????? ????????????
		String serviceKey = "SaEtDV6V2ezASF4OroNIlotaYMp84VVz%2BYxZ%2BFvB9MlN%2F7DSCPLNnNRRTZrFKSffpip1B4BvtocfTS08%2F6Hm1Q%3D%3D";
		
		
		
		HttpUriRequest request = RequestBuilder.post(baseURI_My).addParameter("serviceKey", serviceKey).build();
		
		
		
		
		
		
		//-----------------------------
		//Step.3 Request ?????? ?????? ==> Response ?????? ??????  
		//-----------------------------
		
		try(httpClient) {
			
			CloseableHttpResponse response = httpClient.execute(request);
			
			try(response) {
				
				int statusCode = response.getStatusLine().getStatusCode();
				log.info(" - HTTP Status code : {}", statusCode);
				
				assert statusCode == HttpStatus.SC_OK;
				
//				response.getEntity().writeTo(System.out);
				
				
				//-----------------------------
				//Step.4 Response ????????? Body??? ???????????? JSON ????????? ?????? 
				//-----------------------------
				HttpEntity entity = response.getEntity();
				
				String json =EntityUtils.toString(entity);
				log.info(json);
				
				
			}//try-with-resources
			
			
		}//try-with-resources
		
		
		
	}
	
	
	@Test
	public void testVVV() throws ClientProtocolException, IOException {
		
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=SaEtDV6V2ezASF4OroNIlotaYMp84VVz%2BYxZ%2BFvB9MlN%2F7DSCPLNnNRRTZrFKSffpip1B4BvtocfTS08%2F6Hm1Q%3D%3D");
		
		
		try(client) {
			String input = "2150893029";
			String json = "{ \"b_no\":[\""+ input +"\"]}";
			StringEntity entity = new StringEntity(json);
			
			httpPost.setEntity(entity);
			httpPost.setHeader("Content-type", "application/json");
			
			CloseableHttpResponse response = client.execute(httpPost);
			
			try(response) {
				log.info("STATUS : " + response.getStatusLine().getStatusCode());
				
				HttpEntity print = response.getEntity();
				
				String result = EntityUtils.toString(print);
				log.info(result);
				
				Gson gson = new Gson();
				Map map = new HashMap();
				
				map = (Map) gson.fromJson(result, map.getClass());
				log.info("@@@ : " + map.get("match_cnt"));
			}//try-with-resources
			
			
		}//try-with-resources
		
	}//test
	
	
	
	

} // end class
