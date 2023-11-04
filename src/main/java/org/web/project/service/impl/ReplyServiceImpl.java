package org.web.project.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web.project.domain.Criteria;
import org.web.project.domain.ReplyPageDTO;
import org.web.project.domain.ReplyVO;
import org.web.project.mapper.BoardMapper;
import org.web.project.mapper.ReplyMapper;
import org.web.project.service.ReplyService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapper replyMapper;
    private final BoardMapper boardMapper;

    @Override
    public ReplyPageDTO getListPage(Criteria cri, Long bno) {
        // work
        return new ReplyPageDTO(replyMapper.getCountByBno(bno), replyMapper.getListWithPaging(cri, bno));
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {
        // work
        log.info("......getList Cri =" + cri);
        log.info("......getList bno =" + bno);
        return replyMapper.getListWithPaging(cri, bno);
    }

    @Transactional
    @Override
    public int register(ReplyVO reply) {
        log.info(".......register = " + reply);
//		boardMapper.updateReplyCnt(reply.getBno(), 1);
        return replyMapper.insert(reply);
    }

    @Override
    public ReplyVO get(Long rno) {

        log.info(".........get = " + rno);
        return replyMapper.read(rno);
    }

    @Override
    public int modify(ReplyVO reply) {
        log.info("........modify = " + reply);
        return replyMapper.update(reply);
    }

//	@Transactional
//	@Override
//	public int remove(ReplyVO vo) {
//		log.info("..........remove = " + vo.getRno());
//		ReplyVO reply = replyMapper.getReplyById(vo);
//		if(reply != null) {
//			boardMapper.updateReplyCnt(reply.getBno(), -1);
//			return replyMapper.delete(reply.getRno());
//		}
//		return 0;
//	}

    @Transactional
    @Override
    public int remove(Long rno) {
        return replyMapper.delete(rno);
    }


}




