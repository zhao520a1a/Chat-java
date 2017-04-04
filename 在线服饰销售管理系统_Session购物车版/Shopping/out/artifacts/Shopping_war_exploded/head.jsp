<%--
  Created by IntelliJ IDEA.
  User: 小鑫哦
  Date: 2016/11/3 0003
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 页面头部 </title>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <%--js弹出层动画特效：带遮罩效果--%>
    <script type="text/javascript" src="js/popup-layer.js"></script>
</head>

<body>
<jsp:useBean id="shop" scope="page" class="com.xin.service.ShopServiceImpl"></jsp:useBean>

<!-- start header -->
<div class="header_bg">
    <div class="wrap">
        <div class="header">
            <div class="logo">
                <a href="index.jsp"><img src="images/logo.png" alt=""/> </a>
            </div>
            <div class="h_icon">
                <ul class="icon1 sub-icon1">

                    <% if (!shop.getIsLogin(request)) { %>
                    <li><a class="active-icon c1" href="#"><i>Cart</i></a>
                        <ul class="sub-icon1 list">
                            <li><h3>客官，还没有登录，请先去登录！</h3></li>
                            <li><p> 如果还不是本店用户，请点击<a href="register.jsp">注册</a></p></li>
                        </ul>
                    </li>
                    <% } else { %>
                    <li><a class="active-icon c1" href="showCart.jsp"><i>Cart</i></a>
                        <ul class="sub-icon1 list">
                            <li><p>主人，快来看看，你到底收藏了多少宝贝吧！，</p></li>
                        </ul>
                    </li>
                    <%
                        }
                    %>

                </ul>
            </div>
            <div class="h_search">
                <form>
                    <input type="text" value="">
                    <input type="submit" value="">
                </form>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>

<div class="header_btm">
    <div class="wrap">
        <div class="header_sub">
            <div class="h_menu">
                <ul>
                    <% if (!shop.getIsLogin(request)) { %>
                    <li id="main"><a href="javascript:showBg();"> 登录 </a></li>
                    <% } else { %>
                    <li><a href="logout.jsp"> 残忍离去 </a></li>
                    <% }%>
                    |
                    <li class="active"><a href="index.jsp">首页</a></li>
                    |
                    <li><a href="GoodsServlet?action=showGoods&goodsClassId=-1">在线购物</a></li>
                    |
                    <li><a href="GoodsServlet?action=showGoods&goodsClassId=4&goodsClassId=5"> 挎包</a></li>
                    |
                    <li><a href="GoodsServlet?action=showGoods&goodsClassId=2&goodsClassId=3"> 上衣 </a></li>
                    |
                    <li><a href="GoodsServlet?action=showGoods&goodsClassId=1">鞋品</a></li>
                    |
                    <li><a href="service.jsp"> 店铺简介 </a></li>
                    <% if (shop.getIsLogin(request)) { %>
                    |
                    <li><a href="showIndent.jsp"> 查看订单 </a></li>
                    <% }
                        ;%>
                </ul>
            </div>
            <%--滚动推荐商品栏--%>
            <div class="top-nav">
                <nav class="nav">
                    <a href="details.jsp" id="w3-menu-trigger"> </a>
                    <ul class="nav-list" style="">
                        <li class="nav-item"><a class="active" href="index.jsp">Home</a></li>
                        <li class="nav-item"><a href="sale.jsp">Sale</a></li>
                        <li class="nav-item"><a href="handbags.jsp">Handbags</a></li>
                        <li class="nav-item"><a href="goodsList.jsp">Accessories</a></li>
                        <li class="nav-item"><a href="shoes.jsp">Shoes</a></li>
                        <li class="nav-item"><a href="service.jsp">Services</a></li>
                        <li class="nav-item"><a href="register.jsp">Contact</a></li>
                    </ul>
                </nav>
                <div class="search_box">
                    <form>
                        <input type="text" value="Search" onFocus="this.value = '';"
                               onBlur="if (this.value == '') {this.value = 'Search';}"><input type="submit" value="">
                    </form>
                </div>
                <div class="clear"></div>
                <script src="js/responsive.menu.js"></script>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>

<%--用户登录页面--%>
<jsp:include page="login.jsp"></jsp:include>

</body>
</html>