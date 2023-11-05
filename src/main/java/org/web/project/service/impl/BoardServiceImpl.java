package org.web.project.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web.project.domain.BoardAttachVO;
import org.web.project.domain.BoardVO;
import org.web.project.domain.Criteria;
import org.web.project.mapper.BoardAttachMapper;
import org.web.project.mapper.BoardMapper;
import org.web.project.mapper.ReplyMapper;
import org.web.project.service.BoardService;

import java.util.List;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private BoardAttachMapper attachMapper;

    @Autowired
    private  ReplyMapper replyMapper;

    @Transactional
    @Override
    public void register(BoardVO boardVO) {
        boardMapper.insertSelectKey(boardVO);
        if (boardVO.getAttachList() == null || boardVO.getAttachList().size() <= 0){
            return;
        }
        boardVO.getAttachList().forEach(attach -> {
            attach.setBno(boardVO.getBno());
            attachMapper.insert(attach);
        });
    }

    @Override
    public BoardVO get(Long bno) {
        return boardMapper.read(bno);
    }

    @Transactional
    @Override
    public boolean modify(BoardVO boardVO) {
        attachMapper.deleteAll(boardVO.getBno());
        boolean modifyResult = boardMapper.update(boardVO) == 1;
        if (modifyResult && boardVO.getAttachList() != null && boardVO.getAttachList().size() > 0){
            boardVO.getAttachList().forEach(attach -> {
                attach.setBno(boardVO.getBno());
                attachMapper.insert(attach);
            });
        }
        return modifyResult;
    }

    @Transactional
    @Override
    public boolean remove(Long bno) {
        attachMapper.deleteAll(bno);
        replyMapper.deleteAll(bno);
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

    @Override
    public List<BoardAttachVO> getAttachList(Long bno) {
        return attachMapper.findByBno(bno);
    }
}
