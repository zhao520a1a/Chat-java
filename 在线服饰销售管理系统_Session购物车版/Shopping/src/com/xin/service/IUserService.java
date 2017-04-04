package com.xin.service;

import com.xin.bean.ShopUser;

import java.io.UnsupportedEncodingException;

/**
 * Created by golden on 2016/11/5 0005.
 */
public interface IUserService {

     String register(ShopUser user);

    boolean login(String username, String password) throws UnsupportedEncodingException;
}
