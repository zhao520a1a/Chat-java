package com.xin.dao;

import com.xin.bean.ShopUser;

/**
 * Created by golden on 2016/11/4 0004.
 */
public interface IUserDao  {

    boolean userIsExist(String username);

    boolean insert(ShopUser user);

    boolean query(String username, String password);

    Integer getUserid(String username);
}
