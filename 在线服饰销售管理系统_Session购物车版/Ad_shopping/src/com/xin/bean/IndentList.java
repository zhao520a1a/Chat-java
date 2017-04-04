package com.xin.bean;

/**
 * Created by golden on 2016/11/4 0004.
 * 订单和商品的关联的信息表
 */
public class IndentList {
    private int indentId;
    private int goodsId;
    private int amount;
    private int subtotal; //小计

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }




    public int getIndentId() {
        return indentId;
    }

    public void setIndentId(int indentId) {
        this.indentId = indentId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
