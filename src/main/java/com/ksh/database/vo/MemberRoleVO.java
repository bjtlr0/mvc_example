package com.ksh.database.vo;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class MemberRoleVO implements GrantedAuthority{
	private static final long serialVersionUID = -8731993650082615294L;
	private final MemberRole role;
	private final String criteria;

	@Override
	public String getAuthority() {
		return this.role.toString();
	}
}
