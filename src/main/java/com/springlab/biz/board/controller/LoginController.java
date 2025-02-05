package com.springlab.biz.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.user.domain.UserDO;
import com.springlab.biz.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController implements Controller {
	
	@Autowired
	private UserService userService;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 로그인 처리...");
		
		ModelAndView mv = new ModelAndView();
		String method = request.getMethod();
		
		
		if (method.equalsIgnoreCase("GET")) {
			mv.setViewName("login");
		}
		else if (method.equalsIgnoreCase("POST")) {
			//step 1. get request parameters
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			//step 2. data processing by business logic
			UserDO user = new UserDO();
			user.setId(id);
			user.setPassword(password);
			
			
			user = userService.getUser(user);

			//step 3. output results --> route to views
			if (user != null && password.equals(user.getPassword())) {
				request.getSession().setAttribute("user", user);
				mv.setViewName("redirect:getBoardList");
			}
			else {
				mv.setViewName("redirect:login");
			}
		}
		return mv;
	}

}
