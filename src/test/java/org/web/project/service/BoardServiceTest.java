package org.web.project.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.web.project.domain.BoardVO;
import org.web.project.domain.Criteria;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testExist(){
        log.info(boardService);
        assertNotNull(boardService);
    }

    @Test
    public void testGetList(){
        boardService.getList().forEach(log::info);
    }

    @Test
    public void testInsertSelectKey(){
        BoardVO boardVO = BoardVO.builder().title("새로 작성하는 글").content("새로 작성하는 내용").writer("newbie").build();
        boardService.register(boardVO);
    }

    @Test
    public void testRead(){
        log.info(boardService.get(6L));
    }

    @Test
    public void testDelete(){
        boardService.remove(2L);;
    }

    @Test
    public void testUpdate(){
        BoardVO boardVO = BoardVO.builder().bno(1L).title("새로 작성하는 글1").content("새로 작성하는 내용1").writer("newbie").build();
        boardService.modify(boardVO);
    }

    @Test
    public void testGetList2(){
        boardService.getList(new Criteria(2, 10)).forEach(log::info);
    }
}