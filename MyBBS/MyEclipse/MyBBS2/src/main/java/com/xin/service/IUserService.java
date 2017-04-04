package com.xin.service;

import javax.servlet.http.HttpServletRequest;

import com.xin.vo.User;

public interface IUserService {
	User login(User user);
	boolean register(User user);
	User uploadPic(HttpServletRequest request);
	byte[] getPic(int id);
}
