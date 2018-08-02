var url=location.href;
var index=url.indexOf("=");
index=parseInt(index);
var number=url.substring(index+1);
var commodityNumber='';
$(function () {
    $("#detailInfo").attr("href","CommodityDetailInfo.html?number="+number);
    $("#comment").attr("href","CommodityComment.html?number="+number);
    $("#iframe").attr("src","CommodityDetailInfo.html?number="+number);
    var data={commodityNumber:number};
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/get-commodity',
        data:{commodityData:JSON.stringify(data)},
        dataType:'text',
        async:true,
        success:function (data) {
            alert(data);
            data=JSON.parse(data);
            var commodity=data.commodity;
            $("#name").text(commodity.commodityName);
            $("#price").text('￥'+commodity.commodityPrice);
            $("#count").text('还剩'+commodity.commodityInventory+'件');
            $("#time").text('上架时间：'+commodity.shelfTime);
            commodityNumber=commodity.commodityNumber;
        },
        error:function () {
            alert("错误");
        }
    })
})
function jian() {
    var quantity=$("#quantity").val();
    quantity=parseInt(quantity);
    if(quantity>1){
        quantity-=1;
        $("#quantity").val(quantity);
    }
}
function jia() {
    var quantity=$("#quantity").val();
    quantity=parseInt(quantity);
    quantity+=1;
    $("#quantity").val(quantity);
}
function AddShoppingCart() {
    var quantity=$("#quantity").val();
    var user_number=localStorage.getItem("userNumber");
    if(user_number==null||user_number==''){
        //未登录
        alert("请先登录");
        var index=url.lastIndexOf("/");
        var herf=url.substring(0,index+1);
        herf+='Login.html';
        location.href=herf;
    }
    var data={user_number:user_number,commodity_number:commodityNumber,count:quantity};
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/addShoppingCart',
        dataType:'text',
        data:{addShoppingCartdata:JSON.stringify(data)},
        async:true,
        success:function (data) {
            alert(data);

        }
    });
}
function FirstGet() {
    var quantity=$("#quantity").val();
    var index=url.lastIndexOf("/");
    var herf=url.substring(0,index+1);
    var json='[{"number":'+number+',"quantity":'+quantity+'}]';
    json=json.toString();
    alert(json);
    herf+='SetOrder.html?json='+json;
    alert(herf);
    location.href=herf;
}