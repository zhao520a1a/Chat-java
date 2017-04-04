<%@ page import="com.xin.bean.Goods" %>
<%@ page import="com.xin.bean.IndentList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: 小鑫哦
  Date: 2016/11/12 0012
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑购物车</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/etao.js"></script>
    <script type="text/javascript" src="js/updateCart.js"></script>
    <link href="css/cart.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css"/>
</head>
<body class="container">
<jsp:useBean id="shop" scope="page" class="com.xin.service.ShopServiceImpl"></jsp:useBean>
<%
    List<IndentList> shopList = (List<IndentList>) session.getAttribute("shopcart");
    String msg = null;

    String del = request.getParameter("del");
    if (del != null && !del.equals("")) {
        if (shop.deleteGoodsFromCart(request)) {
            msg = "删除成功";
            response.sendRedirect("updateCart.jsp");
        } else {
            msg = "删除失败";
            out.print(msg);
        }
    }

    String nowbuy = request.getParameter("exitOk");
    if (nowbuy != null && !nowbuy.equals("")) {
        if (shop.updateToCart(request)) {
            msg = "更新购物车信息成功";
            response.sendRedirect("showCart.jsp");
        } else {
            msg = "更新购物车信息失败";
            out.print(msg);
        }
    }
%>

<div class="row hidden-print">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <ol class="breadcrumb">
            <li class="active">编辑购物车</li>
        </ol>
    </div>
</div>
<div class="cart-wrap">
    <form id="cartForm" method="post" action="">
        <table id="cartTable" class="cart table table-condensed">
            <thead>
            <tr>
                <th class="t-goods text-center"><label>产品型号</label></th>
                <th class="text-center">产品名称</th>
                <th class="t-selling-price text-center"><label>销售单价</label></th>
                <th class="t-qty text-center"><label>采购数量</label></th>
                <th class="t-subtotal text-center"><label>金额小计</label></th>
                <th class="t-action"><label>操作</label></th>
            </tr>
            </thead>

            <%--<tbody> --%>
            <tbody>
            <%
                if (shopList != null) {
                    for (int i = 0; i < shopList.size(); i++) {
                        IndentList iList = shopList.get(i);
                        Goods goods = shop.queryGoodsInfo(iList.getGoodsId());
            %>
            <tr>
                    <%--隐藏传递商品Id信息--%>
                    <input type="hidden" class="goodId" name="goodsId" value="<%=iList.getGoodsId()%>"/>
                <td class=" text-center" style="padding-top: 1.1rem;">
                    <label>
                        <%=goods.getGoodsNo()%>
                    </label>
                </td>
                <td class=" text-center" style="padding-top: 1.1rem;">
                    <label>
                        <%=goods.getGoodsName()%>
                    </label>
                </td>
                <td class="selling-price number small-bold-red  text-center  "
                    style="padding-top: 1.1rem;" data-bind="<%=goods.getPrice()%>"><%=goods.getPrice()%>
                </td>
                <td style="width: 40px" class="text-center" style="padding-top: 1.1rem;">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon minus">-</span>
                        <input type="text" class="number form-control input-sm" name="saleAmount" value="<%=iList.getAmount()%>"/>
                        <span class="input-group-addon plus">+</span>
                    </div>
                </td>
                <td class="subtotal number small-bold-red text-center"
                    style="padding-top: 1.1rem;  ">
                </td>
                <td class="action" style="padding-top: 1.1rem;">
                    <a href="updateCart.jsp?del=<%=goods.getGoodsId()%> " class="btn btn-xs btn-warning"> 删除</a>
                </td>
            </tr>
            </tbody>
            <%--</tbody>--%>
            <%
                    }
                }
            %>
        </table>
        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12">
                <div class="cart-summary">
                    <div style="margin-left: 2rem;" class="pull-right">
                        <input id="btn_settlement"  type="submit" class="btn btn-primary" name="exitOk" value="编辑完成">
                    </div>
                    <div style="margin-left: 1rem; margin-top: 0.4rem;" class="pull-right total">
                        <label>金额合计:<span id="priceTotal"
                                          class="price-total large-bold-red">0.00</span></label>
                    </div>
                    <div style="margin-top: 4px;" class="pull-right">
                        <label>购物车中有<span id="itemCount" class="large-bold-red"
                                         style="margin: 0 4px;"></span>种产品型号，共计<span
                                id="qtyCount" class="large-bold-red" style="margin: 0 4px;"></span>件
                        </label>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>

