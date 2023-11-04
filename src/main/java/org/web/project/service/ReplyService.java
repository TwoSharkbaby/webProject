package org.web.project.service;


import org.web.project.domain.Criteria;
import org.web.project.domain.ReplyPageDTO;
import org.web.project.domain.ReplyVO;

import java.util.List;

public interface ReplyService {
	
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
	
	public List<ReplyVO> getList(Criteria cri, Long bno);
	// CRUD
	public int register(ReplyVO reply);
	
	public ReplyVO get(Long rno);
	
	public int modify(ReplyVO reply);
	
//	public int remove(ReplyVO reply);

	public int remove(Long rno);
	
	
}
