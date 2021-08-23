package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;

public interface BoardService {
	
	public abstract List<BoardVO> getList();	//게시글 목록 조회 
	
	public abstract List<BoardVO> getListPerPage(Criteria cri); //한 페이지 분량만큼만 목록을 조회하자!
	
	public abstract boolean register(BoardVO board);	// 새로운 게시글 등록 
	public abstract boolean modify(BoardVO board);	//게시글 수정
	public abstract boolean remove(Integer bno); //기존 게시글 삭제
	public abstract BoardVO get(Integer bno); //기존 게시글의 상세조회 
	
	public abstract int getTotal(Criteria cri); //총 게시글 수를 조회 
	
}
