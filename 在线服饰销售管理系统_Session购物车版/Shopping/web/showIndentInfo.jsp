<%@ page import="com.xin.bean.Goods" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: 小鑫哦
  Date: 2016/11/14 0014
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 查看订单详细信息 </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/cart.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css"/>
</head>
<body>

<%--返回上一级--%>
<script type="text/javascript" >
    function returnBefore() {
        window.history.back();
    }
</script>


<div class="row hidden-print">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <ol class="breadcrumb">
            <li><a href="index.jsp ">首页</a></li>
            <li><a href="#" onclick="returnBefore()">返回上一级</a></li>
            <li class="active">查看订单</li>
        </ol>
    </div>
</div>


<div class="cart-wrap">
    <form id="cartForm" method="post" action="">
        <table id="cartTable" class="cart table table-condensed">
            <thead>
            <tr>
                <th class="t-goods text-center"><label>商品名称</label></th>
                <th class="text-center">单价</th>
                <th class="t-selling-price text-center"><label>数量</label></th>
            </tr>
            </thead>

            <%--<tbody> --%>
            <tbody>
            <jsp:useBean id="shop" scope="page" class="com.xin.service.ShopServiceImpl"></jsp:useBean>
            <%
                int indentId = Integer.parseInt(request.getParameter("queryIndent"));
                List<Goods> goodsList = shop.queryIndentListInfo(indentId);
                if (goodsList.size() > 0) {
                    for (Goods goods : goodsList) {
            %>
            <tr>
                <td class="text-center" style="width:30%; padding-top: 1.1rem;">
                    <label>
                        <%=goods.getGoodsName()%>
                    </label>
                </td>
                <td class=" text-center" style="width:30%;padding-top: 1.1rem;">
                    <label>
                        <%=goods.getPrice()%>
                    </label>
                </td>
                <td class="text-center" style="width:30%;padding-top: 1.1rem;">
                    <label>
                        <%=goods.getSaleNum()%>
                    </label>
                </td>
            </tr>
            </tbody>
            <%--</tbody>--%>
            <%
                    }
                }
            %>
        </table>
    </form>
</div>
</body>
</html>
