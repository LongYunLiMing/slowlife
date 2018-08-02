var sendjson;
$(function () {
    var url=location.href;
    var index=url.lastIndexOf("=")+1;
    var json=url.substring(index);
    json=json.replace(/%22/g,"\"");
    json=json.replace('\\','');
    jsonArray=eval('('+json+')');

    var data={list:jsonArray};
    console.log(data);
    $.ajax({
        type: 'get',
        url: 'http://127.0.0.1:9000/v1/order-get-commodity',
        data:{orderData:JSON.stringify(data)},
        async: true,
        dataType: 'text',
        success: function (data) {
            //alert('初始化'+data);
            data=JSON.parse(data);
            sendjson=data;
            var str='';
            if(data.msg='success'){
                var resultArray=data.result;
                //alert("商家数量"+resultArray.length);
                for(var i=0;i<resultArray.length;i++){
                    var commodityArray=resultArray[i].commodityJSONArry;
                    //alert("商品数量"+commodityArray.length);
                    str+='<div>\n' +
                        '        <div>\n' +
                        '            <span>\n' +
                        '                <a href="Merchentinfo.html?id='+resultArray[i].merchentNumber+'"><img src="'+resultArray[i].merchentPicture+'" alt="商家头像"></a> \n' +
                        '            </span>\n' +
                        '            <span>\n' +
                        '                <a href="Merchentinfo.html?id='+resultArray[i].merchentNumber+'">'+resultArray[i].merchentName+'</a>\n' +
                        '            </span>\n' +
                        '        </div>';
                    for(var l=0;l<commodityArray.length;l++){
                        str+='<div>\n' +
                            '            <div class="picture">\n' +
                            '                <a href="Commodityinfo.html?id='+commodityArray[l].commodityNumber+'"><img src="'+commodityArray[l].commodityPicture+'" alt="商品图片"></a>\n' +
                            '            </div>\n' +
                            '            <div class="info">\n' +
                            '                <span><a href="Commodityinfo.html?id='+commodityArray[l].commodityNumber+'">'+commodityArray[l].commodityName+'</a> </span>\n' +
                            '                <span>￥'+commodityArray[l].commodityPrice+'</span>\n' +
                            '                <span>'+commodityArray[l].count+'</span>\n' +
                            '            </div>\n' +
                            '        </div>';
                    }
                    str+='<span>'+resultArray[i].money+'</span>\n' +
                        '    </div>';
                }
                str+='<div>总金额：'+data.money+'</div>';
               // alert(str);
                $("#show").prepend(str);
            }
        },
        error: function (response) {
            alert("error");
        }
    });
})
function sendOrder() {
    alert("提交订单");
    //获取订单地址
   var redion=document.getElementsByName("address");
    var address='';
    for(var i=0;i<redion.length;i++){
        if(redion[i].checked){
            address=redion[i].value;
        }
    }
    alert(address+":"+localStorage.getItem("userNumber")+":"+sendjson.result.toString());
    //var data={userNumber:localStorage.getItem("userNumber"),address:address,list:json.result,money:json.money};
    var data={userNumber:localStorage.getItem("userNumber"),address:address,list:sendjson.result};
    alert(data.userNumber);
    $.ajax({
        type: 'get',
        url: 'http://127.0.0.1:9000/v1/create-order',
        data:{orderData:JSON.stringify(data)},
        async: true,
        dataType: 'text',
        success: function (data) {
            alert("返回数据123"+data)
        },
        error: function (response) {
            alert("error");
        }
    });

}
function addressShow() {
    var userNumber=localStorage.getItem("userNumber");
    var data={userNumber:userNumber};
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/getAddress',
        data:{addressData:JSON.stringify(data)},
        dataType:'text',
        async:true,
        success:function (data) {
            alert(data);
            var data=JSON.parse(data);
            var result=data.result;
            var str='';
            for(var i=0;i<result.length;i++){
                str+='<div>\n' +
                    '                <div class="info">\n' +
                    '                    <input type="radio" name="address" value="'+result[i].addressNumber+'">\n' +
                    '                </div>\n' +
                    '                <div class="inlne">\n' +
                    '                    <div>address:'+result[i].address+'</div>\n' +
                    '                    <div>phone:'+result[i].phone+'</div>\n' +
                    '                </div>\n' +
                    '            </div>';
            }
            $("#address").prepend(str);
        }
    });
    $("#address").css("display","block");
}
function addressYes() {
    $("#address").css("display","none");
    var redion=document.getElementsByName("address");
    for(var i=0;i<address.length;i++){
        if(redion[i].checked){
            address=redion[i].value;
        }
    }
}