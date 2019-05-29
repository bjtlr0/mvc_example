package com.ksh.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controller는 dao객체를 직접 불러 사용하기보다
 * service객체(비즈니스 로직)를 불러서 사용하는게 맞다.
 * */

@Controller
public class CustomErrorController implements ErrorController{

	@RequestMapping(value="/error")
	public String error(HttpServletRequest request, HttpServletResponse response){
		String errorPath = "";
		String status = String.valueOf(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
		if (status.equalsIgnoreCase(HttpStatus.NOT_FOUND.toString())){
			// 404
			errorPath = "html/error_404";
		}else if(status.equalsIgnoreCase(HttpStatus.INTERNAL_SERVER_ERROR.toString())){
			// 500
			errorPath = "html/error_500";
		}else{
			errorPath = "html/error_default";
			// blar
		}
		
		response.setStatus(200); // 이런식으로 response에 직접 status를 지정해서 원래 에러코드를 감출 수 있다.
		
		return errorPath;
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
