package com.ksh.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public Model test(Model m,Principal p){
		System.out.println("ADMIN role");
		return m;
	}
}
