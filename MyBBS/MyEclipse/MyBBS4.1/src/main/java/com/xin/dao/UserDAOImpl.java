package com.xin.dao;



import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xin.db.Druid;
import com.xin.vo.User;

public class UserDAOImpl implements IUserDAO {
	private Connection conn;
	
	public UserDAOImpl() {
		conn = Druid.geteConnection();
	}
	
	@Override
	public User login(User user) {
		String sql = "select * from bbsuser where username=? and password=?";
		PreparedStatement pstmt = null;
		ResultSet result = null;
		User returnUser = null;  //返回给前端的用户对象
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			
			result = pstmt.executeQuery();
			if(result.next()) {
				returnUser = new User();
				returnUser.setUsername(user.getUsername());
				returnUser.setId(result.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return  returnUser;
	}

	@Override
	public boolean register(User user) {
		String sql =" insert into bbsuser(username, password, pic) value(?, ?, ?)";
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
            //将图片添加进数据库
			FileInputStream fs = new FileInputStream(user.getPath());
			pstmt.setBinaryStream(3, fs, fs.available()); //以二进制流的形式传输
			
			flag = pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return flag;
	}

	//通过用户的id来查找用户头像
	@Override
	public byte[] getPic(int id) {
		String sql = " select pic from bbsuser where id=?";
		PreparedStatement pstmt = null;
		ResultSet result = null;
		byte[] buffer = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeQuery();
			if(result.next()) {
				//取出数据库中图片信息
				Blob b = result.getBlob("pic");
				buffer = b.getBytes(1, (int) b.length());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer;
		
	}

	@Override
	public boolean editPageNum(int rowsPerPage, int userid) {
		String sql = "update bbsuser set rowsPerPage = ? where id = ?";
		PreparedStatement pstmt = null;
		boolean flag = false;
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,rowsPerPage);
			pstmt.setInt(2, userid);
			flag = pstmt.executeUpdate()> 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return flag;
	}

}
