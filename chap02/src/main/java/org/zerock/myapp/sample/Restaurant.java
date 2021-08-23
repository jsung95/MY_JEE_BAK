package org.zerock.myapp.sample;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;
import lombok.extern.log4j.Log4j;



//@Data
@Log4j
@Component("restaurant")
public class Restaurant implements InitializingBean, DisposableBean{
	
//	@Setter(onMethod = { @Autowired })
//	@Setter(onMethod = { @Inject })
//	@Setter(onMethod = { @Resource })
//	@Setter(onMethod = { @Resource(type=Chef.class) })
	
//	@Setter(onMethod = @Autowired)
//	@Setter(onMethod = @Inject)
//	@Setter(onMethod = @Resource)
//	@Setter(onMethod = @Resource(type = Chef.class))
	
//	@Autowired
//	@Inject
//	@Resource
	
//	@Resource(type=Chef.class)
	
	
	private Chef chef;
	
	//생성자를 통한 의존성 주입 테스트
	//Spring Ver4.3 이후로는, 주입할 필드가 1개이고, 생성자의 매개변수 역시 주입시킬 타입의 필드를 매개변수로 가진다면
	//굳이.. 주입 시그널을 보내지 않아도 자동으로 의존성객체 주입해준다. 
//	@Autowired
	public Restaurant(Chef chef) {
		log.debug(String.format("Restaurant(%s) invoked.", chef));
		
		this.chef = chef;
	}


	
	
	public Chef getChef() {
		log.debug("getChef() invoked.");
		return chef;
	}//getChef

//	@Autowired
//	public void setChef(Chef chef) {
//		log.debug(String.format("setChef(%s) invoked.", chef));
//		this.chef = chef;
//	}//setChef
	
	
	@Override
	public void destroy() throws Exception {
		log.debug("destroy() invoked.");
		
	}//destroy
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("afterPropertiesSet() invoked.");
	}//afterPropertiesSet
	
	
	
	
	
	
}//end class
