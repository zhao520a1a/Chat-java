

<%@ page import="com.xin.bean.ShopUser" %>
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
    <script type="text/javascript" src="js/user.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改用户信息</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="userslist.jsp" onsubmit="return updateUser();">
            <div class="form-group">

                <%
                    ShopUser user = (ShopUser) session.getAttribute("user");
                %>
                <input type="hidden" id="userId" value="<%=user.getUserid()%>">
                <div class="label">
                    <label>用户名：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="username" value="<%=user.getUsername()%>" id="goodsNo"
                           name="goodsNo" data-validate="required:请输入用户姓名"/>
                    <div class="tips"></div>
                </div>
            </div>


            <div class="form-group">
                <div class="label">
                    <label>密码：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="password" value="<%=user.getPassword()%>"
                           data-validate="required:请输入密码"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>用户真实姓名：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="realname" value="<%=user.getRealname()%>"
                           data-validate="required:请输入用户真实姓名"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>用户性别：</label>
                </div>
                <div class="field">
                    <input type="radio" name="sex" value="M" checked="checked"/>
                    <img src="/images/boypic.gif" width="21" height="25"/>
                    <input type="radio" name="sex" value="W"/>
                    <img src="/images/girlpic.gif" width="21" height="25"/></span>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>用户电话：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="userPhone" name="provider" value="<%=user.getUserPhone()%>"
                           data-validate="required:用户电话"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>用户地址：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="userAddress" name="provider" value="<%=user.getUserAddress()%>"
                           data-validate="required:用户地址"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>用户Email：</label>
                </div>
                <div class="field">
                    <div class="field">
                        <input type="text" class="input w50" id="userEmail" name="provider" value="<%=user.getUserEmail()%>"
                               data-validate="required:用户Email"/>
                        <div class="tips"></div>
                    </div>
                </div>
            </div>
       <%--     <div class="form-group">
                <div class="label">
                    <label>用户注册时间：</label>
                </div>
                <div class="field">
                    <input type="text" class="laydate-icon input w50" id="regTime"
                           onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"
                           style="padding:10px!important; height:auto!important;border:1px solid #ddd!important;"/>
                    <div class="tips"></div>
                </div>
            </div>--%>

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