<%--
  Created by IntelliJ IDEA.
  User: 小鑫哦
  Date: 2016/12/8 0008
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<link href="css/login.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="js/verifyLogin_ajax.js"></script>


<div id="fullbg"></div>   <%--遮罩层--%>

<div id="login-page" class="login-page">
    <p class="close"><a href="#" onclick="closeBg();">关闭</a></p>
    <div class="login-body">
        <h1> 用户登录:</h1>   <h4><span id="result" style="color:brown"></span></h4>
        <br/>
        <input type="text" class="user" id="username1" name="username" placeholder="用户名">
        <input type="password" id="password1" name="password" placeholder="密码">
        <!--
        <div class="loginform_row">
        <label>验证码:</label>
        <input type="text" class="loginform_input_validationCode" name="validationCode"/>
        <img class="validationCode_img" src="/UserLogin/Sample1/validationCode">
        </div>
        -->
        <input name="submit" type="button" onclick="verifyLogin()" value="登录">
        <div class="forgot-grid">
            <label class="checkbox"><input type="checkbox" name="checkbox"> <i>记住我</i>



            </label>

            <div class="forgot">
                <a href="#">忘记密码?</a>
            </div>
        </div>

        <div>
            <h6> 没有账户? <a href="register.jsp">立即注册 »</a></h6>
        </div>
    </div>
</div>




