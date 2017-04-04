/**
 * Created by 小鑫哦 on 2016/11/12 0012.
 */


$(document).ready(function () {
    /*
     * 计算购物车中每一个产品行的金额小计
     * 注：参数 row 购物车表格中的行元素tr
     */
    function getSubTotal(row) {
        var price = parseFloat($(row).find(".selling-price").data("bind"));
        var qty = parseInt($(row).find(".saleAmount").val());
        var result = price * qty;
        $(row).find(".selling-price").text($.formatMoney(price, 2));
        $(row).find(".subtotal").text($.formatMoney(result, 2)).data("bind", result.toFixed(2));
    };

    /*
     * 计算购物车中产品的合计，总数量，种类数
     */
    function getTotal() {
        var qtyTotal = 0;  /*所选产品采购的总数量*/
        var itemCount = 0;  /*所选产品种类个数*/
        var priceTotal = 0;  /*合计总价*/
        $(cartTable).find("tr:gt(0)").each(function () {
            getSubTotal(this);
            if ($(this).find(":checkbox").prop("checked") == true) {
                itemCount++;
                qtyTotal += parseInt($(this).find(".saleAmount").val());
                priceTotal += parseFloat($(this).find(".subtotal").data("bind"));
            }
        });
        $("#itemCount").text(itemCount).data("bind", itemCount);
        $("#qtyCount").text(qtyTotal).data("bind", qtyTotal);
        $("#priceTotal").text($.formatMoney(priceTotal, 2)).data("bind", priceTotal.toFixed(2));
        $(".price-total").prop("value",$.formatMoney(priceTotal, 2)).data("bind", priceTotal.toFixed(2));
    };

    var cartTable = $("#cartTable");
    getTotal();

    //为每一个勾选框指定单击事件
    $(cartTable).find(":checkbox").click(function () {
        //全选框单击
        if ($(this).hasClass("check-all")) {
            var checked = $(this).prop("checked");
            $(cartTable).find(".check-one").prop("checked", checked);

            /*将列表中所有的check的id置为对应商品的id*/
            $(cartTable).find(".check-one").each( function () {
                /*勾选则将复选框值赋为商品的id号，为了之后后端能通过复选框值来判断获取选择行的商品信息*/
                    var goodId = $(this).parent().find(".goodId").val();
                    $(this).prop("value", goodId);
                // alert($(this).val());
            } );


        }

        //如果手工一个一个的点选了所有勾选框，需要自动将“全选”勾上或是取消
        var items = cartTable.find("tr:gt(0)");
        $(cartTable).find(".check-all").prop("checked", items.find(":checkbox:checked").length == items.length);

        //设置结算按钮disabled属性
        $("#btn_settlement").attr("disabled", items.find(":checkbox:checked").length == 0);
        getTotal();

        var checked = $(this).prop("checked");
        /*勾选则将复选框值赋为商品的id号，为了之后后端能通过复选框值来判断获取选择行的商品信息*/
        if(checked == true) {
            var goodId = $(this).parent().find(".goodId").val();
            $(this).prop("value", goodId);
            // alert($(this).val()); 打印当前复选框的值
        } else {
            $(this).prop("value", 0);
        }
    });




});