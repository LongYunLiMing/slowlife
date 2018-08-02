$(function () {

    var data={userNumber:localStorage.getItem("userNumber")};
    //alert("初始化"+data.userNumber+":"+localStorage.getItem("userNumber"));
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/getAddressP',
        data:{getData:JSON.stringify(data)},
        dataType:'text',
        async:true,
        success:function (data) {
            alert(data);
            data=JSON.parse(data);
            if(data.msg=='success'){
                var address=data.result;
                var str='';
                for(var i=0;i<address.length;i++){
                    str+='<div id="delete'+address[i].addressNumber+'">\n' +
                        '            <div id="'+address[i].addressNumber+'show">\n' +
                        '                <div id="addressShow'+address[i].addressNumber+'">地址：'+address[i].address+'</div>\n' +
                        '                <div id="phoneShow'+address[i].addressNumber+'">电话：'+address[i].phone+'</div>\n' +
                        '                <div class="two"><button onclick="updataShow('+address[i].addressNumber+')">修改</button></div>\n' +
                        '                <div class="two"><button onclick="deleteAddress('+address[i].addressNumber+')">删除</button></div>\n' +
                        '            </div>\n' +
                        '            <div id="'+address[i].addressNumber+'updata" style="display: none;">\n' +
                        '                <!-- 修改区 -->\n' +
                        '                <input id="addressinput'+address[i].addressNumber+'" type="text" placeholder="请输入修改地址"><br>\n' +
                        '                <input id="phoneinput'+address[i].addressNumber+'" type="text" placeholder="请输入修改电话"><br>\n' +
                        '                <button onclick="updata('+address[i].addressNumber+')">修改</button>\n' +
                        '            </div>\n' +
                        '            <hr size="3">\n' +
                        '        </div>';
                }
                $("#show").prepend(str);
            }
        }
    });
})
function updataShow(id){
    $("#"+id+"updata").css("display","block");}

function deleteAddress(id){
    var data={addressNumber:id};
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/deleteAddress',
        dataType:'text',
        data:{deleteData:JSON.stringify(data)},
        async:true,
        success:function(data){
            data=JSON.parse(data);
            if(data.msg='success'){
                $("#"+id+"Show").remove();
                alert("删除成功");
            }else {
                alert("删除失败");
            }
        }
    });
    $("#delete"+id).remove();
}

function updata(id){
    var address=$("#addressinput"+id).text();
    var phone=$("#phoneinput"+id).text();
    var data={addressNumber:id,address:address,phone:phone};
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/updataAddress',
        dataType:'text',
        data:{updataData:JSON.stringify(data)},
        async:true,
        success:function(data){
            data=JSON.parse(data);
            if(data.msg=='success'){
                $("#addressShow"+id).text("地址："+address);
                $("#phoneShow"+id).text("电话："+phone);
                $("#"+id+"updata").css("display","none");
            }else {
                alert("地址更新失败");
            }
        }
    });
}

function addAddress(){

    var address=$("#address").val();
    var phone=$("#phone").val();
    var data={address:address,phone:phone,userNumber:localStorage.getItem("userNumber")};
    alert('添加地址123');
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/addAddress',
        data:{addData:JSON.stringify(data)},
        dataType:'text',
        async:true,
        success:function (data) {
            data=JSON.parse(data);
            var addressNumber=data.addressNumber;
            if(data.msg=='success'){
                var str='<div id="delete'+addressNumber+'">\n' +
                    '            <div id="'+addressNumber+'show">\n' +
                    '                <div id="addressShow'+addressNumber+'">地址：'+address+'</div>\n' +
                    '                <div id="phoneShow'+addressNumber+'">电话：'+phone   +'</div>\n' +
                    '                <div class="two"><button onclick="updataShow('+addressNumber+')">修改</button></div>\n' +
                    '                <div class="two"><button onclick="deleteAddress('+addressNumber+')">删除</button></div>\n' +
                    '            </div>\n' +
                    '            <div id="'+addressNumber+'updata" style="display: none;">\n' +
                    '                <!-- 修改区 -->\n' +
                    '                <input id="addressinput'+addressNumber+'" type="text" placeholder="请输入修改地址"><br>\n' +
                    '                <input id="phoneinput'+addressNumber+'" type="text" placeholder="请输入修改电话"><br>\n' +
                    '                <button onclick="updata('+addressNumber+')">修改</button>\n' +
                    '            </div>\n' +
                    '            <hr size="3">\n' +
                    '        </div>';
                alert(str);
                $("#show").prepend(str);
                $("#address").val("");
                $("#phone").val("");
            }
        }
    });

}
