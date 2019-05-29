package com.ksh.mapper.work;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ksh.vo.work.MemberVO;

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
	public int deleteMember(long id) {
		return sqlSessionTemplate.delete("deleteMember", id);
	}

	@Override
	public int updateMember(MemberVO member) {
		return sqlSessionTemplate.update("updateMember", member);
	}

	@Override
	public MemberVO selectMember(long id) {
		return sqlSessionTemplate.selectOne("selectMember", id);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return sqlSessionTemplate.selectList("memberList");
	}

	@Override
	public MemberVO selectMemberByUserId(String userId) {
		return sqlSessionTemplate.selectOne("selectMemberByUserId", userId);
	}

}
