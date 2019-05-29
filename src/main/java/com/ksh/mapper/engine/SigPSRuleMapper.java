package com.ksh.mapper.engine;

import java.util.List;

import com.ksh.vo.engine.SigPSRuleVO;

public interface SigPSRuleMapper {
	// @Select("select id, type, risk, method, signature, res_code, res_header, res_body from sig_psrule where id=(#{id})")
	public SigPSRuleVO getSigPSRule(long id);
	
	// @Select("select id, type, risk, method, signature, res_code, res_header, res_body from sig_psrule")
	public List<SigPSRuleVO> getSigPSRuleList();
}
