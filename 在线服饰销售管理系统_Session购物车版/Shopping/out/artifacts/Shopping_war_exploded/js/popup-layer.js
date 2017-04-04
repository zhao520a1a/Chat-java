/**
 * Created by 小鑫哦 on 2016/12/8 0008.
 */

//显示灰色 jQuery 遮罩层
    function showBg() {
        var bh = $("body").height();
        var bw = $("body").width();
        $("#fullbg").css({
            height:bh,
            width:bw,
            display:"block"

        });
        $("#login-page").show();
    }
//关闭灰色 jQuery 遮罩
function closeBg() {
    $("#fullbg,#login-page").hide();
}

