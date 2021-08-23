package org.zerock.myapp.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TTT {
	
	@Select("SELECT sysdate FROM dual")
	public abstract String getMyName();
	
	
	public abstract Double getSalary(@Param("empid") Integer myEmpId, 
									 @Param("email") String myEmail);
	
}
