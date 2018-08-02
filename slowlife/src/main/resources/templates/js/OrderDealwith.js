function OrderDW() {
    //订单处理获取
    alert("订单处理获取");
    $.ajax({
        type:'post',
        url:'',
        dataType:'json',
        data:{"state":"待处理"},
        contentType:'application/json',
        success:function (data) {

        }
    });
}
function ReturnGoods() {
    //退货处理获取
    alert("退货处理获取");
    $.ajax({
        type:'post',
        url:'',
        dataType:'json',
        data:{"state":"待退款"},
        contentType:'application/json',
        success:function (data) {

        }
    });
}
function Get() {
    //订单检索
    var number=$("#OrderNumber").val();
    alert("订单检索："+number);
    $.ajax({
        type:'post',
        url:'',
        dataType:'json',
        data:{"number":number},
        contentType:'application/json',
        success:function (data) {

        }
    });
}
function OrderChange(number) {
    //订单处理
    alert("订单处理通过");
    $.ajax({
        type:'post',
        url:'',
        dataType:'json',
        data:{"number":number},
        contentType:'application/json',
        success:function (data) {


           // $("#dealwith"+number).remove();
        }
    });
}
function ReturnGoodChang() {
    //退货申请
    alert("退货申请通过");
    $.ajax({
        type:'post',
        url:'',
        dataType:'json',
        data:{"number":number},
        contentType:'application/json',
        success:function (data) {


            $("#return"+number).remove();
        }
    });
}