package com.ksh.mapper.work;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ksh.vo.work.MemberRoleVO;

@Repository
public class MemberRoleMapperDao implements MemberRoleMapper{
	@Autowired
	@Qualifier("workSqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public MemberRoleVO selectMemberRole(long roleNum) {
		return sqlSessionTemplate.selectOne("selectMemberRole", roleNum);
	}

	@Override
	public int insertMemberRole(MemberRoleVO memberRole) {
		return sqlSessionTemplate.insert("insertMemberRole", memberRole);
	}
}
