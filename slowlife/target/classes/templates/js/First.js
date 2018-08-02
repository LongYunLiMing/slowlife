$(document).read(function () {
    alert("欢迎用户"+localStorage.getItem("userId")+"访问主页");
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/slowlife/index',
        dataType:'json',
        anync:true,
        success:function(data){
            alert(data);
            data=JSON.parse(data);
            var result=data.result;
            var str="";
            for(var i=0;i<result.length;i++){
                str+="<a href=\"merchentinfo.jsp?number="+result[i].merchentNumber+"&name="+result[i].merchentName+"&quantity="+result[i].merchentQuantity+"&picture="+result[i].merchentPicture+"\"><div class=\"Merchent\">"+
                    "<span class=\"merchentPicture\">"+
                    "<img alt=\"商家图片2\" src=\""+result[i].merchentPicture+"\">"+
                    "</span>"+
                    "<div class=\"MerchentInfo\">"+
                    "<span>"+result[i].merchentName+"</span><br>"+
                    "<span>"+result[i].commodityQuantity+"</span>"+
                    "</div>"+
                    "<hr size=\"2\">"
                "</div></a>";
            }
            $("#merchent").after(str);
        }
    });
});

function Get() {
    var keyword=$("input").val();
    alert(keyword);
    var url=location.href;
    var index=url.lastIndexOf("/")+1;
    url=url.substring(0,index);
    url+="CommodityShow.html?K="+keyword;
    location.href=url;
}