package org.web.project.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web.project.domain.BoardVO;
import org.web.project.domain.Criteria;
import org.web.project.mapper.BoardMapper;
import org.web.project.service.BoardService;

import java.util.List;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {


    @Autowired
    private BoardMapper boardMapper;
    @Override
    public void register(BoardVO boardVO) {
        boardMapper.insertSelectKey(boardVO);
    }

    @Override
    public BoardVO get(Long bno) {
        return boardMapper.read(bno);
    }

    @Override
    public boolean modify(BoardVO boardVO) {
        return boardMapper.update(boardVO) == 1;
    }

    @Override
    public boolean remove(Long bno) {
        return boardMapper.delete(bno) == 1;
    }

    @Override
    public List<BoardVO> getList() {
        return boardMapper.getList();
    }

    @Override
    public List<BoardVO> getList(Criteria cri) {
        return boardMapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal(Criteria cri) {
        return boardMapper.getTotalCount(cri);
    }
}
