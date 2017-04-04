/**
 * Created by 小鑫哦 on 2016/12/14 0014.
 */
function showAllUsers() {
    //将数据传给Servlet
    $.ajax({
        type: "POST",
        async:false,
        url: "UserServlet",
        data: "action=showAllUsers",
        dataType: "text", //返回的数据格式
        success: show_all_users  //定义回调函数
    })
}

function show_all_users(data) {
    // alert(data);
}

function showOneUser(userId) {
    $.ajax({
        type: "POST",
        url: "UserServlet",
        async:false,
        cache:false,
        data: "action=showOneUser&userId="+ encodeURI(encodeURI(userId)),
        dataType: "text", //返回的数据格式
        success: show_one_user  //定义回调函数
    })
}

function show_one_user(data) {
    if (data == "获取单个商品信息成功") {
        return true;
    } else {
        return false;
    }

}


function  updateUser() {
    var userId = $("input#userid").val();
    var userName = $("input#username").val();
    var password = $('input#password').val();
    var realName = $('input#realname').val();
    var userEmail = $('input#userEmail').val();
    var userPhone = $('input#userPhone').val();
    var userAddress = $('input#userAddress').val();
    var sex = $("input[name='sex']:checked").val();
    // var regTime = $("input#regTime").val();

    //将数据传给Servlet
    $.ajax({
        type: "POST",
        url: "UserServlet",
        async:false,
        data: "action=updateUser&userName=" + encodeURI(encodeURI(userName)) +
        "&password="+ encodeURI(encodeURI(password)) +
        "&realName=" + encodeURI(encodeURI(realName)) +
        "&userEmail=" + encodeURI(encodeURI(userEmail)) +
        "&userPhone=" + encodeURI(encodeURI(userPhone)) +
        "&userAddress=" + encodeURI(encodeURI(userAddress)) +
        "&sex=" + encodeURI(encodeURI(sex)) +
        "&userId=" + encodeURI(encodeURI(userId)),
        dataType: "text", //返回的数据格式
        success: update_user  //定义回调函数
    })
}

function update_user(data) {
    alert(data);
    if (data == "修改用户成功") {
        showAllUsers();
        return true;
    } else {
        return false;
    }

}


//单个删除
function delUser(userId) {
    if (confirm("您确定要删除吗?")) {
        //将数据传给Servlet
        $.ajax({
            type: "POST",
            async:false,
            url: "UserServlet",
            data: "action=delUser&userId="+ encodeURI(encodeURI(userId)),
            dataType: "text", //返回的数据格式
            success: del_user  //定义回调函数
        })

    }
}

function del_user(data) {
    alert(data);
    if (data == "删除用户成功") {
        showAllUsers();
    }
}