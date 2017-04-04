package com.xin.control;

import com.xin.bean.Goods;
import com.xin.bean.Indent;
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
            case "showAllIndents": {
                showAllIndents(req, resp);
            }
            break;
            case "delIndent": {
                delIndent(req, resp);
            }
            break;
            case "showOneIndent": {
                showOneIndent(req, resp);
            }
            break;
        }
    }


    private void showOneIndent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        int indentId = Integer.parseInt(req.getParameter("indentId"));
        List<Goods> goodsList = indentService.queryIndentListInfo(indentId);
        if (goodsList != null) {
            req.getSession().setAttribute("goodsList", goodsList);
            printWriter.print("获取单个订单信息成功");
        } else {
            printWriter.print("获取单个订单信息失败");
        }
        printWriter.close();
    }


    private void delIndent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        int indentId = Integer.parseInt(filter(req.getParameter("indentId")));
        if (indentService.delIndent(indentId)) {
            printWriter.print("删除订单成功");
        } else {
            printWriter.print("删除订单成功");
        }
    }

    private void showAllIndents(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        List<Indent> indents = indentService.showAllIndents();
        if (indents != null && indents.size() != 0) {
            req.getSession().setAttribute("indents", indents);
            printWriter.print("获取所有订单信息成功");
        } else {
            printWriter.print("没有订单信息可供查询！");
        }
        printWriter.close();

    }

    public String filter(String date) throws UnsupportedEncodingException {
        return URLDecoder.decode(date, "UTF-8");
    }


}