package com.xin.control;

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
import java.util.List;

/**
 * Created by golden on 2016/12/10 0010.
 */

@WebServlet(
        name = "IndentServlet", urlPatterns = {"/IndentServlet"}
)
public class IndentServlet extends HttpServlet {

    private IShopService indentService = new ShopServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "payout": {
                payout(req, resp);
            }
            break;

        }
    }


    /*用户提交订单*/
    public void payout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        HttpSession session = req.getSession(false);
        List<IndentList> nowbuyList = (List<IndentList>) session.getAttribute("nowbuyList");
        if (nowbuyList == null || nowbuyList.size() <= 0) {
            printWriter.print("Sorry,提交出错，请稍后重试");
        }

        String content = URLDecoder.decode(req.getParameter("content"), "UTF-8");  //用户订单备注
        float priceTotal = Float.parseFloat(URLDecoder.decode(req.getParameter("priceTotal"), "UTF-8"));  //订单总价格
        int userid = (int) session.getAttribute("userid");   //用户id
        String indentNo = indentService.getIndentNo(userid);   //订单号

        /*将数据添加到Indent表中*/
        if (indentService.addIndent(indentNo, userid, priceTotal, content)) {
            /*将数据添加到IndentList表中*/
            if (indentService.addIndentList(userid, nowbuyList)) {
                /*注意别忘了了要修改good表中库存量的值*/
                if (indentService.updateLeaveAmount(nowbuyList)) {
                  /*还有更新购物车中数据信息*/
                    List<IndentList> purchaseList = (List<IndentList>) session.getAttribute("shopcart");
                    if(purchaseList != null) {
                        for (IndentList iList : nowbuyList) {
                            for (int i = 0; i < purchaseList.size(); i++) {
                                IndentList indentList = purchaseList.get(i);
                                if (iList.getGoodsId() == indentList.getGoodsId()) {
                                    purchaseList.remove(i);
                                    break;
                                }
                            }
                        }
                    }
                    /*清空立即购买商品的信息*/
                    session.setAttribute("nowbuyList", null);
                    printWriter.print("成功生成订单");
                }
            }
        } else {
            printWriter.print("Sorry,提交出错，请稍后重试");
        }
        printWriter.close();
    }
}