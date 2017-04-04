<%@ page import="com.xin.bean.Goods" %>
<%@ page import="com.xin.bean.GoodsClass" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xin.bean.ShopUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>


    <%--返回上一级--%>
    <script type="text/javascript">
        function returnBefore() {
            window.history.back();
        }
    </script>
</head>
<body>
<form method="post" action="" id="listform">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 订单详情 </strong>
            <a href="#" onclick="returnBefore()"   style="float:right;">返回上一级</a>
        </div>

        <table class="table table-hover text-center">
            <tr>
                <th width="30%">商品名称</th>
                <th width="20%"> 单价</th>
                <th  width="20%" >数量</th>
                <th  width="20%">小计</th>
            </tr>

            <%
                List<Goods> goodsList = (List<Goods>) session.getAttribute("goodsList");
                if (goodsList.size() > 0) {
                    for (Goods goods : goodsList) {
            %>
            <tr>
                </td>
                <td><%=goods.getGoodsName()%>
                </td>
                <td><%=goods.getPrice()%>
                </td>
                <td><%=goods.getSaleNum()%>
                </td>
                <td><%= goods.getPrice() * goods.getSaleNum()%>
                </td>
            </tr>
            <%
                    }
                }
            %>
            <%--<tr>--%>
            <%--<td colspan="8"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>--%>
            <%--</tr>--%>
        </table>
    </div>
</form>
</body>
</html>