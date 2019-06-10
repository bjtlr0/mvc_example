package com.ksh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksh.mapper.work.MemberMapperDao;
import com.ksh.vo.work.MemberVO;

@Service
public class MemberService implements UserDetailsService {
	
	@Autowired
	MemberMapperDao memberDao;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		MemberVO member = memberDao.selectMemberByUserId(userId);
		if(member == null){
			throw new UsernameNotFoundException(userId + ", UserName의 사용자 정보를 알 수 없습니다.");
		}
		return member;
	}
	
	@Transactional // 기본적으로 확인되지 않은 Exception 발생 시 Rollback하도록 한다.
	public boolean authenticationTest (Authentication authentication){
		boolean result = false;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// 인증된 token을 가졌다면
			String userName = authentication.getName();
			System.out.println("username : " + userName);
			result = true;
		}else{
			System.out.println("anonymousAuthentication Token");
		}
		return result;
	}
}
