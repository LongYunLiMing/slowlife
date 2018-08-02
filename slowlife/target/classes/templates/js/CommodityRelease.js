var y=0;//设计区的节点id 自增长
var number='';
$(function () {
   // $("#CommoditySubmit").attr("disabled",true);
})
function upload() {
    //商品基本信息上传
    var name=$("#CommodityName").val();
    var price=$("#CommodityPrice").val();
    var inventory=$("#CommodityInventory").val();
    var picture=$("#CommodityPicture").val();
    if(name!=""&&price!=""&&inventory!=""&&picture!=""){
        $.ajax({
            type:'post',
            url:'',
            secureuri: false, //一般设置为false
            fileElementId: 'CommodityPicture', // 上传文件的id、name属性名
            dataType: 'json', //返回值类型，一般设置为json、application/json
            data: {"name":name,"price":price,"inventory":inventory}, //传递参数到服务器
            success: function (data, status) {
                if (data.Result) {
                    alert("文件成功处理完成!" + data.FileName);
                } else {
                    alert("文件成功处理出错！原因：" + data.ErrorMessage);
                }
            },
            error: function (data, status, e) {
                alert("错误：上传组件错误，请检察网络!");
            }
        });
    }else {
        alert("请填写完整内容");
    }
}
function WritDetailShow() {
    //商品详细页面显示  DetailShow
   if(number!=''){
       if($("#DetailShow").css("display")=="none"){
           $("#DetailShow").css("display","block");
       }else if($("#DetailShow").css("display")=="block"){
           $("#DetailShow").css("display","none");
       }
   }else{
       alert("请先发布商品基本信息");
       if($("#DetailShow").css("display")=="none"){
           $("#DetailShow").css("display","block");
       }else if($("#DetailShow").css("display")=="block"){
           $("#DetailShow").css("display","none");
       }
   }
}
function sizejian() {
    //插入文本的size-
    var size=$("#textsize").val();
    size=parseInt(size);
    if(size>5){
        size-=1;
        $("#textsize").val(size);
    }else{
        alert("已达到下限");
        $("#textsize").val(5)
    }
}
function sizejia() {
    //插入文本的size-
    var size=$("#textsize").val();
    size=parseInt(size);
    if(size<50){
        size+=1;
        $("#textsize").val(size);
    }else{
        alert("已达到上限");
        $("#textsize").val(50)
    }
}
function text() {
    //插入一段文本
    var text=$("#text").val();
    var size=$("#textsize").val();
    text=text.replace(/(\r)*\n/g,"<br/>").replace(/\s/g,"&nbsp;");
    var str='<div id="'+y+'"><p style="font-size: '+size+'px">'+text+'</p></div>';
    y+=1;
    $("#Design").append(str);
}
function picture() {
    //插入一张图片

}
function deleteHtml() {
    //取消一个节点
    var id=y-1;
    if(y>=0){
        $("#"+id).remove();
        y-=1;
    }

}
function submitDesign() {
    //设计提交按钮
    var html=$("#Design").html();
    $("#Design").append(html);
}