package org.zerock.myapp.persistence;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.DepartmentsVO;
import org.zerock.myapp.mapper.TimeMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SqlSessionFactoryTests {
	
	
	@Setter(onMethod_ = { @Autowired })
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setup() {
		log.debug("setup() invoked.");
		
		assertNotNull(this.sqlSessionFactory);
		log.info("sqlSessionFactory : " + this.sqlSessionFactory);
		
	} //setup
	
	
	@Test
	public void textDQL1() {
		log.debug("textDQL1() invoked.");
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try(sqlSession) {
			log.info("\t+ sqlSession : " + sqlSession);
			
			String namespace = "sqlmapper1";
			String sqlId = "DQL1";
			String mappedStatement = namespace + "." + sqlId;
			
			String now = sqlSession.<String>selectOne(mappedStatement);
			log.info("\t+now : " + now);
			
		} //try-with-resources
		
	}//testXXX
	
	//1) MyBatis SQL Mapper XML 파일과 namespace/sqlID를 이용한 방식(**추천**)
	@Test(timeout = 1000)
	public void testDQL2(){
		log.debug("textDQL1() invoked.");
		
		SqlSession session = this.sqlSessionFactory.openSession();
		
		try(session) {
			String namespace = "sqlmapper2";
			String sqlId = "DQL2";
			String mappedStatement = namespace + '.' + sqlId;
			
			Map<String, Object> params = new HashMap<>();
			params.put("deptno", 100);
			params.put("deptname", "Co");
			
			List<DepartmentsVO> depts = session.<DepartmentsVO>selectList(mappedStatement, params);
			
			assertNotNull(depts);
			
			log.info("\t+ type: " + depts.getClass().getName());
			depts.forEach(log::info);
			
		}//try-with-resources
		
	}//testDQL2
	
	
	//2) mybatis interface와 annotaion을 이용한 방식
	@Test
	public void testGetNow() {
		log.debug("testGetNow() invoked.");
		
		SqlSession session = this.sqlSessionFactory.openSession();
		
		try(session) {
			
			TimeMapper mapper = session.<TimeMapper>getMapper(TimeMapper.class);
			assertNotNull(mapper);
			
			log.info("\t+ mapper : " + mapper);
			
			
			Date now = mapper.getNow();
			log.info("\t+ now : " + now);
			
		}//try-with-resources
		
	}//testGetNow
	
	//3. SQL Mapper XML 방식 + MyBatis interface 방식의 결합
	@Test
	public void testGetTime() {
		log.debug("testGetNow() invoked.");
		
		SqlSession session = this.sqlSessionFactory.openSession();
		
		try(session) {
			TimeMapper mapper = session.getMapper(TimeMapper.class);
			log.info("\t+ mapper : " + mapper);
			
			String now = mapper.getTime(); //@Setter어노테이션이 없는 추상메소드
			log.info("\t+ now : " + now);
			
		}//try-with-resources
		
	}//testGetTime
	
	
	
	
	@After
	public void tearDown() {
		log.debug("tearDown() invoked.");
	}//tearDown
	
	
}
