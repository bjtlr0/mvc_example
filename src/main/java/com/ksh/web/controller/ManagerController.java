package com.ksh.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@RequestMapping(value="/test" ,method=RequestMethod.GET)
	public ModelAndView test(ModelAndView mav){
		mav.setViewName("basic/basic");
		System.out.println("리턴형이 ModelAndView인 경우 RequestMapping URL경로와 별도로 ViewName과 Model을 지정할 수 있다.");
		return mav;
	}
	
	@RequestMapping(value="/mngr", method=RequestMethod.GET)
	public Model mngr(Model m, Principal p){
		System.out.println("리턴형이 Model인 경우 RequestMapping URL경로가 view의 이름이 된다.");
		return m;
	}
	
	/**
	 * 이제 RequestFilter를 알아볼 때다.
	 * */
}
