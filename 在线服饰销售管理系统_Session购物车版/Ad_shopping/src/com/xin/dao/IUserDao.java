package com.xin.dao;

import com.xin.bean.ShopUser;

import java.util.List;

/**
 * Created by golden on 2016/11/4 0004.
 */
public interface IUserDao  {

    boolean login(String username, String password);

    List<ShopUser> queryAllUsers();

    boolean delUser(int userId);

    ShopUser queryUserInfo(int userId);

    boolean updateUser(ShopUser user);
}
