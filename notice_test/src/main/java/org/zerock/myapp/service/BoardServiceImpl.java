package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.domain.AttachFileVO;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.Criteria;
import org.zerock.myapp.domain.ReplyDTO;
import org.zerock.myapp.domain.ReplyVO;
import org.zerock.myapp.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BoardServiceImpl implements BoardService{

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Override
	public List<BoardVO> readAll() {
		
		return mapper.getList();
	}

	@Override
	public BoardVO readOne(BoardDTO dto) {
		readCnt(dto);
		return mapper.readOne(dto);
	}

	@Override
	public boolean register(BoardDTO dto) {
		log.debug("register_service invoked.");
		boolean isSuccess = this.mapper.insert(dto);
		return isSuccess;
	}

	@Override
	public boolean modify(BoardDTO dto) {
		boolean isSuccess = this.mapper.update(dto);
		return isSuccess;
	}

	@Override
	public boolean remove(BoardDTO dto) {
		boolean isSuccess = this.mapper.delete(dto);
		return isSuccess;
	}

	@Override
	public List<BoardVO> readNotice() {
		return this.mapper.getNotice();
	}

	@Override
	public List<BoardVO> getListPerPage(Criteria cri) {
		
		return this.mapper.selectWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		
		return this.mapper.selectTotalAmount(cri);
	}

	@Override
	public int getTotalNonNotice(Criteria cri) {
		return this.mapper.selectTotalNonNoticeAmount(cri);
	}

	@Override
	public boolean readCnt(BoardDTO dto) {
		
		boolean isSuccess = this.mapper.updateReadCnt(dto);
		return isSuccess;
	}

	
    //=============================================
	// REPLY
	//=============================================
	@Override
	public boolean writeReply(ReplyDTO dto) {
		
		boolean isSuccess = this.mapper.insertReply(dto);
		
		return isSuccess;
	}

	@Override
	public List<ReplyVO> getReply(ReplyDTO dto) {

		List<ReplyVO> list = this.mapper.selectReply(dto);
		
		return list;
	}
	

	@Override
	public boolean removeReply(ReplyDTO dto) {
		
		boolean isSuccess = this.mapper.deleteReply(dto);
		
		return isSuccess;
	}

    //=============================================
	// Answer ??????
	//=============================================
	@Override
	public boolean updateAnswer(BoardDTO dto) {
		return this.mapper.updateRepStep(dto);
	}
	
	@Override
	public boolean writeAnswer(BoardDTO dto) {
		updateAnswer(dto);
		boolean isSuccess = this.mapper.insertAnswer(dto);
		
		return isSuccess;
	}

	
	
	//FILE ?????? 
	@Override
	public void uploadFile(AttachFileDTO dto) {
		this.mapper.insertFile(dto);
		
	}

	@Override
	public void addFileId(BoardDTO dto) {
		log.debug("#@#@#@#@#@# >> : invoke.d");
		this.mapper.updateFileId(dto);
		
	}

	@Override
	public AttachFileVO getFileById(BoardDTO dto) {
		
		AttachFileVO file = this.mapper.selectFileById(dto);
		
		return file;
	}





	
	
}
