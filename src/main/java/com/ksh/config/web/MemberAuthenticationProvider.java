package com.ksh.config.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ksh.service.MemberService;
import com.ksh.vo.work.MemberVO;

public class MemberAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = (String)authentication.getCredentials();
		
		MemberVO m = null;
		Collection<? extends GrantedAuthority> authorities = null;
		try{
			m = (MemberVO)memberService.loadUserByUsername(userName);
			if (!passwordEncoder.matches(password, m.getPassword())){
				throw new BadCredentialsException("password mismatch");
			}
			authorities = m.getAuthorities();
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
		return new UsernamePasswordAuthenticationToken(userName, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
