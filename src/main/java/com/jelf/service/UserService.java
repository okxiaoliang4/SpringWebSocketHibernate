package com.jelf.service;

import com.jelf.entity.DataResult;
import com.jelf.entity.User;

public interface UserService {

	public DataResult register(User user);

	public DataResult findAll();

	public DataResult login(User user);

	public Boolean delete(User user);

	public User verificationAccessToken(String accessToken);

}