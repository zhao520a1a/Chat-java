package com.xin.service;

import com.xin.bean.ShopUser;
import com.xin.dao.IUserDao;
import com.xin.dao.UserDaoImpl;

import java.util.List;

/**
 * Created by golden on 2016/11/5 0005.
 */
public class UserServiceImpl implements IUserService {
    private IUserDao userDao = new UserDaoImpl();


    @Override
    public boolean login(String username,String password)  {
        return  userDao.login(username, password);
    }

    @Override
    public List<ShopUser> queryAllUsers() {
        return userDao.queryAllUsers();
    }

    @Override
    public boolean delUser(int userId) {
        return userDao.delUser(userId);
    }

    @Override
    public ShopUser queryUserInfo(int userId) {
          return userDao.queryUserInfo(userId);
    }

    @Override
    public boolean updateUser(ShopUser user) {
        return userDao.updateUser(user);
    }


}
