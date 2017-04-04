package com.xin.control;

import com.xin.bean.Goods;
import com.xin.bean.IndentList;
import com.xin.service.IShopService;
import com.xin.service.ShopServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by golden on 2016/12/7 0007.
 */
@WebServlet(
        name = "GoodsServlet", urlPatterns = {"/GoodsServlet"}
)
public class GoodsServlet extends HttpServlet {

    private IShopService goodsService = new ShopServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "showGoods": {
                showGoods(req, resp);
            }
            break;
            case "addToCart": {
                addToCart(req, resp);
            }
            break;
            case "toNowBuy": {
                toNowBuy(req, resp);
            }
            break;
        }
    }


    public void showGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] goodsClassIds = req.getParameterValues("goodsClassId");
        List<Goods> goodsList = new ArrayList<Goods>();
        IShopService shopService = new ShopServiceImpl();
        String goodShowUrl = "goodsList.jsp";

        if (goodsList != null) {
            for (String goodsClassId : goodsClassIds) {
                if (goodsClassId.equals("-1")) {
                    goodShowUrl = "allGoods.jsp";
                }
                List<Goods> temp = shopService.queryGoodsByClass(Integer.parseInt(goodsClassId));
                goodsList.addAll(temp);
            }
            req.setAttribute("goodsList", goodsList);
            req.getRequestDispatcher(goodShowUrl).forward(req, resp);
        }
    }

    public void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        if (!goodsService.getIsLogin(req)) {
            printWriter.print("你还没有登录，请先登录后再提交！");
        } else {
            printWriter.print(goodsService.addToCart(req));
        }
        printWriter.close();
    }

    /*将购物车中的某些商品，去立即结算*/
    public void toNowBuy(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        if (!goodsService.getIsLogin(req)) {
            printWriter.print("你还没有登录，请先登录后再提交！");
        } else {
            String goods_Id = req.getParameter("goodsId");  /*由商品页面直接立即购买*/

            String goodsIdList = req.getParameter("goodsIds");

              /*将购物车中的某些商品，去立即结算*/
            HttpSession session = req.getSession(false);
            List<IndentList> nowbuyList = new LinkedList<IndentList>();

            if (goods_Id != null) {
                String goodsId = URLDecoder.decode(goods_Id, "UTF-8");

                int saleAmount = Integer.parseInt(URLDecoder.decode(req.getParameter("saleAmount"), "UTF-8"));

                IndentList indentList = new IndentList();
                indentList.setGoodsId(Integer.parseInt(goodsId));
                indentList.setAmount(saleAmount);
                nowbuyList.add(indentList);

                session.setAttribute("nowbuyList", nowbuyList);
                printWriter.print("结算成功");
            } else if (goodsIdList != null) {
                String[] goodsIds = URLDecoder.decode(goodsIdList, "UTF-8").split(","); /*获取被勾选复选框的值,注：之前已将其值赋为指定的goodsId*/
                List<IndentList> shopList = (List<IndentList>) session.getAttribute("shopcart");
                for (String id : goodsIds) {
                    for (IndentList iList : shopList) {
                        if (Integer.parseInt(id) == iList.getGoodsId()) {
                            nowbuyList.add(iList);
                            break;
                        }
                    }
                }

                session.setAttribute("nowbuyList", nowbuyList);
                printWriter.print("结算成功");
            } else {
                printWriter.print("系统异常，请稍候再试！");
            }
            printWriter.close();
        }
    }
}

