$(function(){
    alert("加载已完成订单");
    var str='';
    str=' <div class="Order">\n' +
        '            <div><!--商家名称-->\n' +
        '                <a href="Merchentinfo.html">商家名称</a>\n' +
        '            </div>\n' +
        '            <a href="Orderinfo.html?id=123">\n' +
        '                <div><!--商品信息-->\n' +
        '                    <div class="Pictureshow"><!--商品图片显示-->\n' +
        '                        <img class="Commodityphoto" src="" alt="商品图片">\n' +
        '                    </div>\n' +
        '                    <div class="Commodityname"><!--商品名称-->\n' +
        '                        商品名称\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </a>\n' +
        '            <div><!--商品数量及金额-->\n' +
        '                <div class="Quanity"><!--商品数量-->\n' +
        '                    共N件商品\n' +
        '                </div>\n' +
        '                <div class="Money"><!--总金额-->\n' +
        '                    实付款￥N\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <hr size="3">\n' +
        '        </div>';
        $("#Order").append(str);
   /* $.ajax({
        type:'POST',
        url:'',
        data:{"type":"completed"},
        dataType:'json',
        contentType:'application/json',
        success:function(data){
            var str='';
            for (var i=0;i<data.length;i++){
                str+=' <div class="Order">\n' +
                    '            <div><!--商家名称-->\n' +
                    '                <a href="Merchentinfo.html">商家名称</a>\n' +
                    '            </div>\n' +
                    '            <a href="Orderinfo.html?id=订单编号">\n' +
                    '                <div><!--商品信息-->\n' +
                    '                    <div class="Pictureshow"><!--商品图片显示-->\n' +
                    '                        <img class="Commodityphoto" src="" alt="商品图片">\n' +
                    '                    </div>\n' +
                    '                    <div class="Commodityname"><!--商品名称-->\n' +
                    '                        商品名称\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '            </a>\n' +
                    '            <div><!--商品数量及金额-->\n' +
                    '                <div class="Quanity"><!--商品数量-->\n' +
                    '                    共N件商品\n' +
                    '                </div>\n' +
                    '                <div class="Money"><!--总金额-->\n' +
                    '                    实付款￥N\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '            <hr size="3">\n' +
                    '        </div>';
            }
             $("#Order").append(str)
        }
    });*/
})