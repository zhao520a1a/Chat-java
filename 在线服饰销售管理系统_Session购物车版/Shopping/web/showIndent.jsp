<%@ page import="com.xin.bean.Indent" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 小鑫哦
  Date: 2016/11/14 0014
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 查看订单 </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/cart.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="row hidden-print">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <ol class="breadcrumb">
            <li><a href="index.jsp ">首页</a></li>
            <li class="active">查看订单</li>
        </ol>
    </div>
</div>


<div class="cart-wrap">
    <form id="cartForm" method="post" action="">
        <table id="cartTable" class="cart table table-condensed">
            <thead>
            <tr>
                <th class="t-goods text-center"><label>订单号</label></th>
                <th class="text-center">提交时间</th>
                <th class="t-selling-price text-center"><label>总金额</label></th>
                <th class="t-qty text-center"><label>付款</label></th>
                <th class="t-subtotal text-center"><label>发货</label></th>
                <th class="t-subtotal text-center"><label>详情</label></th>
            </tr>
            </thead>

            <%--<tbody> --%>
            <tbody>
            <jsp:useBean id="shop" scope="page" class="com.xin.service.ShopServiceImpl"></jsp:useBean>


            <%
                List<Indent> indents = shop.queryIndentInfo((Integer) session.getAttribute("userid"));
                if (indents.size() > 0) {
                    for (int i = 0; i < indents.size(); i++) {
                        Indent indent = indents.get(i);
            %>
            <tr>
                <td class=" text-center" style="padding-top: 1.1rem;">
                    <label>
                        <%=indent.getIndentNo()%>
                    </label>
                </td>
                <td class=" text-center" style="padding-top: 1.1rem;">
                    <label>
                        <%=indent.getSumbitTime()%>
                    </label>
                </td>
                <td class=" text-center" style="padding-top: 1.1rem;">
                    <label>
                        <%=indent.getTotalPrice()%>
                    </label>
                </td>

                <td class=" text-center" style="padding-top: 1.1rem;">
                    <label>
                        未付
                    </label>
                </td>
                <td class=" text-center" style="padding-top: 1.1rem;">
                    <label>
                        未发货
                    </label>
                </td>
                <td class="action text-center" style="padding-top: 1.1rem;">
                    <a href="showIndentInfo.jsp?queryIndent=<%=indent.getIndentId()%> " class="btn btn-xs btn-warning">
                        查看</a>
                </td>
            </tr>
            </tbody>
            <%--</tbody>--%>
            <% }
            } else {
                out.print("订单列表为空！！！");
            }
            %>
        </table>
    </form>
</div>
</body>
</html>
