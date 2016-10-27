package com.jelf.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jelf.entity.User;
import com.jelf.exception.AccessTokenException;
import com.jelf.service.UserService;

@Component
@Aspect
public class AccessTokenAop {
	@Autowired
	UserService userService;

	// 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(* com.jelf.controller..*(..))")
	public void aspect() {
	}

	// 设置以自定义annotation作为切入点

	@Before("@annotation(com.jelf.annotation.AccessToken)")
	public void checkAccessToken(JoinPoint joinPoint) throws Exception {
		// 获取拦截的请求参数
		Object[] args = joinPoint.getArgs();
		HttpServletRequest request = (HttpServletRequest) args[0];
		String accessToken = request.getHeader("accessToken");
		if (accessToken == null) {
			throw new AccessTokenException("Access Token is NUll(该令牌为空值)");
		}
		User user = userService.verificationAccessToken(accessToken);
		if (user.getId() == null) {
			throw new AccessTokenException(
					"Access Token not found(该登录令牌不存在,未登录)");
		}
	}

}