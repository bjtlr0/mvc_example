package com.ksh.vo.work;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class MemberRoleVO implements GrantedAuthority{
	private long roleNum;
	private MemberRole role;
	
	@Override
	public String getAuthority() {
		return this.role.toString();
	}
}
