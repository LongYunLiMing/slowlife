$(function(){
    var data={userNumber:localStorage.getItem("userNumber")};
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/allOrder',
        data:{data:JSON.stringify(data)},
        dataType:'text',
        async:true,
        success:function(data){
            var str='';
            for (var i=0;i<data.length;i++){
                str+=' <div class="Order">\n' +
                    '            <div>\n' +
                    '                <a href="Merchentinfo.html">商家名称</a>\n' +
                    '            </div>\n' +
                    '            <a href="Orderinfo.html?id=订单编号">\n' +
                    '                <div><!--商品信息-->\n' +
                    '                    <div class="Pictureshow">\n' +
                    '                        <img class="Commodityphoto" src="" alt="商品图片">\n' +
                    '                    </div>\n' +
                    '                    <div class="Commodityname">\n' +
                    '                        商品名称\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '            </a>\n' +
                    '            <div>\n' +
                    '                <div class="Quanity">\n' +
                    '                    共N件商品\n' +
                    '                </div>\n' +
                    '                <div class="Money">\n' +
                    '                    实付款￥N\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '            <hr size="3">\n' +
                    '        </div>';
            }
             $("#Order").append(str)
        }
    });
})