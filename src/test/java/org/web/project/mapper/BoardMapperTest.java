package org.web.project.mapper;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.web.project.domain.BoardVO;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j
public class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void testGetList(){
        boardMapper.getList().forEach(log::info);
    }

    @Test
    public void testInsert(){
        BoardVO boardVO = BoardVO.builder().title("새로 작성하는 글").content("새로 작성하는 내용").writer("newbie").build();
        boardMapper.insert(boardVO);
    }

    @Test
    public void testInsertSelectKey(){
        BoardVO boardVO = BoardVO.builder().title("새로 작성하는 글1").content("새로 작성하는 내용1").writer("newbie").build();
        boardMapper.insertSelectKey(boardVO);
    }

    @Test
    public void testRead(){
        log.info(boardMapper.read(6L));
    }

    @Test
    public void testDelete(){
        boardMapper.delete(2L);;
    }

    @Test
    public void testUpdate(){
        BoardVO boardVO = BoardVO.builder().bno(1L).title("새로 작성하는 글1").content("새로 작성하는 내용1").writer("newbie").build();
        boardMapper.update(boardVO);
    }

}