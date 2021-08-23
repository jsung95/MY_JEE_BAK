package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;


// tbl_board 테이블의 목록 + CRUD메소드를 선언 
public interface BoardMapper {
	
//	@Select("SELECT /*+ index_desc(tbl_board) */ * FROM tbl_board WHERE bno > 0")
//	@Select("SELECT * FROM tbl_board WHERE bno > 0")
	public abstract List<BoardVO> getList(); //게시판 목록조회
	
	
	//============================================================================================//
	public abstract List<BoardVO> getListWithPaging(Criteria cri);      //페이징 처리된 게시판 목록 조회 //
	//============================================================================================//
	
	
	
	public abstract BoardVO read(Integer bno); // 특정 게시글 조회
	
	public abstract Integer delete(Integer bno); //특정게시글삭제 
	
	public abstract int insert(BoardVO board); //게시글작성 
	public abstract int insertSelectKey(BoardVO board);
	
	
	public abstract int update(BoardVO board); //게시글 수정 
	
}//end class
