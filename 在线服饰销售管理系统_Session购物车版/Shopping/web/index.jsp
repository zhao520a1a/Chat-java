<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 首页 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <!-- start slider -->
    <link href="css/slider.css" rel="stylesheet" type="text/css" media="all"/>
    <script type="text/javascript" src="js/modernizr.custom.28468.js"></script>
    <script type="text/javascript" src="js/jquery.cslider.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#da-slider').cslider();
        });
    </script>
    <!--  Owl Carousel：强大的响应式jQuery焦点图轮播插件  -->
    <link href="css/owl.carousel.css" rel="stylesheet">
    <!-- 修饰 -->
    <script src="js/owl.carousel.js"></script>
    <script>
        $(document).ready(function () {
            $("#owl-demo").owlCarousel({
                items: 4,
                lazyLoad: true,
                autoPlay: true,
                navigation: true,
                navigationText: ["", ""],
                rewindNav: false,
                scrollPerPage: false,
                pagination: false,
                paginationNumbers: false,
            });
        });
    </script>
    <!-- Owl Carousel插件结束 -->
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
<!--  头部 -->
<jsp:include page="head.jsp"></jsp:include>

<!--- 商品大屏滚动展示---->
<!--  大屏的内容 -->
<div id="da-slider" class="da-slider">
    <div class="da-slide">
        <h2>欢迎光临小二班旗舰店</h2>
        <p> 店铺介绍：-----------------------------------------------------------------------------------------.</p>
        <a href="GoodsServlet?action=showGoods&goodsClassId=-1" class="da-link"> 开启购物之旅 </a>
        <div class="da-img"><img src="images/manCoat_pic2.png" alt="image01"/></div>
    </div>
    <div class="da-slide">
        <h2> 行止有度 </h2>
        <p> 简介：*************************** </p>
        <a href="GoodsServlet?action=showGoods&goodsClassId=-1" class="da-link"> 开启购物之旅</a>
        <div class="da-img"><img src="images/manShoes_pic7.png" alt="image01"/></div>
    </div>
    <div class="da-slide">
        <h2> 彰显自己 </h2>
        <p> 简介：*************************** </p>
        <a href="GoodsServlet?action=showGoods&goodsClassId=-1" class="da-link"> 开启购物之旅</a>
        <div class="da-img"><img src="images/manCoat_pic3.png" alt="image01"/></div>
    </div>
    <div class="da-slide">
        <h2> 简约是态度 </h2>
        <p>简介：*************************** </p>
        <a href="GoodsServlet?action=showGoods&goodsClassId=-1" class="da-link"> 开启购物之旅</a>
        <div class="da-img"><img src="images/manCoat_pic1.png" alt="image01"/></div>
    </div>
    <nav class="da-arrows">
        <span class="da-arrows-prev"></span>
        <span class="da-arrows-next"></span>
    </nav>
</div>


<!---  图片展示---->
<div class="wrap">
    <!---- 开始图片展示---->
    <div id="owl-demo" class="owl-carousel">
        <div class="item" onClick="location.href='details.jsp?id=4';">
            <div class="cau_left">
                <img class="lazyOwl" src="images/manShoes_pic4.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.jsp?id=4"> 商务必备 </a></h4>
                <a href="details.jsp?id=4" class="btn">瞧瞧</a>
            </div>
        </div>
        <div class="item" onClick="location.href='details.jsp?id=18';">
            <div class="cau_left">
                <img class="lazyOwl" src="images/womanBag_pic4.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.jsp?id=18"> 美女最爱 </a></h4>
                <a href="details.jsp?id=18" class="btn">点我</a>
            </div>
        </div>
        <div class="item" onClick="location.href='details.jsp?id=2';">
            <div class="cau_left">
                <img class="lazyOwl" src="images/manShoes_pic2.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.jsp?id=2"> 路在脚下 </a></h4>
                <a href="details.jsp?id=2" class="btn">查看</a>
            </div>
        </div>

        <div class="item" onClick="location.href='details.jsp?id=15';">
            <div class="cau_left">
                <img class="lazyOwl" src="images/womanBag_pic1.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.jsp?id=15"> 美女最爱 </a></h4>
                <a href="details.jsp?id=15" class="btn">看看</a>
            </div>
        </div>
        <div class="item" onClick="location.href='details.jsp?id=4';">
            <div class="cau_left">
                <img class="lazyOwl" src="images/manShoes_pic4.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.jsp?id=4"> 商务必备 </a></h4>
                <a href="details.jsp?id=4" class="btn">点我</a>
            </div>
        </div>
        <div class="item" onClick="location.href='details.jsp?id=17';">
            <div class="cau_left">
                <img class="lazyOwl" src="images/womanBag_pic3.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.jsp?id=17"> 美女最爱 </a></h4>
                <a href="details.jsp?id=17" class="btn">看看</a>
            </div>
        </div>
        <div class="item" onClick="location.href='details.jsp?id=3';">
            <div class="cau_left">
                <img class="lazyOwl" src="images/manShoes_pic3.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.jsp?id=3"> 路在脚下 </a></h4>
                <a href="details.jsp?id=3" class="btn">看看</a>
            </div>
        </div>
    </div>
    <!---- 结束图片展示--->
</div>

<!-- 首页商品介绍 -->
<div class="main_bg1">
    <div class="wrap">
        <div class="main1">
            <h2> 镇店之宝</h2>
        </div>
    </div>
</div>

<!-- 热卖品 -->
<div class="main_bg">
    <div class="wrap">
        <div class="main">

            <div class="grids_of_3">
                <div class="grid1_of_3">
                    <a href="details.jsp?id=7">
                        <img src="images/manCoat_pic1.png " alt=""/>
                        <h3> 我有我的范</h3>
                        <div class="price">
                            <h4>$300<span> 查看 </span></h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
                <div class="grid1_of_3">
                    <a href="details.jsp?id=8">
                        <img src="images/manCoat_pic2.png" alt=""/>
                        <h3>蝙蝠侠同款</h3>
                        <div class="price">
                            <h4>$300<span> 查看 </span></h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
                <div class="grid1_of_3">
                    <a href="details.jsp?id=9">
                        <img src="images/manCoat_pic3.png" alt=""/>
                        <h3> Hi！瞅我干啥？</h3>
                        <div class="price">
                            <h4>$300<span> 查看 </span></h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
                <div class="clear"></div>
            </div>

            <div class="grids_of_3">
                <div class="grid1_of_3">
                    <a href="details.jsp?id=19">
                        <img src="images/manShoes_pic7.png" alt=""/>
                        <h3>不服，来战！</h3>
                        <div class="price">
                            <h4>$300<span> 查看 </span></h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
                <div class="grid1_of_3">
                    <a href="details.jsp?id=15">
                        <img src="images/womanBag_pic1.jpg" alt=""/>
                        <h3>哎呀妈！这包我喜欢</h3>
                        <div class="price">
                            <h4>$300<span> 查看 </span></h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
                <div class="grid1_of_3">
                    <a href="details.jsp?id=20">
                        <img src="images/manShoes_pic8.png" alt=""/>
                        <h3>对，哥就是低调！</h3>
                        <div class="price">
                            <h4>$300<span> 查看 </span></h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
                <div class="clear"></div>
            </div>

        </div>
    </div>
</div>

<!-- 尾部 -->
<jsp:include page="foot.jsp"></jsp:include>


</body>
</html>
