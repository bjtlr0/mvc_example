package com.ksh.controller;

import java.security.Principal;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ksh.mapper.engine.SigPSRuleMapperDao;
import com.ksh.vo.engine.SigPSRuleVO;

@Controller
public class DefaultController {
	@RequestMapping("/")
	public String defaultIndex(){
		return "html/index";
	}
	/*
	 *
	 * pathVariable 예제로 놔둔다 .
	 */ 
	/*@Autowired
	private SigPSRuleMapperDao ruleDao;
	
	@RequestMapping(value="/services/{id}", method=RequestMethod.GET) 
	public ModelAndView serviceTest (ModelAndView mav, @PathVariable String id){ // Model을 임의로 지정할 경우
		try{
			SigPSRuleVO rule = ruleDao.getSigPSRule(Long.parseLong(id));
			if (rule != null){
				mav.addObject(rule);
			}else {
				// id로 찾을 수 없음.
			}
		}catch(Exception ex){
			// blar
			System.out.println(ex.getMessage());
		}
		mav.setViewName("service");
		return mav;
	}*/
}
