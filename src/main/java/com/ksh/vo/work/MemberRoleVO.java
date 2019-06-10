package com.ksh.vo.work;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class MemberRoleVO implements GrantedAuthority{
	private final MemberRole role;
	private final String description;
	private final String criteria;

	@Override
	public String getAuthority() {
		return this.role.toString();
	}
}
