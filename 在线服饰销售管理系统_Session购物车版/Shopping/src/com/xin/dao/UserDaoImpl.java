package com.xin.dao;
import com.xin.bean.ShopUser;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by golden on 2016/11/4 0004.
 */
public class UserDaoImpl  extends DataBaseImpl implements IUserDao {

    @Override
    public boolean userIsExist(String username) {
        try {
            sqlStr = "select id from users where username='"+ username +"'";
            rs = stmt.executeQuery(sqlStr);
            if(rs.next()) {
                    rs.close();
                    return true;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Integer getUserid(String username) {
        Integer userid = null ;  //没有找到，则返回null；
        //sqlStr = "select id from users where username=" + username;  //Unknown column 'å•Šå•Š' in 'where clause'
        sqlStr = "select id from users where username='"+ username +"'";
        try {;
            rs = stmt.executeQuery(sqlStr);
            while(rs.next()) {
                userid = rs.getInt(1);
            }
            rs.close();
            return userid;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userid;
    }

    @Override
    public boolean insert(ShopUser user) {
        sqlStr = "insert into users(username,password,realname,sex,email,phone,address,regTime) values(?,?,?,?,?,?,?,?)";
        try {
            prepstmt = conn.prepareStatement(sqlStr);
            prepstmt.setString(1,user.getUsername());
            prepstmt.setString(2,user.getPassword());
            prepstmt.setString(3,user.getRealname());
            prepstmt.setString(4,user.getSex());
            prepstmt.setString(5,user.getUserEmail());
            prepstmt.setString(6,user.getUserPhone());
            prepstmt.setString(7,user.getUserAddress());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            prepstmt.setTimestamp(8, timestamp );
            if(prepstmt.executeUpdate() > 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean query(String username, String password) {
        try {
            sqlStr = "select * from users where username = ? and password = ?";
            prepstmt = conn.prepareStatement(sqlStr);
            prepstmt.setString(1,username);
            prepstmt.setString(2,password);
            rs = prepstmt.executeQuery();
            if (rs.next()) {
                rs.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
