package com.springlab.biz.board.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 로그아웃 처리...");
		// step #1. get request parameters
		
		// step #2. data processing
		ModelAndView mv = new ModelAndView("redirect:login");
		
		request.getSession().invalidate();
		
		// step #3. output results
		return mv;
	}

}
