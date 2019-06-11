package com.ksh.database.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ksh.database.vo.MemberRoleVO;

@Repository
public class MemberRoleMapperDao implements MemberRoleMapper{
	@Autowired
	@Qualifier("workSqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int insertMemberRole(MemberRoleVO memberRole) {
		return sqlSessionTemplate.insert("insertMemberRole", memberRole);
	}

	@Override
	public MemberRoleVO selectMemberRole(String role) {
		return sqlSessionTemplate.selectOne("selectMemberRole", role);
	}
}
