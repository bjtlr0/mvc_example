package com.ksh.mapper.work;

import java.util.List;

import com.ksh.vo.work.MemberVO;

public interface MemberMapper {
	public int insertMember(MemberVO member);
	public int deleteMember(long id);
	public int updateMember(MemberVO member);
	public MemberVO selectMember(long id);
	public MemberVO selectMemberByUserId(String userId);
	public List<MemberVO> getMemberList();
}
