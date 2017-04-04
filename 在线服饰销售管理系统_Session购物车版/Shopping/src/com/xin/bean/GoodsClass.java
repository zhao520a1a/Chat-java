package com.xin.bean;


/**
 * Created by golden on 2016/11/4 0004.
 */
public class   GoodsClass {
    private int goodClassId;
    private String  classname;



    public GoodsClass(int goodClassId, String className ) {
        this.goodClassId = goodClassId;
        this.classname = className;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getGoodClassId() {
        return goodClassId;
    }

    public void setGoodClassId(int goodClassId) {
        this.goodClassId = goodClassId;
    }
}