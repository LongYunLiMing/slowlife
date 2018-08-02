$(function () {
    var url=location.href;
    var index=url.indexOf("=");
    index=parseInt(index)+1;
    var number=url.substring(index);
    alert("订单编号："+number);
    $.ajax({
        type:'POST',
        url:'',
        dataType:'json',
        data:{"number":number},
        contentType:'application/json',
        success:function (data) {

        }
    });
})