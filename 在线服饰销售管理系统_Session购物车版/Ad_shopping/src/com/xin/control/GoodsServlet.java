package com.xin.control;

import com.xin.bean.Goods;
import com.xin.bean.GoodsClass;
import com.xin.service.IShopService;
import com.xin.service.ShopServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
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
            case "showAllGoods": {
                showAllGoods(req, resp);
            }
            break;
            case "addGoods": {
                addGoods(req, resp);
            }
            break;
            case "delGoods": {
                delGoods(req, resp);
            }
            break;
            case "updateGoods": {
                updateGoods(req, resp);
            }
            case "showOneGoods": {
                showOneGoods(req, resp);
            }
            break;
        }
    }

    private void showOneGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        int goodsId = Integer.parseInt(filter(req.getParameter("goodsId")));
        Goods goods = goodsService.queryGoodsInfo(goodsId);
        if( goods !=null) {
            req.getSession().setAttribute("goods",goods);
            printWriter.print("获取单个商品信息成功");

        } else {
            printWriter.print("获取单个商品信息失败");
        }
        printWriter.close();
    }

    private void updateGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        int goodsId = Integer.parseInt(filter(req.getParameter("goodsId")));
        String goodsName = filter(req.getParameter("goodsName"));
        float goodsPrice = Float.parseFloat(filter(req.getParameter("goodsPrice")));
        int goodsClassId = Integer.parseInt(filter(req.getParameter("goodsClassId")));
        String provider = filter(req.getParameter("provider"));
        String goodsNo = filter(req.getParameter("goodsNo"));
        String content = filter(req.getParameter("content"));
        int amount = Integer.parseInt(filter(req.getParameter("amount")));
        int leave_amount = Integer.parseInt(filter(req.getParameter("leave_amount")));
        Goods oldGoods = new Goods(goodsId,goodsName, goodsPrice, new GoodsClass(goodsClassId), provider, goodsNo, content, amount, leave_amount);

        if(goodsService.updateGoods(oldGoods)) {
            printWriter.print("修改商品成功");
        } else {
            printWriter.print("修改商品失败");
        }
        printWriter.close();
    }

    private void delGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        int goodsId = Integer.parseInt(filter(req.getParameter("goodsId")));
        if(goodsService.delGoods(goodsId)) {
            printWriter.print("删除商品成功");
        } else {
            printWriter.print("删除商品失败");
        }

    }

    private void addGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        String goodsName = filter(req.getParameter("goodsName"));
        float goodsPrice = Float.parseFloat(filter(req.getParameter("goodsPrice")));
        String goodsImg = filter(req.getParameter("goodsImg"));
        int goodsClassId = Integer.parseInt(filter(req.getParameter("goodsClassId")));
        String provider = filter(req.getParameter("provider"));
        String goodsNo = filter(req.getParameter("goodsNo"));
        String content = filter(req.getParameter("content"));
        int amount = Integer.parseInt(filter(req.getParameter("amount")));
        int leave_amount = Integer.parseInt(filter(req.getParameter("leave_amount")));
        Timestamp regtime = Timestamp.valueOf(filter(req.getParameter("regtime")));
        Goods newGoods = new Goods(goodsName, goodsPrice, goodsImg, new GoodsClass(goodsClassId), provider, goodsNo, content, amount, leave_amount, regtime);
        if(goodsService.addGoods(newGoods)) {
            printWriter.print("增加商品成功");
        } else {
            printWriter.print("增加商品失败");
        }
        printWriter.close();
    }


    public void showAllGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        List<GoodsClass> goodsClassList = goodsService.queryAllGoodsClass();
        if (goodsClassList != null) {
            req.getSession().setAttribute("goodsClassList", goodsClassList);
            List<Goods> goodsList = goodsService.queryAllGoods();
            if (goodsList != null && goodsList.size() != 0) {
                req.getSession().setAttribute("goodsList", goodsList);
                printWriter.print("获取商品信息成功");
            }
        } else {
            printWriter.print("没有商品信息可供查询！");
        }
        printWriter.close();

    }


    public String filter(String date) throws UnsupportedEncodingException {
        return URLDecoder.decode(date, "UTF-8");
    }
}

