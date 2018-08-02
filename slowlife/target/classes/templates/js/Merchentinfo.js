$(function(){
    alert("进入商家详情");
    var url=location.href;
    var index=url.lastIndexOf("=");
    index=parseInt(index)+1;
    var id=url.substring(index);
    alert("商家编号："+id);
    $.ajax({
        type:'POST',
        url:'',
        dataType:'json',
        contentType:'applicatio/json',
        success:function(data){
           var picture=$("#MerchentPicture");
           picture.src("图片地址");
           $("#MerchentName").text("商家名称");
           $("#CommodityQuantity").text("商品数量");
        }

    });
    $.ajax({
        type:'POST',
        url:'',
        dataType:'json',
        contentType:'application/json',
        success:function (data) {
            var str='';



            $("#Commodity").append(str);
        }
    });
})