$(document).ready(function (){
   // alert("商品显示页面进入初始化");
    var url=location.href;
    var index=url.lastIndexOf("=")+1;
    var data=url.substring(index);
   // alert("输入框内容"+data)
    var type=url.substring(index-2,index-1);
    data={"key":data};
    if(type=='K'){
        //通过关键字查询
        //alert("关键字："+data.key);
        $.ajax({
            type:'get',
            url:'http://127.0.0.1:9000/v1/slowlife/search-by-keyword',
            data:{keyword:JSON.stringify(data)},
            async:true,
            dataType:'json',
            success:function (data) {
                data=eval("("+data+")");
                var result=data.result;
                var commodity;
                var str='';
                for(var i=0;i<result.length;i++){
                    commodity=result[i];
                    str+='<div>\n' +
                        '            <div class="line">\n' +
                        '                <a href="Commodityinfo.html?id='+commodity.commodityNumber+'"><img src="" alt="商品图片"></a>\n' +
                        '            </div>\n' +
                        '            <div class="line">\n' +
                        '                <div><a href="Commodityinfo.html?id='+commodity.commodityNumber+'">'+commodity.commodityName+'</a></div>\n' +
                        '                <div>￥'+commodity.commodityPrice+'</div>\n' +
                        '                <div>'+commodity.shelfTime+'</div>\n' +
                        '                <div>'+commodity.commodityInvertory+'件</div>'+
                        '            </div>\n' +
                        '            <hr size="2">\n' +
                        '        </div>';
                }
                $("#show").append(str);
                alert(str);
            }
        });
    }else if(type=='T'){
        //通过类别查询
       // alert("类别："+data.key);
        $.ajax({
            type:'get',
            url:'http://127.0.0.1:9000/v1/slowlife/type-commodity',
            data:{type:JSON.stringify(data)},
            async:true,
            dataType:'json',
            success:function (data) {
                data=eval("("+data+")");
                var result=data.result;
                var commodity;
                var str='';
                for(var i=0;i<result.length;i++){
                    commodity=result[i];
                    str+='<div>\n' +
                        '            <!--单个商品显示区-->\n' +
                        '            <div class="line">\n' +
                        '                <!--商品图片显示-->\n' +
                        '                <a href="Commodityinfo.html?id='+commodity.commodityNumber+'"><img src="" alt="商品图片"></a>\n' +
                        '            </div>\n' +
                        '            <div class="line">\n' +
                        '                <!--商品基本信息-->\n' +
                        '                <div><a href="Commodityinfo.html?id='+commodity.commodityNumber+'">'+commodity.commodityName+'</a></div>\n' +
                        '                <div>￥'+commodity.commodityPrice+'</div>\n' +
                        '                <div>'+commodity.shelfTime+'</div>\n' +
                        '                <div>'+commodity.commodityInvertory+'件</div>'+
                        '            </div>\n' +
                        '            <hr size="2">\n' +
                        '        </div>';
                }
                $("#show").append(str);
                alert(str);
            }
        });
    }
})
function Get() {
    var keyword=$("input").val();
    data={key:keyword};
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/slowlife/search-by-keyword',
        data:{keyword:JSON.stringify(data)},
        async:true,
        dataType:'json',
        success:function (data) {
            data=eval("("+data+")");
            var result=data.result;
            var commodity;
            var str='';
            $("#show").remove();
            str='<div id="show"></div>';
            $("#showline").after(str);
            str='';
            for(var i=0;i<result.length;i++){
                commodity=result[i];
                str+='<div>\n' +
                    '            <!--单个商品显示区-->\n' +
                    '            <div class="line">\n' +
                    '                <!--商品图片显示-->\n' +
                    '                <a href="Commodityinfo.html?id='+commodity.commodityNumber+'"><img src="" alt="商品图片"></a>\n' +
                    '            </div>\n' +
                    '            <div class="line">\n' +
                    '                <!--商品基本信息-->\n' +
                    '                <div><a href="Commodityinfo.html?id='+commodity.commodityNumber+'">'+commodity.commodityName+'</a></div>\n' +
                    '                <div>￥'+commodity.commodityPrice+'</div>\n' +
                    '                <div>'+commodity.shelfTime+'</div>\n' +
                    '                <div>'+commodity.commodityInvertory+'件</div>'+
                    '            </div>\n' +
                    '            <hr size="2">\n' +
                    '        </div>';
            }
            $("#show").append(str);
        }
    });

}