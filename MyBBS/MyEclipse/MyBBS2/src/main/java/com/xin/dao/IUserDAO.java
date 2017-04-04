package com.xin.dao;

import com.xin.vo.User;

public interface IUserDAO {
	User login(User user);
	boolean register(User user);
	byte[] getPic(int id);
}
