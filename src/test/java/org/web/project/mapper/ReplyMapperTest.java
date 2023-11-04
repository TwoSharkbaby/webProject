package org.web.project.mapper;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.web.project.domain.Criteria;
import org.web.project.domain.ReplyVO;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/root-context.xml"
        ,"file:src/main/webapp/WEB-INF/servlet-context.xml"})
@Log4j
public class ReplyMapperTest {

    private Long[] bnoArr = { 3L, 4L, 5L, 6L, 7L, 9L, 10L };

    @Autowired
    private ReplyMapper replyMapper;

    @Test
    public void testMapper(){
        log.info(replyMapper);
    }

    @Test
    public void tesCreate(){
        IntStream.rangeClosed(1, 10).forEach(value -> {
            ReplyVO vo = new ReplyVO();
            vo.setBno(bnoArr[value % 5]);
            vo.setReply("댓글 테스트" + value);
            vo.setReplyer("replyer" + value);

            replyMapper.insert(vo);
        });
    }

    @Test
    public void tesRead(){
        log.info(replyMapper.read(5L));
    }

    @Test
    public void testDelete(){
        log.info(replyMapper.delete(5L));
    }

    @Test
    public void testUpdate(){
        ReplyVO vo = replyMapper.read(1L);
        vo.setReply("Update Reply");
        log.info(replyMapper.update(vo));
    }

    @Test
    public void testList(){
        Criteria cri = new Criteria(1, 10);
        log.info(replyMapper.getListWithPaging(cri, 4L));
    }
}