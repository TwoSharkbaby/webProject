package org.web.project.security;


import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.web.project.domain.MemberVO;
import org.web.project.mapper.MemberMapper;
import org.web.project.security.domain.CustomUser;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn("Load user By UserName : " + username);

		MemberVO vo = memberMapper.read(username);

		return vo == null ? null : new CustomUser(vo);
	}




}
