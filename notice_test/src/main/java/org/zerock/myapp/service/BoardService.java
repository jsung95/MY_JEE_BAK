package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.domain.AttachFileVO;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ReplyDTO;
import org.zerock.myapp.domain.ReplyVO;

public interface BoardService {
	public abstract List<BoardVO> readAll();
	public abstract BoardVO readOne(BoardDTO dto);
	
	
	public abstract List<BoardVO> getListPerPage(Criteria cri);
	public abstract int getTotalNonNotice(Criteria cri);
	
	public abstract int getTotal(Criteria cri);
	
	
	public abstract boolean readCnt(BoardDTO dto);
	
	
	//INSERT REPLY
	public abstract boolean writeReply(ReplyDTO dto);
	//READ REPLY
	public abstract List<ReplyVO> getReply(ReplyDTO dto);
	//DEL REPLY
	public abstract boolean removeReply(ReplyDTO dto);

	
	
	//Answer 답변
	public abstract boolean updateAnswer(BoardDTO dto);
	public abstract boolean writeAnswer(BoardDTO dto);
	
	
	//FILE 처리 
	public abstract void uploadFile(AttachFileDTO dto);
	public abstract void addFileId(BoardDTO dto);
	public abstract AttachFileVO getFileById(BoardDTO dto);
	
	
	
	public abstract List<BoardVO> readNotice();
	public abstract boolean register(BoardDTO dto);
	public abstract boolean modify(BoardDTO dto);
	public abstract boolean remove(BoardDTO dto);
}
