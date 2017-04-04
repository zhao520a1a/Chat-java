package com.xin.bean;

import java.sql.Timestamp;

/**
 * Created by golden on 2016/11/4 0004.
 * 订单和用户关联的信息表
 */
public class Indent {

    private int indentId;
    private String indentNo;
    private int userId;
    private float totalPrice;
    private Timestamp sumbitTime;  //提及时间

    public Indent() {
    }

    public Indent(int indentId, String indentNo, float totalPrice, Timestamp sumbitTime) {
        this.indentId = indentId;
        this.indentNo = indentNo;
        this.totalPrice = totalPrice;
        this.sumbitTime = sumbitTime;
    }

    public int getIndentId() {
        return indentId;
    }

    public void setIndentId(int indentId) {
        this.indentId = indentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
