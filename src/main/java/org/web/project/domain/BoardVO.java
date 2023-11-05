package org.web.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {

    private Long bno;
    private String title;
    private String content;
    private String writer;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date regdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedate;

    private int replyCnt;

    private List<BoardAttachVO> attachList;

}
