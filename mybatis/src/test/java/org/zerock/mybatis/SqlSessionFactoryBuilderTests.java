package org.zerock.mybatis;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.zerock.mybatis.domain.EmployeesVO;
import org.zerock.mybatis.mapper.Time2Mapper;
import org.zerock.mybatis.mapper.TimeMapper;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
public class SqlSessionFactoryBuilderTests {
	
	private SqlSessionFactoryBuilder builder;
	private SqlSessionFactory factory;
	private String mybatisConfigXml = "mybatis-config.xml";
	
	
	@Before
	public void setup() throws IOException {
		log.debug("setup() invoked.");
		
		// 0. mybatis-config.xml파일에 대한, 바이트 기반 InputStream객체 생
		InputStream inputStream = Resources.getResourceAsStream(mybatisConfigXml);
		
		// 1. SqlSessionFactoryBuilder 생성
		this.builder = new SqlSessionFactoryBuilder();
		log.info("\t+ bulder : " + builder);
		
		// 2. SqlSessionFactory 생성
		this.factory = this.builder.build(inputStream);
		log.info("\t+ factory : " + factory);
		
		
	}//setup
	
	//걍 이거쓰셈;;
	//1) MyBatis SQL Mapper XML 파일과 namespace/sqlID를 이용한 방식(**추천**)
	@Test
	public void testGetTime() {
		log.debug("testGetTime() invoked.");
		
		// AutoClosable한 자원객체 
		SqlSession session = this.factory.openSession();
		log.info("\t+ session : " + session);
		
		try(session) {
			
			//Mybatis에게 SQL 처리를 하라! 라고 명령을 하당!! (이게 어렵기도하고, 핵심이다!)
			String namespace = "Test1";
			String sqlId = "getDept";
			
			String sql = namespace + "." +  sqlId; //Test1.getTime
			
			Map<String, Object> params = new HashMap<>();
			params.put("deptname", "Co");
			params.put("location", 1500);
			List<EmployeesVO> now = session.<EmployeesVO>selectList(sql, params);
			log.info("\t+ now : " + now); 
		}//try-with-resources
		
		//3. SqlSession 생성 
	}//testGetTime
	
	@Test(timeout = 1000)
	public void testGetAllEmployees() {
		log.debug("testGetTime() invoked.");
		
		SqlSession session = this.factory.openSession();
		
		try(session) {
			String namespace = "org.zerock.mybatis";
			String sqlId = "getAllEmployees";
			
			String mappedStmt = namespace + "." + sqlId;
			
			//그리고 언간하면 HashMap으로 바인딩하셈 ;;
//			Map<String, Number> params = new HashMap<>();
//			params.put("NAME1", 150);
//			params.put("NAME2", 5000.0);
				
			//--------------------------------------------------------//
			class Params {
				private Integer name1;
				private Double name2;
				
				public Params(Integer name1, Double name2) {
					this.name1 = name1;
					this.name2 = name2;
				}
				
				public Integer getName1() {
					log.debug("getName1() invoked.");
					return name1;
				}
				
				public Double getName2() {
					log.debug("getName2() invoked.");
					return name2;
				}
				
			};
//			Params params = new Params(150, 5000.0);
			//--------------------------------------------------------//
			
			Object params = new Object() {
				private Integer name1 = 150;
				private Double name2 = 5000.0;
				
				public Integer getName1() {
					return name1;
				}
				
				public Double getName2() {
					return name2;
				}
			};
			
			
			
			List<EmployeesVO> employees = session.<EmployeesVO>selectList(mappedStmt, params);
			
			assertNotNull(employees);
//			Objects.requireNonNull(employees);
//			assert employees != null;
			
			employees.forEach(log::info);
			
			employees.clear();
			employees = null;
			
		}//try-with-resources
		
	}//testGetAllEmployees
	
	
	
	
	@Test
	public void testGetNow() {
		log.debug("testGetNow() invoked.");
		
		SqlSession session = this.factory.openSession();
		
		try(session) {
			//Lvalue(interface) 변수 = Rvalue(interface 구현객체)
			TimeMapper mapper = session.getMapper(TimeMapper.class);
			
			assertNotNull(mapper);
			
			log.info("\t+ mapper : " + mapper);
			String now = mapper.getNow();
			log.info(now);
			
			
		}//try-with-resources
		
	}//testGetNow
	
	
	//2) mybatis interface와 annotaion을 이용한 방식
	@Test
	public void testGetEmplyees() {
		log.debug("testGetEmplyees() invoked.");
		
		SqlSession session = this.factory.openSession();
		
		try(session) {
			//Lvalue(interface) 변수 = Rvalue(interface 구현객체)
			Time2Mapper mapper = session.getMapper(Time2Mapper.class);
			
			assertNotNull(mapper);
			
			log.info("\t+ mapper : " + mapper);
			List<EmployeesVO> emps = mapper.getEmployees(150, 5000.0);
			log.info(emps);
			
			
		}//try-with-resources
		
	}//testGetNow
	
	//------------------------------------------------//
	//3. SQL Mapper XML 방식 + MyBatis interface 방식의 결합 
	//------------------------------------------------//
	@Test
	public void testGetNow3() {
		log.debug("testGetNow3() invoked.");
		
		SqlSession session = this.factory.openSession();
		
		try(session) {
			
			TimeMapper mapper = session.getMapper(TimeMapper.class);
			log.info("\t+ mapper : " + mapper);
			
			assertNotNull(mapper);
			
			Date now = mapper.getNow3();
			log.info("\t+now : " + now);
		}//try-with-resources
		
	}//testGetNow3
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
	}//tearDown
	
}//end class
