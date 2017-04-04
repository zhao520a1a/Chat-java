<%@ page import="com.xin.bean.Goods" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title> 上衣 </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="js/jquery.min.js"></script>
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
<!-- start main -->


<div class="main_bg">
    <div class="wrap">
        <div class="main">
            <h2 class="style top"> Cloths</h2>
            <!-- start grids_of_3 -->
            <%
                List<Goods> goodsList = (List<Goods>) request.getAttribute("goodsList");
                for (int i = 0; i < goodsList.size(); i++) {
                    /*class="grids_of_3":一行的格式设定(这里是一行包含3个)，class="clear"：相当于换行*/
            %>
            <div class="grids_of_3">
                <div class="grid1_of_3">
                    <a href="details.jsp?id=<%=goodsList.get(i).getGoodsId()%>">
                        <img src="images/<%=goodsList.get(i).getImg()%>  " alt=""/>
                        <h3><%=goodsList.get(i).getGoodsName()%>
                        </h3>
                        <div class="price">
                            <h4><%=goodsList.get(i).getPrice() %><span>查看 </span></h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
            </div>
            <%
                if( (i + 1) % 3 == 0) {
            %>
            <div class="clear"></div>
            <%
                    }
                }
            %>
            <div class="clear">
            <!-- end grids_of_3 -->
        </div>
    </div>
</div>

<!-- start footer -->
<jsp:include page="foot.jsp"></jsp:include>

</body>
</html>