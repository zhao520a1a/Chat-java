/**
 * Created by 小鑫哦 on 2016/11/26 0026.
 */

/*运用jQuery框架来进行AJAX的异步数据交互:获取服务器端返回的纯文本数据*/
function verifyLogin() {
    //1.从页面获取数据
    var username = $('#username1').val();
    var password = $('#password1').val();
    if (username == "") {
        $("#result").html("用户名为空！").show(300).delay(3000).hide(300);
    } else if (password == "") {
        $("#result").html("密码为空！").show(300).delay(3000).hide(300);
    }
    else {
        //2.将数据传给Servlet
        $.ajax({
            type: "POST",
            url: "ajaxServlet",
            data: "action=login&username=" + encodeURI(encodeURI(username)) + "&password=" + encodeURI(encodeURI(password)),
            dataType: "text", //返回的数据格式
            success: callback1  //定义回调函数
        })
    }
}
//3.接收返回的纯文本数据-->data
function callback1(data) {
    if (data == "") {
        /*登录成功跳转*/
        window.location.href = "index.jsp";
    }
    //4.数据显示到页面上
    $("#result").html(data).show(300).delay(3000).hide(300);
}





