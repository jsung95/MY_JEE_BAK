package org.zerock.myapp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface Sample1Mapper {

	@Insert("INSERT INTO tbl_sample1 (col) VALUES ( #{col} )")
	public abstract int insertCol(@Param("col") String data);
	
	
} // end interface
