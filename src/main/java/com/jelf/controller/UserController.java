package com.jelf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jelf.entity.DataResult;
import com.jelf.entity.User;
import com.jelf.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@ResponseBody
	@RequestMapping("/register.do")
	public DataResult register(User user) {
		return userService.register(user);
	}

	@ResponseBody
	@RequestMapping(value = "/login.do")
	public DataResult login(User user) {
		return userService.login(user);
	}

}
