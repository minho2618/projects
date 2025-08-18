package com.service.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.spring.domain.User;
import com.service.spring.service.UserService;

/*
	@RequestMapping 부분은 클라이언트 요청부분을 확인해서
	@GetMapping | @PostMapping으로 수정합니다.
*/
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("memberLogin")
	public ModelAndView login() throws Exception{
	
		
		return new ModelAndView();
	}
	
	@RequestMapping("memberLogout")
	public ModelAndView logout() throws Exception{
		
		
		return new ModelAndView();
	}

}















