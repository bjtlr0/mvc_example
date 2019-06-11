package com.ksh.web.component;

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

import com.ksh.database.service.MemberService;
import com.ksh.database.vo.MemberVO;

@Component
public class MemberAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private MemberService memberService;
	
	/*@Autowired*/
	private BCryptPasswordEncoder passwordEncoder;
	
	public MemberAuthenticationProvider(){
		passwordEncoder = new BCryptPasswordEncoder();
	}
	
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
		return true;
	}

}
