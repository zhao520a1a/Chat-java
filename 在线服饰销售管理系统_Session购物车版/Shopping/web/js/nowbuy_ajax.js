/**
 * Created by 小鑫哦 on 2016/12/10 0010.
 */

function goodsToNowBuy() {
    //1.从页面获取数据
     var goodsId  = $("#goodsId").val();
     var saleAmount  = $("#saleAmount").val();

    //2.将数据传给Servlet
    $.ajax({
        type: "POST",
        url: "GoodsServlet",
        data: "action=toNowBuy&goodsId=" + encodeURI(encodeURI(goodsId)) + "&saleAmount=" + encodeURI(encodeURI(saleAmount)) ,
        dataType: "text", //返回的数据格式
        success: callback2  //定义回调函数
    })

}
//3.接收返回的纯文本数据-->data
function callback2(data) {
    if (data == "结算成功") {
        var priceTotal = $("#saleAmount").val() * $("#goodsPrice").val();
        window.location.href = "nowbuy.jsp?nowBuyGoodsId=" + $("#goodsId").val() +"&priceTotal=" + priceTotal ;
    } else {
        alert(data)
    }
}



function cartToNowBuy() {
    //1.从页面获取数据
     //jquery获取复选框值
        var goodsIds =new Array();
        $('input[name="checkone"]:checked').each(function(){
            goodsIds.push($(this).val());
        });

        //2.将数据传给Servlet
        $.ajax({
            type: "POST",
            url: "GoodsServlet",
            data: "action=toNowBuy&goodsIds=" + encodeURI(encodeURI(goodsIds.toString())) ,
            dataType: "text", //返回的数据格式
            success: callback1  //定义回调函数
        })

}
//3.接收返回的纯文本数据-->data
function callback1(data) {
    if (data == "结算成功") {
        window.location.href = "nowbuy.jsp?priceTotal=" + $("#totalPrice").val();
    } else {
        alert(data)
    }
}