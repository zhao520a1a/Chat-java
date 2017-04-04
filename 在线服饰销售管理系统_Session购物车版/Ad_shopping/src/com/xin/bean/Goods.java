package com.xin.bean;

import java.sql.Timestamp;

/**
 * Created by golden on 2016/11/4 0004.
 */
public class Goods {
    private int goodsId;
    private String goodsName;
    private String provider;
    private String goodsNo;
    private String content;
    private float price;
    private int amount;
    private int leave_amount;
    private int saleNum;
    private String img;
    private GoodsClass goodsClass;
    private  Timestamp regtime;


    public Goods(int goodsId, String goodsName, float price, String img, GoodsClass goodsClass, String provider, String goodsNo, String content, int amount, int leave_amount, Timestamp regtime) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsNo = goodsNo;
        this.content = content;
        this.price = price;
        this.amount = amount;
        this.leave_amount = leave_amount;
        this.img = img;
        this.goodsClass = goodsClass;
        this.goodsNo = goodsNo;
        this.provider = provider;
        this.regtime =  regtime;
    }

    public Goods(int goodsId, String goodsName, float price, String img, GoodsClass goodsClass) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.price =price;
        this.img = img;
        this.goodsClass = goodsClass;
    }

    public Goods(int goodsId, String goodsName, String provider, String goodsNo, String content, float price, int amount, int leave_amount, String img, GoodsClass goodsClass) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsNo = goodsNo;
        this.content = content;
        this.price = price;
        this.amount = amount;
        this.leave_amount = leave_amount;
        this.img = img;
        this.goodsClass = goodsClass;
        this.provider = provider;

    }

    public Goods(String goodsName, float price, String img, GoodsClass goodsClass, String provider, String goodsNo, String content, int amount, int leave_amount, Timestamp regtime) {
        this.goodsName = goodsName;
        this.goodsNo = goodsNo;
        this.content = content;
        this.price = price;
        this.amount = amount;
        this.leave_amount = leave_amount;
        this.img = img;
        this.goodsClass = goodsClass;
        this.goodsNo = goodsNo;
        this.provider = provider;
        this.regtime =  regtime;
    }

    public Goods(String goodsName, float goodsPrice, GoodsClass goodsClass, String provider, String goodsNo, String content, int amount, int leave_amount) {
        this.goodsName = goodsName;
        this.goodsNo = goodsNo;
        this.content = content;
        this.amount = amount;
        this.leave_amount = leave_amount;
        this.goodsClass = goodsClass;
        this.goodsNo = goodsNo;
        this.provider = provider;
        this.price = goodsPrice;
    }

    public Goods(int goodsId, String goodsName, float goodsPrice, GoodsClass goodsClass, String provider, String goodsNo, String content, int amount, int leave_amount) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsNo = goodsNo;
        this.content = content;
        this.amount = amount;
        this.leave_amount = leave_amount;
        this.goodsClass = goodsClass;
        this.goodsNo = goodsNo;
        this.provider = provider;
        this.price = goodsPrice;
    }


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String privoder) {
        this.provider = privoder;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLeave_amount() {
        return leave_amount;
    }

    public void setLeave_amount(int leave_amount) {
        this.leave_amount = leave_amount;
    }

    public GoodsClass getGoodsClass() {
        return goodsClass;
    }

    public void setGoodsClass(GoodsClass goodsClass) {
        this.goodsClass = goodsClass;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public Timestamp getRegtime() {
        return regtime;
    }

    public void setRegtime(Timestamp regtime) {
        this.regtime = regtime;
    }
}
