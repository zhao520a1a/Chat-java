<%@ page import="com.xin.bean.Goods" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xin.bean.GoodsClass" %>
<%--
  Created by IntelliJ IDEA.
  User: 小鑫哦
  Date: 2016/12/8 0008
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 在线购物 </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="js/jquery.min.js"></script>
    <!-- start gallery_sale -->
    <script type="text/javascript" src="js/jquery.easing.min.js"></script>
    <script type="text/javascript" src="js/jquery.mixitup.min.js"></script>
    <script type="text/javascript">
        $(function () {

            var filterList = {

                init: function () {
                    // MixItUp plugin
                    // http://mixitup.io
                    $('#portfoliolist').mixitup({
                        targetSelector: '.portfolio',
                        filterSelector: '.filter',
                        effects: ['fade'],
                        easing: 'snap',
                        // call the hover effect
                        onMixEnd: filterList.hoverEffect()
                    });

                },

                hoverEffect: function () {
                    // Simple parallax effect
                    $('#portfoliolist .portfolio').hover(
                            function () {
                                $(this).find('.label').stop().animate({bottom: 0}, 200, 'easeOutQuad');
                                $(this).find('img').stop().animate({top: -30}, 500, 'easeOutQuad');
                            },
                            function () {
                                $(this).find('.label').stop().animate({bottom: -40}, 200, 'easeInQuad');
                                $(this).find('img').stop().animate({top: 0}, 300, 'easeOutQuad');
                            }
                    );
                }
            };
            // Run the show!
            filterList.init();
        });
    </script>
    <!-- start top_js_button -->
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1200);
            });
        });
    </script>
</head>
<body>
<!-- start header -->
<jsp:include page="head.jsp"></jsp:include>
<%

%>
<!-- start main -->
<div class="main_bg">
    <div class="wrap">
        <div class="main">
            <!-- start gallery_sale  -->
            <div class="gallery1">
                <div class="container">
                    <ul id="filters" class="clearfix">
                        <li><span class="filter active"
                                  data-filter="womanCoat manCoat womanBag manShoes ">全部商品</span>
                        </li>
                        <%--data-filter通过元素的class名来过滤商品信息--%>
                        <li><span class="filter" data-filter="womanCoat">女装</span></li>
                        <li><span class="filter" data-filter="manCoat">男装</span></li>
                        <li><span class="filter" data-filter="womanBag"> 女包</span></li>
                        <li><span class="filter" data-filter="manBag"> 男包</span></li>
                        <li><span class="filter" data-filter="manShoes"> 鞋 </span></li>
                    </ul>


                    <div id="portfoliolist">
                        <%
                            List<Goods> goodsList = (List<Goods>) request.getAttribute("goodsList");
                            for (int i = 0; i < goodsList.size(); i++) {
                                GoodsClass goodsClass = goodsList.get(i).getGoodsClass();
                                String goodClassName = goodsClass.getClassname();
                                String filtername = "";
                                switch (goodClassName) {
                                    case "女士外套":
                                        filtername = "womanCoat";
                                        break;
                                    case "男士外套":
                                        filtername = "manCoat";
                                        break;
                                    case "男鞋":
                                        filtername = "manShoes";
                                        break;
                                    case "男士挎包":
                                        filtername = "manBag";
                                        break;
                                    case "女士挎包":
                                        filtername = "womanBag";
                                        break;
                                }
                        %>

                        <div class="portfolio  <%=filtername%>" data-cat="<%=filtername%>">
                            <div class="portfolio-wrapper">
                                <a href="details.jsp?id=<%=goodsList.get(i).getGoodsId()%>">
                                    <img src="images/<%=goodsList.get(i).getImg()%>" alt="Image 2" style="width: 200px" height="200px"/>
                                </a>
                                <div class="label">
                                    <div class="label-text">
                                        <div> 商品名:<%=goodsList.get(i).getGoodsName()%></div>
                                        <span class="text-category">
                                            商品价格: <%=goodsList.get(i).getPrice() %> ￥
                                        </span>
                                    </div>
                                    <div class="label-bg"></div>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <!---End-gallery----->
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>

<!-- start footer -->
<jsp:include page="foot.jsp"></jsp:include>

</body>
</html>
