package org.zerock.myapp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.zerock.myapp.domain.SampleDTO;
import org.zerock.myapp.domain.SampleDTOList;
import org.zerock.myapp.domain.TodoDTO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@RequestMapping("/sample/")
@Controller("sampleController")
public class SampleController {

	//-------------------------------------------------//
	// 1. @RequestMapping or @RequestMapping("")
	//-------------------------------------------------//

	@ResponseStatus(HttpStatus.OK)
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	@RequestMapping
	@RequestMapping("")
	public String sample() {
		log.debug("sample() invoked.");
		
		return "sample"; // 즉, sample.jsp로 연결 
	}//basic
	
	
	
	
	//-------------------------------------------------//
	// 2. @RequestMapping(path, method)
	//-------------------------------------------------//
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(
//			path= {"/basicGet"},		// ----> /WEB-INF/views/sample/basicGET.jsp
//			method= {RequestMethod.GET} // ----> HTTP Method
//			
//			path= "/basicGet",
//			method= RequestMethod.GET
			
//			path = {"/basicGet1", "/basicGet2" },
//			method = {RequestMethod.GET, RequestMethod.POST}
			
			path = "basicGet"  // ----> ..../sample/basicGet
	)
	public String basicGet() { //중요, 이메소드가 반환하는문장이 곧 View 의 이름이 된다.
		log.debug("basicGet() invoked.");
		
		// --> /WEB-INF/views/sample/basicGET.jsp
		return "sample";
	}
	
	
	
	//-------------------------------------------------//
	// 3. @RequestMapping(path, method)
	//-------------------------------------------------//
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(path = "basicGetPost", method = {GET, POST})
	public String basicGetPost() {
		return "sample";
	}

	
	//-------------------------------------------------//
	// 4. @RequestMapping의 단축형 어노테이션 - @GetMapping(path)
	//-------------------------------------------------//
	
	@ResponseStatus(HttpStatus.OK)
//	@RequestMapping(path = "basicOnlyGet", method = RequestMethod.GET)
	
	//아래의 GetMapping 어노테이션은다음과 같다.
	// --> @RequestMapping(path = "basicOnlyGet", method = RequestMethod.GET)
	@GetMapping("basicOnlyGet")
	public String basicOnlyGet() {
		log.debug("basicOnlyGet() invoked.");
		
		return "sample";
	}
	
	
	//-------------------------------------------------//
	// 5. @PostMapping(path)
	//-------------------------------------------------//
	@ResponseStatus(HttpStatus.OK)

	
	//브라우저에서 보내는방식(주소창에 입력하는방식)은 모두 GET 방식이다.
	//따라서 아래 매핑 경로에 접속해보면 405 에러가 발생한다.
	//따라서 포스트맨 프로그램에서 실행해보면 나옴!
	@PostMapping("basicOnlyPost")
	public String basicOnlyPost() {
		log.debug("basicOnlyPost() invoked.");
		
		return "sample";
	}
	
	
	//-------------------------------------------------//
	// 6. @GetMapping(path) with DTO parameter
	//-------------------------------------------------//
	
    @ResponseStatus(HttpStatus.OK)
   
    @GetMapping("ex01")
 // 요청파라미터를 담을 매개변수의 타입또한 가능하면 기본타입이 아닌 레퍼런스타입으로 써주자!
//    public String ex01(String name, Integer age, Integer age2) {	
	public String ex01(SampleDTO dto) {	
       log.debug("ex01(dto invoked.");
       
       // http://ijinseong-ui-macbookpro.local:8090/sample/ex01?name=lee&age=23
       //														쿼리스트링으로 값을 지정 
//       log.info("\t+name : " + name + "/ age : " + age);
//       log.info(age2);
       
       
       //브라우저에서 쿼리스트링방식으로 요청파라미터를 입력하면 setter 가되어 dto에 값이 들어간다.
       // 전송되지 않은 파라미터 age2 에는 값이 들어오지 않았으므로 자동으로 null이 들어간다.
       log.info("\t+dto : " + dto);
       
       
       //Spring에서는 전송파라미터를 수집하기위한 DTO 객체를  ===> Spring Command Object 라고한다.
       // 이 Spring Command Object는, Spring MVC가 최종적으로 View(JSP)를 호출할때,
       // 동적인 문서를 생성할 수 있도록, Request Scope 공유영역에 자동으로 속성 바인딩(setAttribute)해준다.
       return "sample";
    }//ex01\

    
	//-------------------------------------------------//
	// 7. @GetMapping(path) with som primitive types parameters
	//-------------------------------------------------//
//	@ResponseStatus(HttpStatus.OK)
//	@GetMapping("ex02")
//	public String ex02(String recvName, Integer currentAge) {
//		log.debug("ex02() invoked.");
//		
//		log.info("\t+name : " + recvName);
//		log.info("\t+age : " + currentAge);
//		
//		return "sample";
//	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("ex02")
	
	//전송파라미터를 어노테이션을 통해 얻어보자 
	// @RequestParam("요청파라미터명")  
	public String ex02(	@RequestParam("name") String recvName,
						@RequestParam("age") Integer currentAge) {
		log.debug("ex02() invoked.");
		
		log.info("\t+name : " + recvName);
		log.info("\t+age : " + currentAge);
		
		return "sample";
	}
	
	
	//-------------------------------------------------//
	// 8. @GetMapping(path) with some list types parameter
	//-------------------------------------------------//

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("ex02List")
//	public String ex02List(ArrayList<String> ids) { //수행은 되지만, 리스트에 전송파라미터값을 못받아온다.
//	public String ex02List(	@RequestParam("ids") ArrayList<String> ids ) { //값이 잘 들어온다.
//	public String ex02List(	List<String> ids ) { //인터페이스인 List 타입으로 선언해주면 구현타입이 많다보니, 오류가 난다.
	public String ex02List(	@RequestParam("ids") List<String> ids ) {
		log.debug("ex02List() invoked.");
		
		log.info("\t+ ids : " + ids);
		log.info("\t+ type : " + ids.getClass().getName());
		
		return "sample";
	}
	
	
	
	//-------------------------------------------------//
	// 9. @GetMapping(path) with Array types's parameter
	//-------------------------------------------------//
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.debug("ex02Array() invoked.");
		
		log.info("\t+ ids : " + Arrays.toString(ids));
		
		
		return "sample";
	}
	
	
	//-------------------------------------------------//
	// 10. @GetMapping(path) with DTO List types parameter
	//-------------------------------------------------//
	
	// 쿼리스트링 : ?list[0].name=NAME_1&list[0].age=1&list[1].name=NAME_2&list[1].age=2 하면 오류 발생
	// 배열의 인덱스 대괄호기호떄문에오류가 발생하기 때문에, 코드로 변환해서 쿼리스트링 전송 
	//    ---->   ?list%5B0%5D.name=NAME_1&list%5B0%5D.age=1&list%5B1%5D.name=NAME_2&list%5B1%5D.age=2
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.debug("ex02Bean() invoked.");
		
		log.info("\t+ list : " + list);
		
		
		return "sample";
	}
	
	
	
	//-------------------------------------------------//
	// 11. @InitBinder
	//-------------------------------------------------//
	// automatically called back when binding parameters
	//-------------------------------------------------//
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		log.debug("initBinder() invoked.");
//		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		
//		binder.registerCustomEditor(
//			java.util.Date.class,
//			new CustomDateEditor(dateFormat, false)
//		);
//	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("ex03")
	public String ex03(TodoDTO dto) {
		log.debug("ex03() invoked.");
		
		log.info("\t+ dto : " + dto);
		
		return "sample";
	}
	
	//12. TodoDTO.java에 @DateTimeFormat 어노테이션
	
	
	//-------------------------------------------------//
	// 13. Predefined Model parameter
	//-------------------------------------------------//
	// to transfer request parameters into the View
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("ex05")
	public String ex05(String name, int age, int page, Model model) { //Model은 RequestScope의 파라미터값을 바인딩하기위한 상자
		log.debug("ex05() invoked.");
		
		log.info(
				String.format("\t+ name: %s, age: %d, page: %d", name, age, page)
				);
		
		log.info("\t+ model: " + model.getClass().getName()); //Model 객체는 Map형식의 객체이다. 
		
		SampleDTO dto = new SampleDTO();
		dto.setName(name);
		dto.setAge(age);
		
		//Model 객체는 put으로 값을 안넣어주고 addAttribute로 넣어준다 
		model.addAttribute("sampleDTO", dto);
		model.addAttribute("page", page);
		
		// model이라는 상자안에 addAttribute로 키/값을 지정해주면 그값은 RequestScope 에 할당된다.
		// 따라서 Spring MVC에서 View에 전달되어  JSP에서 참조하고 출력한다.
		
		// Model의 실질적원리는 addAttribute로 키/값을 할당하면 requestScope에 값을 바인딩하는 것에 불과하다.
		
		return "sample";
	}
	
	
	//-------------------------------------------------//
	// 14. @ModelAttribute(key) to transfer data into the view.
	//-------------------------------------------------//
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("ex06")
	public String ex06(SampleDTO dto, @ModelAttribute("page") int page) {
		log.debug("ex06() invoked.");
		
		
		//DTO에 들어간 값은 Command Object가 되어서 자동으로 Model이란 상자안에 들어가서 공유영역인 RequestScope에 바인딩된다.
		//하지만, int page는 RequestScope에 자동으로 들어가지 않는다. (즉, View로 전송되지 않는다.) 
		// --> 따라서, 우리는 page를 Model에 담아줘야 한다.
		//      ---> 그래서 우리는 @ModelAttribute("page") 어노테이션으로 Model 상자안에 13번예제에서 했던 것 처럼 Model이란 상자안에 담아서 RequestScope에 바인딩 시킨다.
		log.info("\t+ dto : " + dto);
		log.info("\t+ page : " + page);
		
		
		return "sample";
	}
	
	
	//-------------------------------------------------//
	// 15. Predefined RedirectAttributes
	//-------------------------------------------------//
	// to redirect request into the other url
	
//	@ResponseStatus(HttpStatus.OK) //******* <----- HttpStatus.OK 는 200번 코드를 보내주기때문에 리다이렉션인 304코드를못받아서 리다이렉션을 못한다 
	@GetMapping("ex07")
	public RedirectView ex07(String name, int age, RedirectAttributes rttrs) { //RedirectAttributes은 리다이렉션을 위한 상자 
		log.debug("ex07() invoked.");
		
		log.info(String.format("\t+ name: %s, age: %d", name, age));
		
		log.info("\t+ rtts : " + rttrs.getClass().getName());
		
		
		// addFlashAttribute 는 1회성이다.
//		rttrs.addFlashAttribute("name", name);
//		rttrs.addFlashAttribute("age", age);
		
		
		// addAttribute는 쿼리 스트링 형식으로 날라간다. 
		rttrs.addAttribute("name", name);
		rttrs.addAttribute("age", age);
		
		// 리다이렉션 응답을 보낸다.
		// 리다이렉션 응답을 보낼때 같이 보낼 데이터가 있다면 RedirectAttributes 상자에 데이터를 담아 리다이렉션을 보낸다.
		// 그럼 브라우저는 /sample/ex01 로 다시 요청을 보낸다.
//		return "redirect:/sample/ex01";		//옛날방식
//		return "redirect:http://localhost:8070/";
		return new RedirectView("/sample/ex01"); //새로운방식 
	}
	
	
	
}//end class
