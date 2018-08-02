$(document).ready(function(){
  //  alert("进入购物车");
  //  alert("欢迎用户"+localStorage.getItem("userId")+"访问主页");
    var data={userNumber:localStorage.getItem("userNumber")};
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/user-cart-info',
        data:{data:JSON.stringify(data)},
        dataType:'text',
        async:true,
        success:function(val){
           // alert(val);
            var data=JSON.parse(val);
            var msg=data.msg;
            var result=data.result;//json数组
            if(msg=='success'){
                var str="";
                for(var i=0;i<result.length;i++){
                    var commodity=result[i].commodity;
                    var id=result[i].id;
                    str+='<div>\n' +
                        '                <span style="display: inline-block;">\n' +
                        '                        <input type="checkbox" id="'+id+'checkbox" number="'+id+'" onclick="checkboxPoint('+id+')">\n' +
                        '                </span>\n' +
                        '                <span class="CommodityPicture"><!-- 商品展示图片 -->\n' +
                        '                        <a href="Commodityinfo.html?number='+commodity.commodityNumber+'"><img alt="商品图片1" src=""></a>\n' +
                        '                </span>\n' +
                        '                <div class="CommodityInfo">\n' +
                        '                    <span><a href="Commodityinfo.html?number='+commodity.commodityNumber+'">商品名称1</a></span><br>\n' +
                        '                    <span id="'+id+'price">'+commodity.commodityPrice+'</span>\n' +
                        '                    <span>\n' +
                        '                            <a id="'+id+'jian" onclick="Jian('+id+');">-</a>\n' +
                        '                            <input type="text" class="input_quantity" id="'+id+'" value="'+result[i].count+'">\n' +
                        '                            <a onclick="Jia('+id+');">+</a>\n' +
                        '                        </span>\n' +
                        '                </div>\n' +
                        '                <hr size="3">\n' +
                        '            </div>';
                }
                $("#show").prepend(str);
               // alert(str);
            }else {
                alert("错误");
                var url=location.href;
                var index=url.lastIndexOf("/");
                url=url.substring(0,index+1);
                url=url+"Login.html";
                alert("url:"+url);
                location.href=url;

            }

        }
    });
});
//当购买的某商品数量减少时所进行的金额逻辑运算
function Jian(id){
    var quantity=$("input[id="+id+"]").val();
    quantity=parseInt(quantity);//获取该商品的数量

    if(quantity>1){
        quantity-=1;
        var data={id:id,count:quantity};
        $.ajax({
            type:'get',
            url:'http://127.0.0.1:9000/v1/commodity_add',
            data:{SCnumber:JSON.stringify(data)},
            async:true,
            dataType:'text',
            success:function (data) {
                alert(data);
                var result=JSON.parse(data);
                if(result.msg=="success"){
                    alert("商品数减一");
                }else{
                    alert("操作失败");
                }
            }
        });
        $("input[id="+id+"]").val(quantity);//让商品数量减一后更新
        if($("#"+id+"checkbox").prop("checked")){//如果正在操作的该商品被用户选中的话则进行金额的逻辑运算更新最终金额
            var price=$("#"+id+"price").text();
            price=parseInt(price);
            var amount=$("#amount").text();
            amount=parseInt(amount);
            amount-=price;
            $("#amount").text(amount);
        }
    }
}
//当购买的某商品数量增加时所进行的金额逻辑运算
function Jia(id){
    var quantity=$("input[id="+id+"]").val();
    quantity=parseInt(quantity);
    quantity+=1;
    $("input[id="+id+"]").val(quantity);//让商品数量加一后，更新结果
    var data={id:id,count:quantity};
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/commodity_add',
        data:{SCnumber:JSON.stringify(data)},
        async:true,
        dataType:'text',
        success:function (data) {
            alert(data);
            var result=JSON.parse(data);
            if(result.msg=="success"){
                alert("商品数加一");
            }else{
                alert("操作失败");
            }
        }
    });

    if($("#"+id+"checkbox").prop("checked")){//如果该商品给选中，则更新总金额
        var price=$("#"+id+"price").text();
        price=parseInt(price);
        var amount=$("#amount").text();
        amount=parseInt(amount);
        amount+=price;
        $("#amount").text(amount);
    }
}
//当选中或取消商品时，所进行的金额逻辑运算
function checkboxPoint(id){
    if($("#"+id+"checkbox").prop("checked")){//当选中商品时，更新金额
        var price=$("#"+id+"price").text();
        price=parseInt(price);
        var amount=$("#amount").text();
        amount=parseInt(amount);
        var quantity=$("#"+id).val();
        quantity=parseInt(quantity);
        amount+=quantity*price;
        $("#amount").text(amount);
        var q=$("#quantity").text();
        q=parseInt(q);
        q+=1;
        $("#quantity").text(q);
    }else if(!$("#"+id+"checkbox").prop("checked")){//若取消选中商品时，更新金额
        var price=$("#"+id+"price").text();
        price=parseInt(price);
        var amount=$("#amount").text();
        amount=parseInt(amount);
        var quantity=$("#"+id).val();
        quantity=parseInt(quantity);
        amount-=quantity*price;
        $("#amount").text(amount);
        var q=$("#quantity").text();
        q=parseInt(q);
        q-=1;
        $("#quantity").text(q);
    }
}
function jiesuan(){
    var checkbox=$("input:checked");
    var jsonArray='[';
    for(var i=0;i<checkbox.length;i++){
        var number=$("input:checked:eq("+i+")").attr("number");
        var quantity=$("#"+number).val();
        if(i==checkbox.length-1){
            var json='{"commodityNumber":"'+number+'","count":"'+quantity+'"}';
        }else{
            var json='{"commodityNumber":"'+number+'","count":"'+quantity+'"},';
        }
        jsonArray+=json;
    }
    jsonArray+=']';
    alert(jsonArray);
    var url=location.href;
    var index=url.lastIndexOf("/");
    url=url.substring(0,index+1);
    url=url+"SetOrder.html?id="+jsonArray;
    alert("url:"+url);
    location.href=url;
}
