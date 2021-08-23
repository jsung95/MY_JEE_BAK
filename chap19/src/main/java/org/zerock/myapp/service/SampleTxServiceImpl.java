package org.zerock.myapp.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.mapper.Sample1Mapper;
import org.zerock.myapp.mapper.Sample2Mapper;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class SampleTxServiceImpl implements SampleTxService {

	
	@Autowired private Sample1Mapper mapper1;
	@Autowired private Sample2Mapper mapper2;
	
	
	

	   
//  @Transactional(propagation=Propagation.REQUIRED)      // default
//  @Transactional(propagation=Propagation.MANDATORY)      // 반드시 특정 TX 하에서만 수행가능
//  @Transactional(propagation=Propagation.NOT_SUPPORTED)   // 기존 TX가 있는 경우, 이 TX이 끝날 때까지 보류한 후, 수행
//  @Transactional(propagation=Propagation.SUPPORTS)      // TX가 필요없으나, TX 하에 있다면 포함되어 실행
//  @Transactional(propagation=Propagation.REQUIRED)      // 기존 Tx가 있는 경우 사용, 없으면 새로운 TX 생성하여 실행
//  @Transactional(propagation=Propagation.REQUIRES_NEW)   // 무조건 자신만의 고유한 TX 생성하여 실행
//  @Transactional(propagation=Propagation.NESTED)         // 기존 TX 이 존재하는 경우, 이 Tx에 포함되어 수행
//  @Transactional(propagation=Propagation.NEVER)         // TX 없이 수행. 만일 TX하에서 수행하면 오류발생
	
	
	
	@Transactional //아래의 메소드 수행을 트랜잭션 처리하겠다!!
	@Override
	public void addData(String value) throws Exception {
		
		log.debug("addData({}) invoked.", value);
		
		Objects.requireNonNull(this.mapper1);
		Objects.requireNonNull(this.mapper2);
		
		
		//2개의 테이블을, 2개의 Connection으로 Insert (DML) 수행
		// 2개의 테이블에 대한 DML 작업이 원자성을 가져야 한다!!! (트랜젝션처리되어야 한다!!!) 
		//ALL of Nothing 처리 (원자성)가 되어야 한다!!!
		this.mapper1.insertCol(value);
		this.mapper2.insertCol(value);
		
		log.info("Success");
		
		
	}//addData

}//end class
