/**
 * Created by 小鑫哦 on 2016/12/9 0009.
 */
function verifyRegister() {

    //1.从页面获取数据
    var username = $("input#reg_username").val();
    var password = $('input#reg_password').val();
    var repassword = $('input#repassword1').val();
    var realname = $('input#realname').val();
    var userEmail = $('input#userEmail').val();
    var userPhone = $('input#userPhone').val();
    var userAddress = $('input#userAddress').val();
    var sex = $("input[name='sex']:checked").val();

    if (username == "") {
        $("#errormsg1").html("用户名为空！").show(300).delay(3000).hide(300);
    } else if (password == "") {
        $("#errormsg2").html("密码为空！").show(300).delay(3000).hide(300);
    } else if (repassword == "") {
        $("#errormsg3").html("请重新输入一边密码！").show(300).delay(3000).hide(300);
    } else if (repassword != password) {
        $("#errormsg3").html("密码不一致！").show(300).delay(3000).hide(300);
    } else if (realname == "") {
        $("#errormsg4").html("真实姓名为空！").show(300).delay(3000).hide(300);
    } else if (userEmail == "") {
        $("#errormsg5").html("Email为空！").show(300).delay(3000).hide(300);
    } else if (userPhone == "") {
        $("#errormsg6").html("电话为空！").show(300).delay(3000).hide(300);
    } else if (userAddress == "") {
        $("#errormsg7").html("地址为空！").show(300).delay(3000).hide(300);
    } else {
        //2.将数据传给Servlet
        $.ajax({
            type: "POST",
            url: "ajaxServlet",
            data: "action=register&username=" + encodeURI(encodeURI(username)) + "&password=" + encodeURI(encodeURI(password))
            + "&realname=" + encodeURI(encodeURI(realname))
            + "&userEmail=" + encodeURI(encodeURI(userEmail))
            + "&userPhone=" + encodeURI(encodeURI(userPhone))
            + "&userAddress=" + encodeURI(encodeURI(userAddress))
            + "&sex=" + encodeURI(encodeURI(sex)),
            dataType: "text", //返回的数据格式
            success: callback  //定义回调函数
        })
    }
}
//3.接收返回的纯文本数据-->data
function callback(data) {
    var data1 =data;
    //4.数据显示到页面上
    alert(data1);
    if (data1 == "注册成功，返回首页!") {
        window.location.href = "index.jsp";
    }

}







