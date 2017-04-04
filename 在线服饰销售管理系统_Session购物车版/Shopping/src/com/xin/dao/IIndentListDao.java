package com.xin.dao;

import com.xin.bean.Goods;
import com.xin.bean.IndentList;

import java.util.List;

/**
 * Created by golden on 2016/11/13 0013.
 *  订单和商品的关联的操作类
 */
public interface IIndentListDao {
    boolean addIndentList(int userid, List<IndentList> nowbuyList);

    Integer getIndentId(int userid);

    List<Goods> getIndentListInfo(int indentId);
}
