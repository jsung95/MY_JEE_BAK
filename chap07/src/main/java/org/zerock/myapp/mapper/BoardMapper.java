package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.BoardVO;

public interface BoardMapper {
	
	@Select("SELECT /*+ index_desc(tbl_board) */ * FROM tbl_board WHERE bno > 0")
//	@Select("SELECT * FROM tbl_board WHERE bno > 0")
	public abstract List<BoardVO> getList();
	
}//end class
