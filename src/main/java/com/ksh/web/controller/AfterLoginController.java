package com.ksh.web.controller;

import java.security.Principal;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AfterLoginController {
	@RequestMapping("/afterLogin.do")
	public String afterLogin(Principal p, Authentication a){
		// 뭐 이런거 하면 된다.. 로그인 제대로 됐는지, 후에 확인할 정보들..
		String userName = p.getName();
		a.getAuthorities();
		return "html/afterLogin";
	}
}
