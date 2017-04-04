package com.xin.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.xin.db.Druid;
import com.xin.vo.User;

public class UserDAOImpl implements IUserDAO {
	private Connection conn;
	
	public UserDAOImpl() {
		conn = Druid.geteConnection();
	}
	
	@Override
	public boolean login(User user) {
		String sql = "select * from bbsuser where username=? and password=?";
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			flag = pstmt.executeQuery().next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return  flag;
	}

}
