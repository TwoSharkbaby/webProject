package org.web.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.web.project.domain.BoardVO;

import java.util.List;

public interface BoardMapper {

//    @Select("SELECT * FROM system.tbl_board")
    public List<BoardVO> getList();

    public void insert(BoardVO boardVO);

    public void insertSelectKey(BoardVO boardVO);

    public BoardVO read(Long bno);

    public int delete(Long bno);

    public int update(BoardVO boardVO);
}
