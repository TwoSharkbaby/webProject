package org.web.project.mapper;


import org.web.project.domain.AuthVO;
import org.web.project.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO read(String userid);
	
	public int insert(MemberVO member);
	
	public int insertAuth(AuthVO auth);
	
	public String findById(String userid);
}
