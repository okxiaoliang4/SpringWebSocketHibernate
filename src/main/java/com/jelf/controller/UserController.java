package com.jelf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jelf.annotation.AccessToken;
import com.jelf.entity.DataResult;
import com.jelf.entity.User;
import com.jelf.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private HttpServletRequest request;

	@ResponseBody
	@RequestMapping("/register.do")
	public DataResult register(User user) {
		return userService.register(user);
	}

	@ResponseBody
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public DataResult login(HttpServletRequest request,@RequestBody User user) {
		user.setAccessToken(request.getHeader("accessToken"));
		return userService.login(user);
	}

	@AccessToken
	@ResponseBody
	@RequestMapping(value = "/deleteUser.do")
	public Boolean deleteUser(HttpServletRequest request, @RequestBody User user) {
		return userService.delete(user);
	}

}
