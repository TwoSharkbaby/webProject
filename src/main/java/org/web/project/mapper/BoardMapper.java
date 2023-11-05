package org.web.project.mapper;


import org.apache.ibatis.annotations.Param;
import org.web.project.domain.BoardVO;
import org.web.project.domain.Criteria;

import java.util.List;

public interface BoardMapper {

//    @Select("SELECT * FROM system.tbl_board")
    public List<BoardVO> getList();

    public List<BoardVO> getListWithPaging(Criteria cri);

    public int getTotalCount(Criteria cri);

    public void insert(BoardVO boardVO);

    public void insertSelectKey(BoardVO boardVO);

    public BoardVO read(Long bno);

    public int delete(Long bno);

    public int update(BoardVO boardVO);

    public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

}
