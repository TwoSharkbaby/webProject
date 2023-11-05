package org.web.project.service;

import org.web.project.domain.BoardAttachVO;
import org.web.project.domain.BoardVO;
import org.web.project.domain.Criteria;

import java.util.List;

public interface BoardService {

    public void register(BoardVO boardVO);

    public BoardVO get(Long bno);

    public boolean modify(BoardVO boardVO);

    public boolean remove(Long bno);

    public List<BoardVO> getList();

    public List<BoardVO> getList(Criteria cri);

    public int getTotal(Criteria cri);

    public List<BoardAttachVO> getAttachList(Long bno);


}
