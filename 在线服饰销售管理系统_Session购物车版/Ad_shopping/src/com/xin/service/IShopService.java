package com.xin.service;

import com.xin.bean.Goods;
import com.xin.bean.GoodsClass;
import com.xin.bean.Indent;

import java.util.List;

/**
 * Created by golden on 2016/11/6 0006.
 */
public interface IShopService {
    List<Goods> queryGoodsByClass(int goodsClassId);
    
    List<Indent> queryIndentInfo(int userId);

    List<Goods> queryIndentListInfo(int indentId);

    List<Goods> queryAllGoods();

    boolean addGoods(Goods newGoods);

    List<GoodsClass> queryAllGoodsClass();

    boolean delGoods(int goodsId);

    boolean updateGoods(Goods goods);

    Goods queryGoodsInfo(int goodsId);

    List<Indent> showAllIndents();

    boolean delIndent(int indentId);
}
