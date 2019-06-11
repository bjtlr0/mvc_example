package com.ksh.database.mapper;

import com.ksh.database.vo.MemberRoleVO;

public interface MemberRoleMapper {
	public MemberRoleVO selectMemberRole(String role);
	public int insertMemberRole(MemberRoleVO memberRole);
}
