package org.zerock.myapp.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Aspect
@Component
public class LogAdvice {
	//Advice 종류 : 5가지 (기준: Target 객체의 JoinPoint가 수행될 때, 어느싯점에 Advice를 적용시킬까에 따라 나눠짐)

	//Before Advice 선언
//	@Before("execution( 접근제한자 패키지명.클래스명.메소드명(매개변수목록) )")
	@Before("execution( * org.zerock.myapp.service.*Service.*(..) )")
	public void logBefore() {
		log.debug("============================================");
		log.debug("****>>> [Before Advice] logBefore() invoked.");
		log.debug("============================================");
		
		
	}//logBefore
	
	
	
	@Before("execution( * org.zerock.myapp.service.*Service.*(..) ) && args(s1, s2)")
	public void logBeforeWithArgs(String s1, String s2) {
		log.debug("============================================");
		log.debug("****>>> [Before Advice] logBeforeWithArgs({}, {}) invoked.", s1, s2);
		log.debug("============================================");
		
		
	}//logBeforeWithArgs	
	
	
	@AfterThrowing(pointcut="execution( * org.zerock.myapp.service.*Service.*(..) )", throwing="e")
	public void logExceptionAfterThrowing(Exception e) {
		log.debug("============================================");
		log.debug("****>>> [AfterThrowing Advice] logExceptionAfterThrowing({}) invoked.", e);
		log.debug("============================================");
		
		log.info("\t+ joinPoint's exception : {}", e.getMessage());
	}
	
	
	@AfterReturning(pointcut="execution( * org.zerock.myapp.service.*Service.*(..) )", returning="result")
	public void logAfterReturning(Object result) {
		log.debug("============================================");
		log.debug("****>>> [AfterReturning Advice] logAfterReturning({}) invoked.", result);
		log.debug("============================================");
	}
	
	
	@After("execution( * org.zerock.myapp.service.*Service.*(..) )")
	public void logAfter() {
		log.debug("============================================");
		log.debug("****>>> [After Advice] logAfter() invoked.");
		log.debug("============================================");
	}
	
	
	@Around("execution( * org.zerock.myapp.service.*Service.*(..) )")
	public void logAround(ProceedingJoinPoint pjp) throws Throwable {
		log.debug("============================================");
		log.debug("****>>> [Around Advice] logAround({}) invoked.", pjp);
		log.debug("============================================");
		
		log.info("\t+ pjp : {}", pjp);
		log.info("\t+ type : {}", pjp.getClass().getName());
		
		
		Object target = pjp.getTarget();
		log.info("\t+ target : " + target);
			
		Signature joinPoint = pjp.getSignature();
		log.info("\t 4. joinPoint: {}", joinPoint);
		
		Object[] joinPointArgs = pjp.getArgs();
		log.info("\t 5. joinPointArgs : {}", Arrays.toString(joinPointArgs));
		
		
		//joinPoint 수행, 실행성능 측정, 로그 출력
		
		long start = System.nanoTime();
		
	//		pjp.proceed();		//실행시킬 join point가 무조건 매개변수가 없어야 함 
			pjp.proceed(joinPointArgs); //현실적인 join point의 실행은 이 메소드로 하게 된다!!
		
		long end = System.nanoTime();
		
		log.debug("============================================");
		log.debug("****>>> [Around Advice(**after**)] logAround() invoked.");
		log.debug("============================================");
		
		
		log.info("\t 경과시간 : {}", (end - start) /Math.pow(10, 9) );
	}
	
	
}
