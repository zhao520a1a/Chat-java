package com.xin.service;

import com.xin.dao.UserDAOImpl;
import com.xin.vo.User;

public class UserServiceImpl implements IUserService {

	private UserDAOImpl dao = new UserDAOImpl();
	
	public static void main(String[] args) {
		 
	}
	
	@Override
	public boolean login(User user) {
		return dao.login(user);
	}

}
