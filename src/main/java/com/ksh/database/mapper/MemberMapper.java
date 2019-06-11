package com.ksh.database.mapper;

import java.util.List;

import com.ksh.database.vo.MemberVO;

public interface MemberMapper {
	public int insertMember(MemberVO member);
	public int deleteMember(int id);
	public int updateMember(MemberVO member);
	public MemberVO selectMember(int id);
	public MemberVO selectMemberByUserId(String userName);
	public List<MemberVO> getMemberList();
}
