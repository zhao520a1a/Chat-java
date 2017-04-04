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
    <script type="text/javascript" src="laydate/laydate.js"></script> <%--日期控件--%>
    <script type="text/javascript" src="js/goods.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加商品信息</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" name="form-addGoods" action="goodslist.jsp" onsubmit="return addGoods();">
            <div class="form-group">
                <div class="label">
                    <label>商品编号：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value=""  id= "goodsNo" name="goodsNo" data-validate="required:请输入商品编号"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品图片：</label>
                </div>
                <div class="field">
                    <%--<input type="file" id="url1" name="img" class="input tips" style="width:25%; float:left;" value=""--%>
                           <%--data-toggle="hover" data-place="right" data-image=""/>--%>
                    <input type="file" class="button bg-blue margin-left" id="goodsImg" name="goodsImg" value="+ 浏览上传"
                           style="float:left;">
                    <%--<div class="tipss">图片尺寸：500*500</div>--%>
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
                <%--<div class="form-group">--%>
                <%--<div class="label">--%>
                <%--<label>其他属性：</label>--%>
                <%--</div>--%>
                <%--<div class="field" style="padding-top:8px;"> --%>
                <%--首页 <input id="ishome"  type="checkbox" />--%>
                <%--推荐 <input id="isvouch"  type="checkbox" />--%>
                <%--置顶 <input id="istop"  type="checkbox" /> --%>
                <%--</div>--%>
                <%--</div>--%>
            </if>

            <div class="form-group">
                <div class="label">
                    <label>商品名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="goodsName" name="goodsName" data-validate="required:请输入商品名称"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>商品价格：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="goodsPrice" name="goodsPrice" data-validate="required:请输入商品价格"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>商品进货量：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="amount"  name="amount" data-validate="required:请输入商品进货量,number:进货量必须为数字"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>生产厂家：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="provider" name="provider" data-validate="required:请输入生成厂家名称"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商品描述：</label>
                </div>
                <div class="field">
                    <textarea class="input" id="goodsContent" name="goodsContent" style=" height:90px;"></textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="clear"></div>

            <div class="form-group">
                <div class="label">
                    <label>更新时间：</label>
                </div>
                <div class="field">
                    <input type="text" class="laydate-icon input w50" id="regtime" name="regtime"
                           onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"

                           style="padding:10px!important; height:auto!important;border:1px solid #ddd!important;"/>
                    <div class="tips"></div>
                </div>
            </div>

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