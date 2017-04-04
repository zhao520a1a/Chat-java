<%@ page import="com.xin.bean.Goods" %>
<%@ page import="com.xin.bean.IndentList" %>
<%@ page import="java.util.List" %>
<%--
 Created by IntelliJ IDEA.
 User: 小鑫哦
 Date: 2016/11/8 0008
 Time: 17:04
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看购物车</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/etao.js"></script>
    <script type="text/javascript" src="js/showCart.js"></script>
    <link href="css/cart.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="js/nowbuy_ajax.js"></script>
</head>
<body class="container">
<jsp:useBean id="shop" scope="page" class="com.xin.service.ShopServiceImpl"></jsp:useBean>
<%
    List<IndentList> shopList = (List<IndentList>) session.getAttribute("shopcart");
%>

<div class="row hidden-print">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <ol class="breadcrumb">
            <li><a href="index.jsp ">首页</a></li>
            <li class="active">查看购物车</li>
        </ol>
    </div>
</div>

<div style="margin-left: 2rem;" class="pull-right">
    <%
        if (shopList != null && shopList.size() > 0) {
    %>
    <a href="updateCart.jsp" class="btn btn-primary"
       name="nowbuy"> 编辑购物车</a>
    <%
        }
    %>
</div>

<div class="cart-wrap">
    <form id="cartForm" method="post" action="">
        <table id="cartTable" class="cart table table-condensed">
            <thead>
            <tr>
                <th class="t-checkbox"><label><input class="check-all check" type="checkbox" name="checkall"/>全选</label>
                </th>
                <th class="t-goods text-center"><label>产品型号</label></th>
                <th class="text-center">产品名称</th>
                <th class="t-selling-price text-center"><label>销售单价</label></th>
                <th class="t-qty text-center"><label>采购数量</label></th>
                <th class="t-subtotal text-center"><label>金额小计</label></th>
            </tr>
            </thead>

            <%--<tbody> --%>
            <tbody>
            <%
                if (shopList != null && shopList.size() > 0) {
                    for (int i = 0; i < shopList.size(); i++) {
                        IndentList iList = shopList.get(i);
                        Goods goods = shop.queryGoodsInfo(iList.getGoodsId());
            %>
            <tr>
                <td style="padding-top: 1.1rem;">
                    <%--隐藏传递商品Id信息--%>
                    <input type="hidden" class="goodId" name="goodId" value="<%=iList.getGoodsId()%>"/>
                    <input type="checkbox" class="check-one check" name="checkone" value="0"/>
                </td>
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

                <td class="selling-price  number text-center " style="padding-top: 1.1rem;"
                    data-bind="<%=goods.getPrice()%>">
                    <label>
                        <%=goods.getPrice()%>
                    </label>
                </td>

                <td class="text-center" style="padding-top: 1.1rem;">
                    <label class="number">
                        <input type="hidden" class="saleAmount" name="saleAmount" value="<%=iList.getAmount()%>"/>
                        <%=iList.getAmount()%>
                    </label>
                </td>

                <td class="subtotal number small-bold-red text-center"
                    style="padding-top: 1.1rem;  ">
                </td>
            </tr>
            </tbody>
            <%--</tbody>--%>
            <%
                    }
                } else {
                        %>
                        <div style="font-size:20px;color: brown">  购物车为空！！  </div>
<%
                }
            %>
        </table>
        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12">
                <div class="cart-summary">
                    <div style="margin-left: 2rem;" class="pull-right">
                        <input id="btn_settlement" disabled="disabled" type="submit" class="btn btn-primary"
                               name="nowbuy" onclick="cartToNowBuy()" value="去结算">
                    </div>
                    <div style="margin-left: 1rem; margin-top: 0.4rem;" class="pull-right total">
                        <label>金额合计:
                            <span id="priceTotal" class="large-bold-red">  0.00   </span></label>
                        <input type="hidden" class="price-total" id="totalPrice" name="totalPrice"  value="">
                             <%--在cart.js中赋值value,用来给nowbuy.jsp页面传递合计的数值--%>
                    </div>
                    <div style="margin-top: 4px;" class="pull-right">
                        <label>您选择了
                            <span id="itemCount" class="large-bold-red" style="margin: 0 4px;"></span>种产品型号，共计<span
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
