package org.koreait.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("org.koreait.controllers")	//공통 적용, 범위 지정(패키지)
public class CommonController {
	@ExceptionHandler(RuntimeException.class)
	public String errorHandler(RuntimeException e, Model model){
		e.printStackTrace();

		model.addAttribute("message", e.getMessage());

		return "error/common";
	}
}
