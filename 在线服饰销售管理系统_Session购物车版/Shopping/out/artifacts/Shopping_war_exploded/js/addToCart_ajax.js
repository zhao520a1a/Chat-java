/**
 * Created by 小鑫哦 on 2016/12/8 0008.
 */
function addToCart() {
    //1.从页面获取数据
    var goodsId = $('#goodsId').val();
    var saleAmount = $('#saleAmount').val();
    //2.将数据传给Servlet
    $.ajax({
        type:"POST",
        url:"GoodsServlet",
        data:"action=addToCart&goodsId=" + encodeURI(encodeURI(goodsId)) + "&saleAmount=" + encodeURI(encodeURI(saleAmount)) ,
        dataType:"text", //返回的数据格式
        success:callback  //定义回调函数
    })
}
//3.接收返回的纯文本数据-->data
function callback(data) {
    //4.数据显示到页面上
    alert(data);
}