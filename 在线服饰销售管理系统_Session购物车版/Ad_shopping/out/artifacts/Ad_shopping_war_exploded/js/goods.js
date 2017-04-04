/**
 * Created by 小鑫哦 on 2016/12/14 0014.
 */

function showAllGoods() {
    //将数据传给Servlet
    $.ajax({
        type: "POST",
        async:false,
        url: "GoodsServlet",
        data: "action=showAllGoods",
        dataType: "text", //返回的数据格式
        success: show_all_goods  //定义回调函数
    })
}

function show_all_goods(data) {
    // if (data == "获取商品信息成功") {
    // } else {
    //     alert(data);
    // }
}


function addGoods() {
    var  goodsName = $("input#goodsName").val();
    var  goodsPrice = $("input#goodsPrice").val();
    var  goodsImg = $("input#goodsImg").val();
    var  goodsClassId = $("#goodsClassId option:selected").val();
    var  provider = $("input#provider").val();
    var  goodsNo = $("input#goodsNo").val();
    var  content = $("#goodsContent").val();
    var  amount = $("input#amount").val();
    var  regtime = $("#regtime").val();

    if(goodsImg =="") {
        goodsImg="undefine"
    }
    //将数据传给Servlet
    $.ajax({
        type: "POST",
        url: "GoodsServlet",
        async:false,
        cache:false,
        data: "action=addGoods&goodsName="+ encodeURI(encodeURI(goodsName)) +
        "&goodsPrice=" + encodeURI(encodeURI(goodsPrice)) +
        "&goodsImg=" + encodeURI(encodeURI(goodsImg)) +
        "&goodsClassId=" + encodeURI(encodeURI(goodsClassId)) +
        "&provider=" + encodeURI(encodeURI(provider)) +
        "&goodsNo=" + encodeURI(encodeURI(goodsNo)) +
        "&content=" + encodeURI(encodeURI(content)) +
        "&amount=" + encodeURI(encodeURI(amount)) +
        "&leave_amount=" + encodeURI(encodeURI(amount)) +
        "&regtime=" + encodeURI(encodeURI(regtime))  ,
        dataType: "text", //返回的数据格式
        success: add_goods  //定义回调函数
    })
}

function add_goods(data) {
    alert(data);
    if (data == "增加商品成功") {
        showAllGoods();
    }
    return true;
}


function showOneGoods(goodsId) {
    //将数据传给Servlet
    $.ajax({
        type: "POST",
        url: "GoodsServlet",
        async:false,
        cache:false,
        data: "action=showOneGoods&goodsId="+ encodeURI(encodeURI(goodsId)),
        dataType: "text", //返回的数据格式
        success: show_one_goods  //定义回调函数
    })
}

function show_one_goods(data) {
    if (data == "获取单个商品信息成功") {
        return true;
    } else {
        alert(data);
        return false;
    }
}


function  updateGoods() {
    var  goodsId = $("#goodsId").val();
    var  goodsName = $("input#goodsName").val();
    var  goodsPrice = $("input#goodsPrice").val();
    var  goodsClassId = $("#goodsClassId option:selected").val();
    var  provider = $("input#provider").val();
    var  goodsNo = $("input#goodsNo").val();
    var  content = $("#goodsContent").val();
    var  amount = $("input#amount").val();

    $.ajax({
        type: "POST",
        url: "GoodsServlet",
        async:false,
        cache:false,
        data: "action=updateGoods&goodsId=" + encodeURI(encodeURI(goodsId)) +
        "&goodsName="+ encodeURI(encodeURI(goodsName)) +
        "&goodsPrice=" + encodeURI(encodeURI(goodsPrice)) +
        "&goodsClassId=" + encodeURI(encodeURI(goodsClassId)) +
        "&provider=" + encodeURI(encodeURI(provider)) +
        "&goodsNo=" + encodeURI(encodeURI(goodsNo)) +
        "&content=" + encodeURI(encodeURI(content)) +
        "&amount=" + encodeURI(encodeURI(amount)) +
        "&leave_amount=" + encodeURI(encodeURI(amount)),
        dataType: "text", //返回的数据格式
        success: update_goods  //定义回调函数
    })
}

function update_goods(data) {
    alert(data);
    if (data == "修改商品成功") {
        showAllGoods();
        return true;
    } else {
        return false;
    }
}


//单个删除
function delGoods(goodsId) {
    if (confirm("您确定要删除吗?")) {
        //将数据传给Servlet
        $.ajax({
            type: "POST",
            async:false,  /*要用同步，否则数据会有延时出现发生*/
            url: "GoodsServlet",
            data: "action=delGoods&goodsId="+ encodeURI(encodeURI(goodsId)),
            dataType: "text", //返回的数据格式
            success: del_goods  //定义回调函数
        })

    }
}

function del_goods(data) {
    alert(data);
    if (data == "删除商品成功") {
        showAllGoods();
         // window.location.reload(true);
    }
}


