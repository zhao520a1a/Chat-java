

//显示浮动窗口的方法
function showwin() {
    //1.找到窗口对应的div节点,id选择器
    var winNode = $("#window");
    //2. 利用JQuery的fadeIn方法,让div对应的窗口显示出来
    winNode.fadeIn("slow");
}

//隐藏窗口的方法
function hide() {
    //1.找到窗口对应的节点
    var winNode = $("#window");
    //2.利用fadeOut方法,将窗口隐藏起来
    winNode.fadeOut("slow");
}

