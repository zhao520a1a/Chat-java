package com.xin.service;

import com.xin.bean.Goods;
import com.xin.bean.GoodsClass;
import com.xin.bean.Indent;
import com.xin.dao.*;

import java.util.List;

/**
 * Created by golden on 2016/11/6 0006.
 * 主要要来处理用户购物时的业务逻辑
 */
public class ShopServiceImpl implements IShopService {
    private IGoodsDao goodsDao = new GoodsDaoImpl();
    private IIndentDao indentDao = new IndentDaoImpl();
    private IIndentListDao indentListDao = new IndentListDaoImpl();

    @Override
    /*查询一类商品*/
    public List<Goods> queryGoodsByClass(int goodsClassId) {
        return goodsDao.queryGoodsByClass(goodsClassId);
    }


    @Override
    public List<Goods> queryAllGoods() {
        return goodsDao.queryAllGoods();
    }

    @Override
    public boolean addGoods(Goods newGoods) {
        return goodsDao.addGoods(newGoods);
    }

    @Override
    public List<GoodsClass> queryAllGoodsClass() {
        return goodsDao.queryAllGoodsClass();
    }

    @Override
    public boolean delGoods(int goodsId) {
        return goodsDao.delGoods(goodsId);
    }

    @Override
    public boolean updateGoods(Goods goods) {
        return goodsDao.updateGoods(goods);
    }

    @Override
    public Goods queryGoodsInfo(int goodsId) {
        return goodsDao.queryGoodsInfo(goodsId);
    }

    @Override
    public List<Indent> showAllIndents() {
        return indentDao.getAllIndents();
    }

    @Override
    public boolean delIndent(int indentId) {
        if(indentListDao.delIndentList(indentId)){
            return  indentDao.delIndent(indentId);
        }
        return false;
    }


    @Override
    /*获得订单的详细信息*/
    public List<Indent> queryIndentInfo(int userId) {
        return indentDao.getIndentInfo(userId);
    }

    @Override
    /*查询订单列表*/
    public List<Goods> queryIndentListInfo(int indentId) {
       return indentListDao.getIndentListInfo(indentId);
    }



}
