package com.xin.service;

import com.xin.bean.ShopUser;

import java.util.List;

/**
 * Created by golden on 2016/11/5 0005.
 */
public interface IUserService {


    boolean login(String username, String password)  ;

    List<ShopUser> queryAllUsers();

    boolean delUser(int userId);

    ShopUser queryUserInfo(int userId);

    boolean updateUser(ShopUser user);
}
