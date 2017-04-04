package com.xin.dao;

import com.xin.bean.Goods;
import com.xin.bean.GoodsClass;

import java.util.List;

/**
 * Created by golden on 2016/11/6 0006.
 */
public interface IGoodsDao {
    List<Goods> queryGoodsByClass(int goodsClassId);

    Goods queryGoodsInfo(int goodId);

    List<Goods> queryAllGoods();

    boolean addGoods(Goods newGoods);

    List<GoodsClass> queryAllGoodsClass();

    int getGoodsClassId(String classname);

    boolean delGoods(int goodsId);

    boolean updateGoods(Goods goods);
}
