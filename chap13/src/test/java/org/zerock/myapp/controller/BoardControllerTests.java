package org.zerock.myapp.controller;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

//Spring Beans Container 객체의 생성 + Spring MVC 흐름을 생성
@WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
      "file:src/main/webapp/WEB-INF/spring/root-*.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-*.xml"
})
public class BoardControllerTests {
   
   //Controller는 여타 다른 계층의 빈 객체처럼 주입받지 않는다!!
//   @Setter(onMethod_=@Autowired)
//   BoardController controller;
   
   @Setter(onMethod_=@Autowired)
   private WebApplicationContext ctx;      //Spring Beans Container 객체
   
   private MockMvc mockMvc;      //Web Browser Emulator
   
   @Before
   public void setup() {
      log.debug("setup() invoked.");
      
      assertNotNull(this.ctx);      
      log.info("\t+ ctx : {}", ctx);   
      
//      String[] allBeans = ctx.getBeanDefinitionNames();
//      //log.info(Arrays.toString(allBeans));
//      
//      int index = 1;
//      for(String beanName : allBeans) {
//         log.info("{}. bean : {}", index++, beanName);
//      } //enhanced-for
   } //setup
   
 //controller는 다른 계층의 빈 객체처럼 주입받지 않는다!!!
   //1) 왜? 요청 URI + 전송파라미터가 필요 ==> HTTP request가 필요 ==> 웹브라우저가 필요
   //2) HTTP request를 받아서 처리할 Servlet container가 필요!! => WAS가 필요!
   //3) 1,2의 환경을 조성해줘야 컨트롤러 테스트가 가능
      //방법 1. WAS구동 + 웹브라우저 사용
      //      대규모 웹서비스를 만들 때에는, WAS를 한번 내렸다 올리는데 최소 30분 이상 걸릴때가 많음.
      //방법 2. WAS를 구동시키지 않더라도, 스프링 프레임워크를 마치 WAS를 구동시킨 것처럼, --> 그냥 가짜환경을 조성해주는거라 API호출은 안됌!!
      //      비슷한 환경으로 만들어 주는 방법 + 웹브라우저마저 에뮬레이션 할 수 있는 방법을 함께 제공
      //
      //   스프링 제공방법(@WebAppConfiguration) : 스프링 코어(core)가 바로 빈즈컨테이너(DI+빈객체를 관리) 생성해줘야 함!
      //   스프링 MVC흐름을 강제시켜야 함!
      //   웹브라우저 에뮬레이션 => Mock(가짜)MVCRequest관련 클래스를 제공한다!
   
   @Test(timeout = 1000)
   public void testListPerPage() throws Exception {
      log.debug("testListPerPage() invoked.");
      
      //=========================================================//
      // MockMvc 를 이용한 웹브라우저 에뮬레이션 수행(HTTP request 문서 생성) //
      //=========================================================//
      
//      MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
//      log.info("\t(1) mockMvcBuilder : " + mockMvcBuilder);
//      
//      MockMvc mockMvc = mockMvcBuilder.build();
//      log.info("\t(2) mockMvc : " + mockMvc);
//      
//      RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/board/list");
//      log.info("\t(3) reqBuilder : " + reqBuilder);
//      
//      //지정된 RequestBuilder를 통해, HTTP request를 만들어 보냄
//      ResultActions resultActions = mockMvc.perform(reqBuilder);
//      log.info("\t(4) resultActions : " + resultActions);
//      
//      //위에서 요청을 보낸 결과로 HTTP response를 받는 코드
//      MvcResult mvcResult = resultActions.andReturn();
//      log.info("\t(5) mvcResult : " + mvcResult);
//      
//      ModelAndView modelAndView = mvcResult.getModelAndView();
//       log.info("\t(6) modelAndView: " + modelAndView);
//         
//       Map<String, Object> model = modelAndView.getModel();
//       log.info("\t(7) model: " + model);
//         
//       String viewName = modelAndView.getViewName();
//       log.info("\t(8) viewName: " + viewName);
//         
//       View view = modelAndView.getView();
//       log.info("\t(9) view: " + view);
      
      //--------------------------------------------------------------------//
      // (1) 가짜 웹브라우저와 HTTP Request를 만들어 요청을 보내서, 컨트롤러 메소드가 수행되고, 
      //      수행된 컨트롤러 메소드의 결과물인 Model과 View이름을 얻어냄
      //--------------------------------------------------------------------//
      MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
      MockMvc mockMvc = mockMvcBuilder.build();
      
      RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/board/listPerPage");
      
      //ModelMap => LinkedHashMap<String, ArrayList>
      ModelMap modelMap = mockMvc.perform(reqBuilder).andReturn().getModelAndView().getModelMap();
      
      //-----------------------------------------------//
      //(2) (1)에서 얻어낸 Model 객체의 모든 key와 value를 출력 //
      //-----------------------------------------------//
      modelMap.forEach((k, v) -> {
         log.info("\t + (key, value) = ({}, {})", k, v);
      });   //forEach      
      
   } //testList
   
   @Test
   public void testGet() throws Exception {
	  log.debug("testGet() invoked.");
	  
	  MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.ctx);
	  MockMvc mockMvc = mockMvcBuilder.build();
	  
	  
	  //Request URI 생성 
	  MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/board/get");
	  
	  //전송파라미터의 설정 
	  reqBuilder.param("bno", "1007");
	  
	  ModelMap modelMap = mockMvc.perform(reqBuilder).andReturn().getModelAndView().getModelMap();
      modelMap.forEach((k, v) -> {
          log.info("\t + (key, value) = ({}, {})", k, v);
       });   //forEach  
   }
   
   
   @Test
   public void testRemove() throws Exception {
	  log.debug("testRemove() invoked.");
	  
	  MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.ctx);
	  MockMvc mockMvc = mockMvcBuilder.build();
	  
	  MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/board/remove");
	  reqBuilder.param("bno", "1001");
	  
	  ModelMap modelMap = mockMvc.perform(reqBuilder).andReturn().getModelAndView().getModelMap();
	  modelMap.forEach((k, v) -> {
		  log.info("\t + (key, value) = ({}, {})", k, v);
	  });
	  
   }//testRemove
   
   @Test
   public void testModify() throws Exception {
	   log.debug("testRemove() invoked.");
	   
	   MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.ctx);
	   MockMvc mockMvc = mockMvcBuilder.build();
	   
	   MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/board/modify");
	   reqBuilder.param("bno", "1").
	   			  param("title", "제목수정1").
	   			  param("content", "내용수정1").
	   			  param("writer", "작성자수정1");
	   
	   ModelMap modelMap = mockMvc.perform(reqBuilder).andReturn().getModelAndView().getModelMap();
	   modelMap.forEach((k, v) -> {
		   log.info("\t+ (key, value) = ({}, {})", k, v);
	   });
   }
   
   @Test
   public void testRegister() throws Exception {
	   log.debug("testRegister() invoked.");
	   
	   MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.ctx);
	   MockMvc mockMvc = mockMvcBuilder.build();
	   
	   MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/board/register");
	   reqBuilder.param("title", "제목1").
	   			  param("content", "내용1").
	   			  param("writer", "작성자1");
	   
	   ModelMap modelMap = mockMvc.perform(reqBuilder).andReturn().getModelAndView().getModelMap();
	   modelMap.forEach((k, v) -> {
		   log.info("\t+ (key, value) = ({}, {})", k, v);
	   });
   }
   
   @After
   public void tearDown() {
      log.debug("tearDown() invoked.");
      
   } //tearDown

} //end class