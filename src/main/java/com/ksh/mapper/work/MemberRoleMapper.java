package com.ksh.mapper.work;

import com.ksh.vo.work.MemberRoleVO;

public interface MemberRoleMapper {
	public MemberRoleVO selectMemberRole(long roleNum);
	public int insertMemberRole(MemberRoleVO memberRole);
}
