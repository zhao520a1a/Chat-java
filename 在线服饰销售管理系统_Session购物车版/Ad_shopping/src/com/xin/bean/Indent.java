package com.xin.bean;

import java.sql.Timestamp;

/**
 * Created by golden on 2016/11/4 0004.
 * 订单和用户关联的信息表
 */
public class Indent {

    private int indentId;
    private String indentNo;
    private ShopUser user;
    private float totalPrice;
    private String content;
    private Timestamp sumbitTime;  //提及时间
    private  String isPayoff;
    private  String isSales;

    public Indent() {
    }

    public Indent(int indentId, String indentNo, float totalPrice, Timestamp sumbitTime) {
        this.indentId = indentId;
        this.indentNo = indentNo;
        this.totalPrice = totalPrice;
        this.sumbitTime = sumbitTime;
    }

    public Indent(int indentId, String indentNo, Float totalPrice, String content, String isPayoff, String isSales, Timestamp submitTime, ShopUser shopUser) {
        this.indentId = indentId;
        this.indentNo = indentNo;
        this.totalPrice = totalPrice;
        this.content  = content;
        this.isPayoff = isPayoff;
        this.isSales = isSales;
        this.sumbitTime = submitTime;
        this.user = shopUser;

    }

    public int getIndentId() {
        return indentId;
    }

    public void setIndentId(int indentId) {
        this.indentId = indentId;
    }


    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getSumbitTime() {
        return sumbitTime;
    }

    public void setSumbitTime(Timestamp sumbitTime) {
        this.sumbitTime = sumbitTime;
    }

    public String getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(String indentNo) {
        this.indentNo = indentNo;
    }

    public ShopUser getUser() {
        return user;
    }

    public void setUser(ShopUser user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsPayoff() {
        return isPayoff;
    }

    public void setIsPayoff(String isPayoff) {
        this.isPayoff = isPayoff;
    }

    public String getIsSales() {
        return isSales;
    }

    public void setIsSales(String isSales) {
        this.isSales = isSales;
    }
}
