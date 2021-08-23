package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.domain.AttachFileVO;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ReplyDTO;
import org.zerock.myapp.domain.ReplyVO;

public interface BoardMapper {
	public abstract List<BoardVO> getList();
	public abstract BoardVO readOne(BoardDTO dto);
	
	
	public abstract List<BoardVO> selectWithPaging(Criteria cri);
	public abstract int selectTotalNonNoticeAmount(Criteria cri);
	
	
	
	public abstract int selectTotalAmount(Criteria cri);
	
	
	public abstract boolean updateReadCnt(BoardDTO dto);
	
	
	
	//INSERT REPLY
	public abstract boolean insertReply(ReplyDTO dto);
	//READ REPLY
	public abstract List<ReplyVO> selectReply(ReplyDTO dto);
	//DEL REPLY
	public abstract boolean deleteReply(ReplyDTO dto);

	
	//ANSWER 답글
	public abstract boolean insertAnswer(BoardDTO dto);
	public abstract boolean updateRepStep(BoardDTO dto);
	
	
	//FILE 처리
	public abstract void insertFile(AttachFileDTO dto);
	public abstract void updateFileId(BoardDTO dto);
	public abstract AttachFileVO selectFileById(BoardDTO dto);
	
	public abstract List<BoardVO> getNotice();
	public abstract boolean insert(BoardDTO dto);
	public abstract boolean update(BoardDTO dto);
	public abstract boolean delete(BoardDTO dto);
}
