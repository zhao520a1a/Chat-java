package com.xin.service;

import com.xin.bean.Goods;
import com.xin.bean.Indent;
import com.xin.bean.IndentList;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by golden on 2016/11/6 0006.
 */
public interface IShopService {
    List<Goods> queryGoodsByClass(int goodsClassId);
    
    Goods queryGoodsInfo(int goodId);

    String addToCart(HttpServletRequest request);

    boolean deleteGoodsFromCart(HttpServletRequest request);

    boolean updateToCart(HttpServletRequest request);

    List<Indent> queryIndentInfo(int userId);

    List<Goods> queryIndentListInfo(int indentId);

    boolean getIsLogin(HttpServletRequest request);

    String getIndentNo(int userid);

    boolean addIndent(String indentNo, int userid, float priceTotal, String content);

    boolean addIndentList(int userid, List<IndentList> nowbuyList);

    boolean updateLeaveAmount(List<IndentList> nowbuyList);
}
