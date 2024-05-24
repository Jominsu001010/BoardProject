package com.springlab.biz.board.controller2;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.springlab.biz")
public class CommonExceptionHandler {
	
	//jsp에 exception을 넘거야해서 model을 파라메타로 받는다.
	@ExceptionHandler(ArithmeticException.class)
	public String handleArithmeticException(Exception ex, Model model) {
		model.addAttribute("excetpion", ex);
		return "error/arithmeticException";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgumentException(Exception ex, Model model) {
		model.addAttribute("excetpion", ex);
		return "error/illegalArgumentException";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex, Model model) {
		model.addAttribute("excetpion", ex);
		return "error/error";
	}
}
