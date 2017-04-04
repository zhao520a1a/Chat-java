package com.xin.bean;


/**
 * Created by golden on 2016/11/4 0004.
 */
public class   GoodsClass {
    private int goodsClassId;
    private String  classname;



    public GoodsClass(int goodsClassId, String className ) {
        this.goodsClassId = goodsClassId;
        this.classname = className;
    }

    public GoodsClass(int goodsClassId) {
        this.goodsClassId = goodsClassId;
    }


    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getGoodsClassId() {
        return goodsClassId;
    }

    public void setGoodsClassId(int goodsClassId) {
        this.goodsClassId = goodsClassId;
    }
}