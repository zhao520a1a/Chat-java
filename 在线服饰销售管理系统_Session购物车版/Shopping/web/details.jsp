<%@ page import="com.xin.bean.Goods" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 商品详细信息 </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- start details -->
    <link rel="stylesheet" type="text/css" href="css/productviewgallery.css" media="all"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/cloud-zoom.1.0.3.min.js"></script>
    <script type="text/javascript" src="js/jquery.fancybox.pack.js"></script>
    <script type="text/javascript" src="js/jquery.fancybox-buttons.js"></script>
    <script type="text/javascript" src="js/jquery.fancybox-thumbs.js"></script>
    <script type="text/javascript" src="js/productviewgallery.js"></script>
    <!-- start top_js_button -->
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1200);
            });
        });
        jQuery(document).ready(function ($) {
            /*
             为数量调整的＋ －号提供单击事件
             * 为购物车中每一行绑定单击事件，以及每行中的输入框绑定键盘事件
             * 根据触发事件的元素执行不同动作
             *   增加数量
             *   减少数量
             *
             */
            var goodNum = $("#goodNum");
            $(goodNum).each(function () {
                var input = $(this).find(":text");
                //为数量调整按钮
                $(this).click(function () {
                    var val = parseInt($(input).val());
                    if (isNaN(val) || (val < 1)) {
                        val = 1;
                    }
                    if ($(window.event.srcElement).hasClass("minus")) {
                        if (val > 1) val--;
                        input.val(val);
                    }
                    else if ($(window.event.srcElement).hasClass("plus")) {
                        if (val < 9999) val++;
                        input.val(val);
                    }
                });
            });
        });
    </script>

    <%--采用ajax技术来向购物车中添加商品--%>
<script type="text/javascript" src="js/addToCart_ajax.js"></script>

    <%--采用ajax技术实现立即购买商品功能--%>
    <script type="text/javascript" src="js/nowbuy_ajax.js"></script>

</head>
<body>

<!-- start header -->
<jsp:include page="head.jsp"></jsp:include>
<!-- start main -->
<jsp:useBean id="shop" scope="page" class="com.xin.service.ShopServiceImpl"></jsp:useBean>
<%
    int goodsId = Integer.parseInt(request.getParameter("id"));
    Goods goods = shop.queryGoodsInfo(goodsId);
    if (goods != null) {
%>


<div class="main_bg">
    <div class="wrap">
        <div class="main">
            <!-- start content -->
            <div class="single">
                <!-- start span1_of_1 -->
                <div class="left_content">
                    <div class="span1_of_1">
                        <!-- start product_slider -->
                        <div class="product-view">
                            <div class="product-essential">
                                <div class="product-img-box">
                                    <div class="more-views" style="float:left;">
                                        <div class="more-views-container">
                                            <ul>
                                                <li>
                                                    <a class="cs-fancybox-thumbs" data-fancybox-group="thumb"
                                                       style="width:64px;height:85px;" href="" title="" alt="">
                                                        <img src="" src_main="" title="" alt=""/></a>
                                                </li>
                                                <li>
                                                    <a class="cs-fancybox-thumbs" data-fancybox-group="thumb"
                                                       style="width:64px;height:85px;" href="" title="" alt="">
                                                        <img src="" src_main="" title="" alt=""/></a>
                                                </li>
                                                <li>
                                                    <a class="cs-fancybox-thumbs" data-fancybox-group="thumb"
                                                       style="width:64px;height:85px;" href="" title="" alt="">
                                                        <img src="" src_main="" title="" alt=""/></a>
                                                </li>
                                                <li>
                                                    <a class="cs-fancybox-thumbs" data-fancybox-group="thumb"
                                                       style="width:64px;height:85px;" href="" title="" alt="">
                                                        <img src="" src_main="" title="" alt=""/></a>
                                                </li>
                                                <li>
                                                    <a class="cs-fancybox-thumbs" data-fancybox-group="thumb"
                                                       style="width:64px;height:85px;" href="" title="" alt="">
                                                        <img src="" src_main="" title="" alt=""/></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="product-image">
                                        <a class="cs-fancybox-thumbs cloud-zoom"
                                           rel="adjustX:30,adjustY:0,position:'right',tint:'#202020',tintOpacity:0.5,smoothMove:2,showTitle:true,titleOpacity:0.5"
                                           data-fancybox-group="thumb" href="images/0001-2.jpg" title="Women Shorts"
                                           alt="Women Shorts">
                                            <img src="images/0001-2.jpg" alt="Women Shorts" title="Women Shorts"/>
                                        </a>
                                    </div>
                                    <script type="text/javascript">
                                        var prodGallery = jQblvg.parseJSON('{"prod_1":{"main":{"orig":"images/0001-2.jpg","main":"images/large/0001-2.jpg","thumb":"images/small/0001-2.jpg","label":""},"gallery":{"item_0":{"orig":"images/0001-2.jpg","main":"images/large/0001-2.jpg","thumb":"images/small/0001-2.jpg","label":""},"item_1":{"orig":"images/0001-1.jpg","main":"images/large/0001-1.jpg","thumb":"images/small/0001-1.jpg","label":""},"item_2":{"orig":"images/0001-5.jpg","main":"images/large/0001-5.jpg","thumb":"images/small/0001-5.jpg","label":""},"item_3":{"orig":"images/0001-3.jpg","main":"images/large/0001-3.jpg","thumb":"images/small/0001-3.jpg","label":""},"item_4":{"orig":"images/0001-4.jpg","main":"images/large/0001-4.jpg","thumb":"images/small/0001-4.jpg","label":""}},"type":"simple","video":false}}'),
                                                gallery_elmnt = jQblvg('.product-img-box'),
                                                galleryObj = new Object(),
                                                gallery_conf = new Object();
                                        gallery_conf.moreviewitem = '<a class="cs-fancybox-thumbs" data-fancybox-group="thumb" style="width:64px;height:85px;" href=""  title="" alt=""><img src="" src_main="" width="64" height="85" title="" alt="" /></a>';
                                        gallery_conf.animspeed = 200;
                                        jQblvg(document).ready(function () {
                                            galleryObj[1] = new prodViewGalleryForm(prodGallery, 'prod_1', gallery_elmnt, gallery_conf, '.product-image', '.more-views', 'http:');
                                            jQblvg('.product-image .cs-fancybox-thumbs').absoluteClick();
                                            jQblvg('.cs-fancybox-thumbs').fancybox({
                                                prevEffect: 'none',
                                                nextEffect: 'none',
                                                closeBtn: true,
                                                arrows: true,
                                                nextClick: true,
                                                helpers: {
                                                    thumbs: {
                                                        width: 64,
                                                        height: 85,
                                                        position: 'bottom'
                                                    }
                                                }
                                            });
                                            jQblvg('#wrap').css('z-index', '100');
                                            jQblvg('.more-views-container').elScroll({
                                                type: 'vertical',
                                                elqty: 4,
                                                btn_pos: 'border',
                                                scroll_speed: 400
                                            });

                                        });

                                        function initGallery(a, b, element) {
                                            galleryObj[a] = new prodViewGalleryForm(prods, b, gallery_elmnt, gallery_conf, '.product-image', '.more-views', '');
                                        }
                                        ;
                                    </script>
                                </div>
                            </div>
                        </div>
                        <!-- end product_slider -->
                    </div>
                    <!-- start span1_of_1 -->

                    <div class="span1_of_1_des">
                        <div class="desc1">
                            <h3 style="font-weight: bold"><%=goods.getGoodsName()%>  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                &nbsp; &nbsp;
                                &nbsp; &nbsp; &nbsp; &nbsp;  </h3>
                            <div style="font-size: 20px;">
                                简介：
                                <p> 商品型号： <%=goods.getGoodsNo() %>
                                <p> 库存量： <%=goods.getLeave_amount() %>
                                <p> 生产厂家：   <%=goods.getPrivoder() %>
                                </p>
                                <p>
                                    <% if (goods.getContent() != null) {
                                    %>  描述： <%=goods.getContent()%>
                                    <%}%>
                                </p>
                            </div>

                            <h5>  <%=goods.getPrice()%> ￥
                                <input type="hidden" id="goodsPrice" value="<%=goods.getPrice()%>"><%--隐藏传递商品价格--%>
                                <a href="#" onclick="goodsToNowBuy()">
                                    <span style="color: brown; font_size:18px"> 立即购买</span>
                                </a>
                            </h5>
                            <div class="available">
                                <%--<h4> 选择 :</h4>--%>
                                <div class="btn_form">
                                    <form method="post" action="#">
                                        <ul>
                                            <%--<li>颜色:
                                                <select>
                                                    <option>黑色</option>
                                                    <option>白色</option>
                                                    <option>蓝色</option>
                                                    <option>红色</option>
                                                </select></li>
                                            <li>Size:
                                                <select>
                                                    <option>XL</option>
                                                    <option>L</option>
                                                    <option>M</option>
                                                    <option>S</option>
                                                </select>
                                            </li>--%>
                                            <input type="hidden" id="goodsId" name="goodsId"
                                                   value="<%=goods.getGoodsId() %>"/> <%--商品的编号--%>
                                            <li id="goodNum">
                                                数量:
                                                <div class="input-group input-group-sm" style="width:100px;">
                                                    <span class="input-group-addon minus">-</span>
                                                    <%--购买的数量--%>
                                                    <input  id="saleAmount" type="text" class="number form-control input-sm" value="1"
                                                           name="saleAmount"/>
                                                    <span class="input-group-addon plus">+</span>
                                                </div>
                                            </li>
                                        </ul>
                                        <input type="button" value="加入购物车" onclick="addToCart()"/>

                                    </form>
                                </div>

                                <span class="span_right"><a href="#"> 加入收藏 </a></span>
                                <div class="clear"></div>
                            </div>
                            <div class="share-desc">
                                <div class="share">
                                    <h4>分享 :</h4>
                                    <ul class="share_nav">
                                        <li><a href="#"><img src="images/facebook.png" title="facebook"></a></li>
                                        <li><a href="#"><img src="images/twitter.png" title="Twiiter"></a></li>
                                        <li><a href="#"><img src="images/gpluse.png" title="Google+"></a></li>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>

                    <div class="clear"></div>
                    <!-- start tabs -->
                    <br> <br><br>
                    <section class="tabs">
                        <input id="tab-1" type="radio" name="radio-set" class="tab-selector-1" checked="checked">
                        <label for="tab-1" class="tab-label-1">说明1</label>

                        <input id="tab-2" type="radio" name="radio-set" class="tab-selector-2">
                        <label for="tab-2" class="tab-label-2">说明2</label>

                        <input id="tab-3" type="radio" name="radio-set" class="tab-selector-3">
                        <label for="tab-3" class="tab-label-3">说明3</label>

                        <div class="clear-shadow"></div>
                        <div class="content">
                            <div class="content-1">
                                <p class="para top"><span>******</span> 商品详细简介---------- </p>
                                <ul>
                                    <li>******</li>
                                    <li>******</li>
                                    <li>******</li>
                                    <li>******</li>
                                    <li>******</li>
                                    <li>******</li>
                                </ul>
                                <div class="clear"></div>
                            </div>
                            <div class="content-2">
                                <p class="para top"><span>******</span> 商品详细简介---------- </p>
                                <ul class="qua_nav">
                                    <li>******</li>
                                    <li>******</li>
                                    <li>******</li>
                                </ul>
                            </div>
                            <div class="content-3">
                                <p class="para top"><span>******</span> 商品详细简介---------- </p>
                                <ul>
                                    <li>******</li>
                                    <li>******</li>
                                    <li>******</li>
                                    <li>******</li>
                                    <li>******</li>
                                    <li>******</li>
                                </ul>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </section>
                    <!-- end tabs -->
                </div>
                <!-- start sidebar -->
                <div class="left_sidebar">
                    <div class="sellers">
                        <h4> 热卖品 </h4>
                        <div class="single-nav">
                            <ul>
                                <li><a href="#"> XXXXXXXXXXX </a></li>
                                <li><a href="#">XXXXXXXXXXX</a></li>
                                <li><a href="#">XXXXXXXXXXX</a></li>
                                <li><a href="#">XXXXXXXXXXX</a></li>
                                <li><a href="#">XXXXXXXXXXX</a></li>
                                <li><a href="#">XXXXXXXXXXX</a></li>
                                <li><a href="#">XXXXXXXXXXX</a></li>
                                <li><a href="#">XXXXXXXXXXX</a></li>
                                <li><a href="#">XXXXXXXXXXX</a></li>
                                <li><a href="#">XXXXXXXXXXX</a></li>
                                <li><a href="#">XXXXXXXXXXX</a></li>
                                <li><a href="#">XXXXXXXXXXX</a></li>
                                <li><a href="#">XXXXXXXXXXX</a></li>
                            </ul>
                        </div>
                        <div class="banner-wrap bottom_banner color_link">
                            <a href="#" class="main_link">
                                <figure><img src="images/delivery.png" alt=""></figure>
                                <h5><span> 免邮费 </span><br> 订单超过 99￥.</h5>
                                <p> 该笔订单由商家负责运输物流费用！</p></a>
                        </div>
                        <div class="brands">
                            <h1>选择快递</h1>
                            <div class="field">
                                <select class="select1">
                                    <option> 请选择</option>
                                    <option> 中通</option>
                                    <option> 顺丰</option>
                                    <option> 圆通</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end sidebar -->
                <div class="clear"></div>
            </div>
            <!-- end content -->
        </div>
    </div>
</div>
<%
    }
    ;
%>
<!-- start footer -->
<jsp:include page="foot.jsp"></jsp:include>

</body>
</html>