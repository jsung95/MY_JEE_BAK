package org.zerock.myapp.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.LoginDTO;
import org.zerock.myapp.domain.UserVO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@Repository
public class UserDAOImpl implements UserDAO{

	@Setter(onMethod_ = @Autowired)
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public UserVO selectUser(LoginDTO dto) throws Exception {
		log.debug("selectUser({}) invoked.", dto);
		
		Objects.requireNonNull(this.sqlSessionFactory);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try(sqlSession){
			String namespace = "userMapper";
			String sqlId = "selectUser";
			
			String sql = namespace + '.' + sqlId;
			log.info("\t+ sql : {}", sql);
			
			UserVO userVO = sqlSession.<UserVO>selectOne(sql, dto);
			
			log.info("\t+ userVO : {}", userVO);
			
			return userVO;
		}
		
	}

	@Override
	public int updateUserWithRememberMe(String userid, String rememberMe, Date rememberMeMaxAge) throws Exception {
		
		log.debug("updateUserWithRememberMe({}, {}, {}) invoked.", userid, rememberMe, rememberMeMaxAge);
		
		Objects.requireNonNull(this.sqlSessionFactory);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try(sqlSession){
			String namespace = "userMapper";
			String sqlId = "updateUserWithRememberMe";
			
			String sql = namespace + '.' + sqlId;
			log.info("\t+ sql : {}", sql);
			
			
			Map<String, Object> params = new HashMap<>();
			params.put("userid", userid);
			params.put("rememberMe", rememberMe);
			params.put("rememberMeMaxAge", rememberMeMaxAge);
			
			int affectedLines = sqlSession.update(sql, params);
			log.info("\t+ affectedLineds : {}", affectedLines);
			
			return affectedLines;
		}
		
	}

	@Override
	public UserVO selectUserWithRememberMe(String rememberMe) throws Exception {
		
		Objects.requireNonNull(this.sqlSessionFactory);
		
		SqlSession sqlSession = this.sqlSessionFactory.openSession();
		
		try(sqlSession){
			String namespace = "userMapper";
			String sqlId = "selectUserWithRememberMe";
			
			String sql = namespace + '.' + sqlId;
			log.info("\t+ sql : {}", sql);
			
			UserVO userVO = sqlSession.<UserVO>selectOne(sql, rememberMe);
			
			log.info("\t+ userVO : {}", userVO);
			
			return userVO;
		}
	}//selectUser
	
}//end class
