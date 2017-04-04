<%@ page import="com.xin.bean.Goods" %>
<%@ page import="com.xin.bean.IndentList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.xin.service.ShopServiceImpl" %>
<%@ page import="com.xin.service.IShopService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src=" js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/etao.js"></script>
    <script type="text/javascript" src="js/updateCart.js"></script>
    <link href="css/etao.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="css/cart.css" media="screen" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css"/>
    <title>订单确认</title>
</head>
<body class="container">
<script type="text/javascript">
    $(document).ready(function () {
        $("#new_address_dialog").on("show.bs.modal", function (e) {
            loadPage("new_address_content_div", "<?php echo $this->baseUrl . '/system/address/add.ajax/cid/' . $this->customerId . '/from/' . base64_encode($_SERVER['REQUEST_URI']); ?>");
        });

        $("#new_address_dialog").on("hidden.bs.modal", function (e) {
            $(this).removeData("bs.modal");
        });

        $("#new_address_button").click(function () {
            $.ajax({
                url: "<?php echo $this->baseUrl . '/system/address/save.ajax' ?>",
                type: 'POST',
                data: {
                    "customer": $('#customer').val(),
                    "address": $('#address').val(),
                    "zip_code": $('#zip_code').val(),
                    "telephone": $('#telephone').val(),
                    "cell_phone": $('#cell_phone').val(),
                    "contact_person": $('#contact_person').val(),
                    "province_name": $('#province_name').val(),
                    "city_name": $('#city_name').val(),
                    "area_name": $('#area_name').val(),
                    "is_default": 0
                },
                error: function () {
                    alert("添加收货地址出错!!");
                },
                success: function (msg) {
                    //alert(msg);
                    $(".address-list li").removeClass("selected");
                    $(".address-list").append("<li class='selected'><input type='radio' id='addr_0' name='ship_to1' value='' checked /><label for='addr_0'>" + msg + "</label>");
                    $('#ship_to').val(msg);
                    $("#new_address_dialog").modal("hide");
                }
            });
        });

        $(".address-list li :radio").click(function () {
            $(this).parent().parent().addClass("selected").siblings().removeClass("selected");
        });

        $("#rebate").blur(function () { //确保返利的数字有效
            item_qty = parseInt($(this).val());
            if (isNaN(item_qty) || (item_qty == 0)) {
                $(this).val($("#max_rebate").val());
            }
        });

        $("#credit").blur(function () { //确保信用额度的数字有效
            item_qty = parseInt($(this).val());
            if (isNaN(item_qty) || (item_qty == 0)) {
                $(this).val(0);
            }
        });

        $("#new_iris_project_dialog").on("show.bs.modal", function (e) {
            loadPage("new_iris_project_content_div", "<?php echo $this->baseUrl . '/trans/iris/add.ajax/'; ?>");
        });

        $("#new_iris_project_dialog").on("hidden.bs.modal", function (e) {
            $(this).removeData("bs.modal");
        });

        $("#new_iris_project_button").click(function () {
            $.ajax({
                url: "<?php echo $this->baseUrl . '/trans/iris/save.ajax' ?>",
                type: 'POST',
                data: {
                    "db_action": $('#db_action').val(),
                    "customer_id": $('#customer_id').val(),
                    "project_name": $('#project_name').val(),
                    "project_site": $('#project_site').val(),
                    "project_cycle": $('#project_cycle').val(),
                    "project_vendor": $('#project_vendor').val(),
                    "freezing_medium": $('#freezing_medium').val(),
                    "is_valid": $('#is_valid').val()
                },
                error: function () {
                    alert("添加项目出错!!");
                },
                success: function (msg) {
                    $("#new_iris_project_dialog").modal("hide");
                    location.reload();
                }
            });
        });
    });

    function saveAddress() {
        $.ajax({
            type: "post",
            url: "<?php echo $this->baseUrl . '/system/address/save.ajax';?>",
            data: {
                "customer": $('#customer').val(),
                "address": $('#address').val(),
                "zip_code": $('#zip_code').val(),
                "telephone": $('#telephone').val(),
                "cell_phone": $('#cell_phone').val(),
                "contact_person": $('#contact_person').val(),
                "province_name": $('#province_name').val(),
                "city_name": $('#city_name').val(),
                "area_name": $('#area_name').val(),
                "is_default": 0
            },
            error: function (msg) {
                alert("Error:" + msg);
            },
            success: function (msg) {
                $(".address-list li").removeClass("selected");
                $(".address-list").append("<li class='selected'><input type='radio' id='addr_0' name='ship_to1' value='' checked /><label for='addr_0'>" + msg + "</label>");
                $('#ship_to').val(msg);
                closeDiv('new_address_div');
            }
        });
    }

    //数据检查
    function beforePost() {
        if ($("#terms").prop("checked") == false) {
            alert("提交订单前，请阅读《本店销售条款和条件》");
            return false;
        }
        return true;
    }
    function checkForm() {
        if (true == beforePost()) {
//            $('#order_confirm_form').submit();
            var priceTotal = $("#priceTotal").val()
            var content = $("#user_content").val();
//            alert(content);

            //2.将数据传给Servlet
            $.ajax({
                type: "POST",
                url: "IndentServlet",
                data: "action=payout&priceTotal=" + encodeURI(encodeURI(priceTotal)) + "&content=" + encodeURI(encodeURI(content)),
                dataType: "text", //返回的数据格式
                success: payout  //定义回调函数
            })
        }
    }

    //3.接收返回的纯文本数据-->data
    function payout(data) {
        if (data == "成功生成订单") {
            alert("你的购物车中的商品已提交给本店，请耐心等待我的发货！点击确定返回首页");
            window.location.href = "index.jsp";
        } else {
            alert(data);
        }
    }

    <%--返回上一级--%>
    function returnBefore() {
        window.history.back();
    }


</script>


<div class="row">
    <div class="col-lg-2 col-md-2 col-sm-2 col-xs-3 hidden-print">
        <%--左导航--%>
    </div>
    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-9" id="workspace" role="main">
        <div class="row hidden-print">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <ol class="breadcrumb">
                    <li><a href="index.jsp">首页</a></li>
                    <li><a href="#" onclick="returnBefore()">返回上一级</a></li>
                    <li class="active">确认购买</li>
                </ol>
            </div>
        </div>

        <form style="  margin-bottom:40px;" class="form-horizontal" id="order_confirm_form" name="order_confirm_form"
              method="post"
              action="nowbuy.jsp?payout=true">
            <input type="hidden" id="db_action" name="db_action" value="insert"/>
            <input type="hidden" id="freight_fee" name="freight_fee" value="0"/>

            <!-- 订单信息 -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="well well-sm">

                        <fieldset>
                            <legend>订单信息</legend>
                            <div class="form-group">
                                <label
                                        class="col-lg-2 col-md-2 col-sm-2 col-xs-12 control-label"
                                        for="payment_term">付款方式</label>
                                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
                                    <select class="form-control input-sm" id="payment_term"
                                            name="payment_term" required requireText="请选择付款方式">
                                        <option>货到付款</option>
                                        <option>支付包付款</option>
                                        <option>微信付款</option>
                                        <option>网银付款</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label
                                        class="col-lg-2 col-md-2 col-sm-2 col-xs-12 control-label">订单备注</label>
                                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-12">
									<textarea class="form-control input-sm" id="user_content"
                                              name="content" rows="5" maxLength="150">   </textarea>
                                    <p class="help-block">留言请控制在150个汉字以内</p>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>
            </div>

            <!-- 收货地址 -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="well well-sm">
                        <fieldset>
                            <legend>
                                收货地址
                                <div class="btn-toolbar pull-right hidden-print" role="tolbar">
                                    <div class="btn-group btn-group-sm">
                                        <button type="button" class="btn btn-default"
                                                title="添加并使用新的收货地址" data-toggle="modal"
                                                data-target="#new_address_dialog">使用新地址
                                        </button>
                                    </div>
                                </div>
                            </legend>
                            <div class="form-group">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="address">
                                        <ul class="address-list data-list">
                                            <li><label><input type="radio" name="ship_to1"
                                                              onclick="$('#ship_to').val($(this).parent().text());"/>这里是客户的收货地址</label>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>
            </div>

            <!-- 购买明细 -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                    <div class="well well-sm table-responsive">
                        <fieldset>
                            <legend>
                                购买明细
                                <%
                                    String nowBuyGoodsId = (String) request.getParameter("nowBuyGoodsId");
                                    if (nowBuyGoodsId == null)  /*有商品页面直接立即支付，没有“回到购物车”功能*/ { %>
                                <div class="btn-toolbar pull-right hidden-print" role="tolbar">
                                    <div class="btn-group btn-group-sm">
                                        <a class="btn btn-default" role="button"
                                           href="showCart.jsp">回到购物车，修改产品</a>
                                    </div>
                                </div>
                                <%}%>

                            </legend>
                            <table class="table table-condensed table-hover">
                                <thead>
                                <tr>
                                    <th>产品型号</th>
                                    <th>产品名称</th>
                                    <th>生产工厂</th>
                                    <th>单价</th>
                                    <th>数量</th>
                                    <th>小计</th>
                                </tr>
                                </thead>

                                <%
                                    IShopService shop = new ShopServiceImpl();

                                    List<IndentList> nowbuyList = (List<IndentList>) session.getAttribute("nowbuyList");
                                    if (nowbuyList != null) {
                                        for (int i = 0; i < nowbuyList.size(); i++) {
                                            IndentList iList = nowbuyList.get(i);
                                            Goods goods = shop.queryGoodsInfo(iList.getGoodsId());
                                %>
                                <form method="post" action="showCart.jsp">
                                    <%--隐藏传递商品Id信息--%>
                                    <input type="hidden" name="goodsId" value="<%=iList.getGoodsId()%>"/>
                                    <%--<tbody> --%>
                                    <tbody>
                                    <tr>
                                        <td>
                                            <%=goods.getGoodsNo()%>
                                        </td>
                                        <td>
                                            <%=goods.getGoodsName()%>
                                        </td>
                                        <td>
                                            <%=goods.getPrivoder()%>
                                        </td>
                                        <td>
                                            <%=goods.getPrice()%>
                                        </td>
                                        <td>
                                            <%=iList.getAmount()%>
                                        </td>
                                        <td>
                                            <%=iList.getAmount() * goods.getPrice()%>
                                        </td>
                                    </tr>
                                    </tbody>
                                    <%--</tbody>--%>
                                </form>
                                <%
                                        }
                                    }
                                %>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="7" class="text-right">
                                        <p class="form-control-static">
                                            产品合计：<span class="large-bold-red">
                                            <%=request.getParameter("priceTotal")%>
                                            <input type="hidden" id="priceTotal" name="priceTotal"
                                                   value=" <%=request.getParameter("priceTotal")%>"/>
                                            ￥</span>
                                        </p>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                        </fieldset>
                    </div>
                </div>
            </div>

            <!-- 客户返利 -->
            <div class="row ">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class=" well well-sm table-responsive">
                        <fieldset>
                            <legend>我的返利</legend>
                            <table class="table table-condensed table-hover">
                                <thead>
                                <tr>
                                    <th>客户代码</th>
                                    <th>客户名称</th>
                                    <th>财年</th>
                                    <th>返利金额</th>
                                    <th>已用金额</th>
                                    <th>剩余金额</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td colspan="6"><span class="empty-content">系统中没有您的返利数据。</span></td>
                                </tr>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="6">
                                        <div class="text-right">
                                            您最多可以使用返利支付<span class="smaller-bold-red">90%</span>的订单金额。
                                            本次可支付￥<input type="number" min="0" max="100"
                                                         class="small-bold-red number" id="rebate" name="rebate"
                                                         value="$rebate" style="width: 100px; margin: 0 3px;"/>元，
                                            使用<input id="use_rebate" name="use_rebate" value="1"
                                                     type="checkbox">
                                        </div>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                        </fieldset>
                    </div>
                </div>
            </div>

            <!-- 信息额度 -->
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="well well-sm table-responsive">
                        <fieldset>
                            <legend>我的信用额度</legend>
                            <table class="table table-condensed table-hover">
                                <thead>
                                <tr>
                                    <th>起始日</th>
                                    <th>结束日</th>
                                    <th>信用额度</th>
                                    <th>上期账单</th>
                                    <th>上期还款</th>
                                    <th>本期账单</th>
                                    <th>本期还款</th>
                                    <th>冻结金额</th>
                                    <th>
                                        <abbr title="可用金额 = 信用额度 - 上期账单 + 上期还款 - 本期账单 + 本期还款 - 本期冻结金额 - 调整金额">可用金额</abbr>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td colspan="9"><span class="empty-content">系统中没有您的信用额度数据。</span></td>
                                </tr>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="9">
                                        <div style="float: right; margin-right: 4px;">
                                            您可以使用信用额度支付￥<input type="number" min="0" max="100"
                                                               class="small-bold-red number" id="credit" name="credit"
                                                               value="0" style="width: 100px; margin: 0 3px;">
                                        </div>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                        </fieldset>
                    </div>
                </div>
            </div>

            <!-- 背对背订单、最终用途、销售条款 -->
            <div class="row" style="margin-bottom:20px;">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                    <div class="checkbox pull-right">
                        <label> <input type="checkbox" id="terms" name="terms"/>
                            我已阅读并同意《<a target="_blank" href="#">小二班旗舰店销售条款和条件</a>》
                        </label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-offset-3 col-md-offset-3 col-sm-offset-3 col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <input type="button" class="btn btn-lg btn-block btn-primary" id="btn_submit_order" name="payout"
                           onclick="checkForm() " value=" 提交订单"/>
                </div>
            </div>
        </form>
    </div>

    <!-- 添加收货地址模式窗体 -->
    <div class="modal fade" role="dialog" tabIndex="-1"
         id="new_address_dialog" aria-labelledby="new_address_modal_label"
         aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="new_address_modal_label">使用新收货地址</h4>
                </div>
                <div class="modal-body" id="new_address_content_div"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary"
                            id="new_address_button">保存并使用
                    </button>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>