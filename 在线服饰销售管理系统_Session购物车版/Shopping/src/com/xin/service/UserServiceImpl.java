package com.xin.service;

import com.xin.bean.ShopUser;
import com.xin.dao.IUserDao;
import com.xin.dao.UserDaoImpl;

import java.io.UnsupportedEncodingException;

/**
 * Created by golden on 2016/11/5 0005.
 */
public class UserServiceImpl implements IUserService {
    private IUserDao userDao = new UserDaoImpl();

    @Override
    public String register(ShopUser user) {
        String username = user.getUsername();

        if (userDao.userIsExist(username)) {
            return "该用户名已存在！";
        } else if(userDao.insert(user)) {
            Integer userid = userDao.getUserid(username);
            if (userid == null) {
                return "系统繁忙，请稍后注册！";
            } else {
                user.setUserid(userid);
                return "注册成功，返回首页!";
            }
        } else {
            return "系统繁忙，请稍后注册！";
        }
    }

    @Override
    public boolean login(String username,String password) throws UnsupportedEncodingException {
        return  userDao.query(username, password);
    }



}
