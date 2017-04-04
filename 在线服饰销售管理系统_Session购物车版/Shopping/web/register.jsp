<%@ page import="com.xin.bean.ShopUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 注册界面 </title>

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

    <%--用ajax技术来传递注册数据--%>
    <script type="text/javascript" src="js/verifyRegister_ajax.js"></script>
</head>
<body>

<%--<jsp:useBean id="user" scope="request" class="com.xin.bean.ShopUser"></jsp:useBean>--%>
<%--<jsp:setProperty name="user" property="*"></jsp:setProperty>--%>
<%--<jsp:useBean id="userService" scope="page" class="com.xin.service.UserServiceImpl"></jsp:useBean>--%>
<%--<%
    String msg = "";
    String submit = request.getParameter("submit");
    if (submit != null && !submit.equals("")) {
        if (userService.register(user)) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userid", user.getUserid());
            response.sendRedirect("index.jsp");
        } else if (userService.getMsg() != null) {
            msg = userService.getMsg();
        } else {
            msg = "注册时出现错误，请稍后再试";
        }
    }
    if (msg != null) {
        out.print(msg);
    }
%>--%>

<!-- start header -->
<jsp:include page="head.jsp"></jsp:include>
<!-- start main -->
<div class="main_bg">
    <div class="wrap">
        <div class="main">
            <div class="contact">
                <div class="contact-form">
                    <h2>注册信息如下：</h2>
                    <form method="post" action="">
                        <div>
                            <span><label>用户名</label>   </span>
                            <span >
                                <input id="reg_username" name="username" type="text" class="textbox" >
                            </span>
                            <span id="errormsg1" class="errormsg"></span>
                        </div>
                        <div>
                            <span><label>密码</label></span>
                            <span><input id="reg_password" name="password" type="password" class="textbox"></span>
                            <span id="errormsg2" class="errormsg"></span>
                        </div>
                        <div>
                            <span><label>确认密码</label></span>
                            <span><input id="repassword1" name="repassword" type="password" class="textbox"></span>
                            <span id="errormsg3" class="errormsg"></span>
                        </div>
                        <div>
                            <span><label>真实姓名 </label></span>
                            <span><input id="realname" name="realname" type="text" class="textbox"></span>
                            <span id="errormsg4" class="errormsg"></span>
                        </div>
                        <div>
                            <span><label>性别</label></span>
                            <span><input type="radio"  name="sex" value="M" checked="checked"/>
                            <img src="/images/boypic.gif" width="21" height="25"/>
                            <input type="radio" name="sex" value="W"/>
                            <img src="/images/girlpic.gif" width="21" height="25"/></span>
                        </div>
                        <div>
                            <span><label>E-mail</label></span>
                            <span><input id="userEmail" name="userEmail" type="text" class="textbox"></span>
                            <span id="errormsg5" class="errormsg"></span>
                        </div>
                        <div>
                            <span><label>电话</label></span>
                            <span><input id="userPhone" name="userPhone" type="text" class="textbox"></span>
                            <span id="errormsg6" class="errormsg"></span>
                        </div>
                        <div>
                            <span><label>收货地址</label></span>
                            <span><input id="userAddress" name="userAddress" type="text" class="textbox"></span>
                            <span id="errormsg7" class="errormsg"></span>
                        </div>
                        <div>
                            <span><input type="button" name="submit" onclick="verifyRegister()" value="提交"></span>
                        </div>
                    </form>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>


<!-- start footer -->
<jsp:include page="foot.jsp"></jsp:include>

</body>
</html>