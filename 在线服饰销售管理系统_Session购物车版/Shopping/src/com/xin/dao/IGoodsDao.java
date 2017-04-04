package com.xin.dao;

import com.xin.bean.Goods;
import com.xin.bean.IndentList;

import java.util.List;

/**
 * Created by golden on 2016/11/6 0006.
 */
public interface IGoodsDao {
    List<Goods> queryGoodsByClass(int goodsClassId);
    Goods queryGoodsInfo(int goodId);
    int getLeaveAmount(int goodId);
    boolean updateLeaveAmount(List<IndentList> nowbuyList);
}
