package com.ksh.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksh.mapper.engine.SigPSRuleMapperDao;

/**
 * service가 dao의 역할을 한다.
 * 여러개의 dao를 호출해야 하는 경우 service를 구현하는게 일반적이다.
 * Service는 Transaction단위의 dao다.
 * 여기서 db작업을 수행한 후 전달하는게 좋다.
 * */
@Service
public class EngineDBService {
	@Autowired
	private SigPSRuleMapperDao sigRuleMapperDao;
	
	@Transactional // 기본적으로 확인되지 않은 Exception 발생 시 Rollback하도록 한다.
	public void transactionTest(boolean exceptionFlag, Authentication authentication){
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			// 인증된 token을 가졌다면
			String userName = authentication.getName();
			
		}
		// blar;;
	}
}
