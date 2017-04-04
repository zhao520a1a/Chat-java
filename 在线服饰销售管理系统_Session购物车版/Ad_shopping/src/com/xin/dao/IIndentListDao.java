package com.xin.dao;

import com.xin.bean.Goods;

import java.util.List;

/**
 * Created by golden on 2016/11/13 0013.
 *  订单和商品的关联的操作类
 */
public interface IIndentListDao {

    Integer getIndentId(int userid);

    List<Goods> getIndentListInfo(int indentId);

    boolean delIndentList(int indentId);
}
