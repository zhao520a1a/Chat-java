<%@ page import="com.xin.bean.Goods" %>
<%@ page import="com.xin.bean.GoodsClass" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/pintuer.js"></script>

    <script type="text/javascript" src="js/goods.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改商品信息</strong></div>
    <div class="body-content">
        <form method="post" class="form-x"  action="goodslist.jsp" onsubmit="return updateGoods();">
            <div class="form-group">

                <%
                    Goods goods = (Goods) session.getAttribute("goods");
                %>
               <input type="hidden" id="goodsId" value="<%=goods.getGoodsId()%>">
                <div class="label">
                    <label>商品编号：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="<%=goods.getGoodsNo()%>"  id= "goodsNo" name="goodsNo" data-validate="required:请输入商品编号"/>
                    <div class="tips"></div>
                </div>
            </div>

            <if condition="$iscid eq 1">
                <div class="form-group">
                    <div class="label">
                        <label>商品类别：</label>
                    </div>
                    <div class="field">
                        <select id="goodsClassId"  name="goodsClassId" class="input w50">
                            <%
                                List<GoodsClass> goodsClassList = (List<GoodsClass>) session.getAttribute("goodsClassList");
                                for (GoodsClass goodsClass : goodsClassList) {
                            %>
                            <option  value="<%=goodsClass.getGoodsClassId()%>"><%=goodsClass.getClassname()%>
                            </option>
                            <%
                                }
                            %>
                        </select>
                        <div class="tips"></div>
                    </div>
                </div>
            </if>

            <div class="form-group">
                <div class="label">
                    <label>商品名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="goodsName"  name="goodsName" value="<%=goods.getGoodsName()%>" data-validate="required:请输入商品名称"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>商品价格：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="goodsPrice" name="goodsPrice" value="<%=goods.getPrice()%>"  data-validate="required:请输入商品价格"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>商品进货量：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="amount"  name="amount" value="<%=goods.getAmount()%>" data-validate="required:请输入商品进货量,number:进货量必须为数字"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>生产厂家：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="provider" name="provider" value="<%=goods.getProvider()%>" data-validate="required:请输入生成厂家名称"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品描述：</label>
                </div>
                <div class="field">
                    <textarea class="input" id="goodsContent" name="goodsContent" style=" height:90px;"> <%=goods.getContent()%></textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="clear"></div>

            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <input class="button bg-main icon-check-square-o" type="submit"/>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>