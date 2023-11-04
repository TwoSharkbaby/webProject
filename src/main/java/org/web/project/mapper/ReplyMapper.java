package org.web.project.mapper;

import org.apache.ibatis.annotations.Param;
import org.web.project.domain.Criteria;
import org.web.project.domain.ReplyVO;


import java.util.List;

public interface ReplyMapper {

	public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);

	public int getCountByBno(Long bno);
	
	public int insert(ReplyVO reply);

	public ReplyVO read(Long rno);

	public int delete(Long rno);
	
	public int deleteAll(Long bno);

	public int update(ReplyVO reply);
	
	public ReplyVO getReplyById(ReplyVO vo);
	
}
