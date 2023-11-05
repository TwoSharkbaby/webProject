package org.web.project.mapper;


import org.web.project.domain.BoardAttachVO;

import java.util.List;

public interface BoardAttachMapper {
	
	public List<BoardAttachVO> getOldFiles();
	
	public void insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO> findByBno(Long bno);
	
	public void deleteAll(Long bno);

}







