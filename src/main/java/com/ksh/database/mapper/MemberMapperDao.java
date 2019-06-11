package com.ksh.database.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ksh.database.vo.MemberVO;

@Repository("MemberMapperDao")
public class MemberMapperDao implements MemberMapper{
	@Autowired
	@Qualifier("workSqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insertMember(MemberVO member) {
		return sqlSessionTemplate.insert("insertMember", member);
	}

	@Override
	public int deleteMember(int id) {
		return sqlSessionTemplate.delete("deleteMember", id);
	}

	@Override
	public int updateMember(MemberVO member) {
		return sqlSessionTemplate.update("updateMember", member);
	}

	@Override
	public MemberVO selectMember(int id) {
		return sqlSessionTemplate.selectOne("selectMember", id);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return sqlSessionTemplate.selectList("memberList");
	}

	@Override
	public MemberVO selectMemberByUserId(String userName) {
		return sqlSessionTemplate.selectOne("selectMemberByUserId", userName);
	}

}
