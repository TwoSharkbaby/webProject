package org.web.project.domain;

import lombok.Data;

import java.util.Date;


@Data
public class ReplyVO {
	private Long rno; // PK
	private Long bno; // FK
	private String reply;
	private String replyer;
	private Date replydate;
	private Date updatedate;
}


