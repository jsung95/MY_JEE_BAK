package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.AttachFileDTO;
import org.zerock.myapp.domain.MemberDTO;

public interface RegisterMapper {

	public abstract int selectCheckCBNO(MemberDTO dto);
	public abstract void insertMember(MemberDTO dto);
	
	
	//파일처리
	public abstract void insertFile(AttachFileDTO dto);
	
}
