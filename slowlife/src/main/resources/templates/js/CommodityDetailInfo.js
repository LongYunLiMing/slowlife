$(function () {
   // alert("商品详细信息");
    var href=location.href;
    var index=href.lastIndexOf("=")+1;
    var number=href.substring(index,index+1);
    var data={commodityNumber:number};
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/getDetailInfo',
        data:{infoData:JSON.stringify(data)},
        dataType:'text',
        async:true,
        success:function (data) {
            data=JSON.parse(data);
            var commodity=data.result;
            $("#show").append(commodity.commodityDescription);
        }
    });
})