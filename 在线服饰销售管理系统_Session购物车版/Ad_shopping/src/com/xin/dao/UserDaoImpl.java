package com.xin.dao;

import com.xin.bean.ShopUser;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by golden on 2016/11/4 0004.
 */
public class UserDaoImpl  extends DataBaseImpl implements IUserDao {

    @Override
    public boolean login(String username, String password) {
        try {
            sqlStr = "select * from ad_users where username = ? and password = ?";
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

    @Override
    public List<ShopUser> queryAllUsers() {
        List<ShopUser> userList = new ArrayList<ShopUser>();

        sqlStr = "select id,username,password,realname,sex,email,phone,address,regTime from users;";
        try {
            rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                int userId = rs.getInt(1);
                String userName = rs.getString(2);
                String password = rs.getString(3);
                String realName = rs.getString(4);
                String sex = rs.getString(5);
                String email = rs.getString(6);
                String phone = rs.getString(7);
                String address = rs.getString(8);
                Timestamp regTime = rs.getTimestamp(9);

               ShopUser user = new ShopUser(userId,userName,password,realName,sex,email,phone,address,regTime );
                userList.add(user);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean delUser(int userId) {
        boolean flag = false;
        sqlStr = "delete from users where id = '" + userId + "'";
        try {
            flag = stmt.executeUpdate(sqlStr) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public ShopUser queryUserInfo(int userId) {
        ShopUser user = new ShopUser();

        sqlStr = "select username,password,realname,sex,email,phone,address,regTime from users where id=" + userId;
        try {
            rs = stmt.executeQuery(sqlStr);
            while (rs.next()) {
                String userName = rs.getString(1);
                String password = rs.getString(2);
                String realName = rs.getString(3);
                String sex = rs.getString(4);
                String email = rs.getString(5);
                String phone = rs.getString(6);
                String address = rs.getString(7);
                Timestamp regTime = rs.getTimestamp(8);

                user = new ShopUser(userId,userName,password,realName,sex,email,phone,address,regTime );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean updateUser(ShopUser user) {
        boolean flag = false;

        sqlStr = "update users set username='" + user.getUsername() + "'" +
                ",password='" + user.getPassword() + "'" +
                ", realname='" + user.getRealname() + "'   " +
                ",sex='" + user.getSex() + "'" +
                ",email='" + user.getUserEmail() + "'" +
                ",phone='" + user.getUserPhone() + "'" +
                ",address='" + user.getUserAddress() + "'" +
                "where id = '" + user.getUserid() + "'" ;
        try {
            flag =   stmt.executeUpdate(sqlStr) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


}
