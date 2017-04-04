/**
 * Created by 小鑫哦 on 2016/12/14 0014.
 */
function showAllIndents() {
    //将数据传给Servlet
    $.ajax({
        type: "POST",
        async:false,
        url: "IndentServlet",
        data: "action=showAllIndents",
        dataType: "text", //返回的数据格式
        success: show_all_indents  //定义回调函数
    })
}

function show_all_indents(data) {
    // alert(data);
}



function showOneIndent(indentId) {

    $.ajax({
        type: "POST",
        url: "IndentServlet",
        async:false,
        data: "action=showOneIndent&indentId="+ encodeURI(encodeURI(indentId)),
        dataType: "text", //返回的数据格式
        success: show_one_indent  //定义回调函数
    })
}

function show_one_indent(data) {

    if (data == "获取单个订单信息成功") {
        return true;
    } else {
        alert(data);
        return false;
    }

}


//单个删除
function delIndent(indentId) {
    if (confirm("您确定要删除吗?")) {
        //将数据传给Servlet
        $.ajax({
            type: "POST",
            async:false,
            url: "IndentServlet",
            data: "action=delIndent&indentId="+ encodeURI(encodeURI(indentId)),
            dataType: "text", //返回的数据格式
            success: del_indent  //定义回调函数
        })
    }
}

function del_indent(data) {
    alert(data);
    if (data == "删除订单成功") {
        showAllIndents();
    } else {
        alert(data);
    }
}