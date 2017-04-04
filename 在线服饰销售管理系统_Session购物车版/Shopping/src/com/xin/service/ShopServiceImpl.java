package com.xin.service;

import com.xin.bean.Goods;
import com.xin.bean.Indent;
import com.xin.bean.IndentList;
import com.xin.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by golden on 2016/11/6 0006.
 * 主要要来处理用户购物时的业务逻辑
 */
public class ShopServiceImpl implements IShopService {
    private IGoodsDao goodDao = new GoodsDaoImpl();
    private IIndentDao indentDao = new IndentDaoImpl();
    private IIndentListDao indentListDao = new IndentListDaoImpl();

    @Override
    /*查询一类商品*/
    public List<Goods> queryGoodsByClass(int goodsClassId) {
        return goodDao.queryGoodsByClass(goodsClassId);
    }

    @Override
    /*查询某一商品的详细信息*/
    public Goods queryGoodsInfo(int goodsId) {
        return goodDao.queryGoodsInfo(goodsId);
    }

    @Override
    /*将商品加入购物车（设置session中一属性，作为记录购物车信息的载体）*/
    public String addToCart(HttpServletRequest request) {

        int goodsId = Integer.parseInt(request.getParameter("goodsId"));
        int saleAmount = Integer.parseInt(request.getParameter("saleAmount"));

        if (saleAmount < 1) {  //购买数量非法
            return "购买数量非法！";
        }
        /*剩余的商品数量是否能满足用户的需求*/
        int leaveAmount = goodDao.getLeaveAmount(goodsId);
        if (saleAmount > leaveAmount) {
            return "库存不足！";
        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "系统异常！";
        }
        List<IndentList> purchaseList = (List<IndentList>) session.getAttribute("shopcart");  /*保存购物车中信息*/
        IndentList indentList = new IndentList();
        indentList.setGoodsId(goodsId);
        indentList.setAmount(saleAmount);
        /*判断是否是已经将该商品加入过购物车，是则添加，否则只是数量增加*/
        boolean flag = false; /*是否购买过该商品*/
        if (purchaseList == null) {    // 购物车为空
            purchaseList = new LinkedList<IndentList>();
            purchaseList.add(indentList);
        } else {
            for (int i = 0; i < purchaseList.size(); i++) {
                IndentList iList = purchaseList.get(i);
                if (iList.getGoodsId() == indentList.getGoodsId()) {  //加入过，购物车中已有该商品，则找到并增加其数量
                    iList.setAmount(iList.getAmount() + indentList.getAmount());
                    purchaseList.set(i, iList);
                    flag = true;
                    break;
                }
            }
            if (!flag) {   //购物车中还没有该商品
                purchaseList.add(indentList);
            }
        }
        /*将结果更新到session中*/
        session.setAttribute("shopcart", purchaseList);
        return "成功加入购物车！";
    }

    @Override
    /*修改购物车操作*/
    public boolean updateToCart(HttpServletRequest request) {
        String[] goodsIds = request.getParameterValues("goodsId");
        String[] saleAmountList = request.getParameterValues("saleAmount");
        HttpSession session = request.getSession(false);
        if (saleAmountList == null && goodsIds == null) {  /*说明用户将购物车中商品全部删除了,则将购物车置空，返回*/
            session.setAttribute("shopcart", null);
            return true;
        }
        //判断购买数量是否非法
        for (String num : saleAmountList) {
            int saleNum = Integer.parseInt(num);
            if (saleNum < 1) {
                return false;
            }
        }
        /*将goodsId和saleAmount关联绑定起来*/
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i < goodsIds.length; i++) {
            Integer goodsId = Integer.parseInt(goodsIds[i]);
            Integer saleAmount = Integer.parseInt(saleAmountList[i]);
            mapping.put(goodsId, saleAmount);
        }
        /*剩余的商品数量是否能满足用户的需求*/
        for (String gId : goodsIds) {
            int goodsId = Integer.parseInt(gId);
            int leaveAmount = goodDao.getLeaveAmount(goodsId);
            if (mapping.get(goodsId) > leaveAmount) {
                return false;
            }
        }

        List<IndentList> purchaseList = (List<IndentList>) session.getAttribute("shopcart");
        for (String gId : goodsIds) {
            int goodsId = Integer.parseInt(gId);
            for (int i = 0; i < purchaseList.size(); i++) {
                IndentList iList = purchaseList.get(i);
                if (iList.getGoodsId() == goodsId) {
                    iList.setAmount(mapping.get(goodsId));
                    purchaseList.set(i, iList);
                    break;
                }
            }
        }
        /*将结果更新到session中*/
        session.setAttribute("shopcart", purchaseList);
        return true;

    }

    /*判断是用户还是游客*/
    public boolean getIsLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("userid") != null) {
            return true;
        }
        return false;
    }

    @Override
    public String getIndentNo(int userId) {
        return indentDao.getIndentNo(userId);
    }

    @Override
    public boolean addIndent(String indentNo, int userid, float priceTotal, String content) {
        return indentDao.addIndent(indentNo,userid,priceTotal,content);
    }

    @Override
    public boolean addIndentList(int userid, List<IndentList> nowbuyList) {
        return indentListDao.addIndentList(userid, nowbuyList);
    }

    @Override
    public boolean updateLeaveAmount(List<IndentList> nowbuyList) {
        return goodDao.updateLeaveAmount(nowbuyList);
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

    @Override
    /*将某一商品从购物车中删除*/
    public boolean deleteGoodsFromCart(HttpServletRequest request) {
        int goodsId = Integer.parseInt(request.getParameter("del"));
        HttpSession session = request.getSession(false);
        List<IndentList> purchaseList = (List<IndentList>) session.getAttribute("shopcart");
        if (purchaseList == null) {
            return false;
        } else {
            for (int i = 0; i < purchaseList.size(); i++) {
                if (purchaseList.get(i).getGoodsId() == goodsId) {
                    purchaseList.remove(i);
                    session.setAttribute("shopcart", purchaseList);
                    break;
                }
            }
            return true;
        }
    }

}
