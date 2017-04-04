package com.xin.bean;

import java.sql.Timestamp;

/**
 * Created by golden on 2016/11/4 0004.
 */
public class ShopUser {
    private int userid;
    private String username;
    private String password;
    private String repassword;
    private String realname;
    private String sex;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private  Timestamp regTime;

    public ShopUser(String username, String password,  String realname, String sex, String userEmail, String userPhone, String userAddress) {
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.sex = sex;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

    public ShopUser(int userId, String userName, String password, String realName, String sex, String email, String phone, String address, Timestamp regTime) {
        this.userid = userId;
        this.username = userName;
        this.password = password;
        this.realname = realName;
        this.sex = sex;
        this.userEmail = email;
        this.userPhone = phone;
        this.userAddress = address;
        this.regTime = regTime;
    }

    public ShopUser() {

    }

    public ShopUser(int userId, String userName, String password, String realName, String sex, String userEmail, String userPhone, String userAddress) {
        this.userid = userId;
        this.username = userName;
        this.password = password;
        this.realname = realName;
        this.sex = sex;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

    public ShopUser(int userId, String username) {
        this.userid = userId;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }
}
