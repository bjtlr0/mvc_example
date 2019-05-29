package com.ksh.mapper.engine;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ksh.vo.engine.SigPSRuleVO;

@Repository("SigPSRuleMapperDao")
public class SigPSRuleMapperDao implements SigPSRuleMapper{
	@Autowired
	@Qualifier("engineSqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public SigPSRuleVO getSigPSRule(long id) {
//		SigPSRuleMapper mapper = sqlSessionTemplate.getMapper(SigPSRuleMapper.class);
		/**
		 * mapper가 등록되어 있으므로 Template를 통해서 mapper에 등록된 id로 호출이 가능하다. 
		 * */
		return sqlSessionTemplate.selectOne("getSigPSRule", id);  // sqlSessionTemplate가 db연결,쿼리수행,db연결종료를 수행하며 트렌젝션의 안정성을 부여한다
	}
	
	@Override
	public List<SigPSRuleVO> getSigPSRuleList() {
		//SigPSRuleMapper mapper = sqlSessionTemplate.getMapper(SigPSRuleMapper.class);
		//List<SigPSRuleVO> list = mapper.getSigPSRuleList();
		List<SigPSRuleVO> list = sqlSessionTemplate.selectList("getSigPSRuleList");
		return list;
	}

}
