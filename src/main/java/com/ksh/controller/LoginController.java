package com.ksh.controller;

import java.security.Principal;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ksh.service.MemberService;

@Controller
@RequestMapping("/loginPage")
public class LoginController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping(params="error")
	public ModelAndView reqLoginError(ModelAndView mav, HttpServletRequest req){
		mav.setViewName("html/customLogin");
		mav.addObject("q-user-name", req.getParameter("q-user-name"));
		return mav;
	}
	
	@RequestMapping(params="logout")
	@ResponseBody
	public String reqLogout(){
		System.out.println("Logout Complete");
		return ("html/customLogin");
	}
	
	// Authentication 객체를 받아서 세션의 인증여부를 확인할 수 있다.
	@RequestMapping(value="default")
	public ModelAndView reqDefault(ModelAndView mav, Authentication authentication){
		boolean result = memberService.authenticationTest(authentication);
		if(result){
			mav.setViewName("html/index");	
		}else{
			mav.setViewName("/logout");
		}
		return mav;
	}
	
	@RequestMapping(value="customLogin")
	public String loginPage(){
		return "html/customLogin";
	}
}
