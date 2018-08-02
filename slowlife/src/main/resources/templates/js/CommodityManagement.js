function GetNumber() {
    //商品编号检索
    var number=$("#number").val();
    $.ajax({
        type:'post',
        url:'',
        dataType:'json',
        data:{"number":number},
        contentType:'application/json',
        success:function (data) {


            $("#name").val('name');
            $("#price").val('price');
            $("#inventory").val('inventory');
            $("#pictureShow").attr("src","");
            $("#Design").append('您好啊');
        }
    });
}
function updataName(number) {
    //修改商品名称
    var name=$("#name").val();
    $.ajax({
        type:'post',
        url:'',
        dataType:'json',
        data:{"number":number,"name":name},
        contentType:'application/json',
        success:function (data) {

            $("#name").val('name');
        }
    });

}
function updataPrice() {
    //修改商品单价
    var price=$("#price").val();
    $.ajax({
        type:'post',
        url:'',
        dataType:'json',
        data:{"number":number,"price":price},
        contentType:'application/json',
        success:function (data) {

            $("#price").val('price');
        }
    });
}
function updataPicture() {
    //重新上传封面图片

}
function updataInventory() {
    //修改商品库存
    var inventory=$("#inventory").val();
    $.ajax({
        type:'post',
        url:'',
        dataType:'json',
        data:{"number":number,"inventory":inventory},
        contentType:'application/json',
        success:function (data) {

            $("#inventory").val('inventory');
        }
    });
}
function Design() {
    //重新设计详情页面

    var str='<div id="DesignShow"></div>';
    $("#DesignShow").remove();
    $("#line").after(str);
    alert("重新设计详细");
    str='<div id="Design">\n' +
        '                <!--设计区-->\n' +
        '\n' +
        '            </div>\n' +
        '            <div>\n' +
        '                <!--工具区-->\n' +
        '                <div>\n' +
        '                    <!--文本工具区-->\n' +
        '                    <div>\n' +
        '                        <textarea id="text" rows="5" cols="100" placeholder="商品描述"></textarea>\n' +
        '                    </div>\n' +
        '                    <div>\n' +
        '                        <span>\n' +
        '                            <button id="jian" onclick="sizejian()">-</button>\n' +
        '                            size:<input type="text" size="2px" id="textsize" value="20">\n' +
        '                            <button id="jia" onclick="sizejia()">+</button>\n' +
        '                        </span>\n' +
        '                        <span>\n' +
        '                            <button onclick="text()">插入一段文本</button>\n' +
        '                        </span>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div>\n' +
        '                    <!--图片工具区-->\n' +
        '                    <div>\n' +
        '                        <input type="file" id="picture">\n' +
        '                    </div>\n' +
        '                    <div>\n' +
        '                        <span>\n' +
        '                            <button onclick="picture()">插入一张图片</button>\n' +
        '                        </span>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div>\n' +
        '                    <!--减少一个节点-->\n' +
        '                    <button onclick="deleteHtml()">取消一个节点</button>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div>\n' +
        '                <button onclick="submitDesign()">设计提交</button>\n' +
        '            </div>';
    $("#DesignShow").append(str);
}
function Delete() {
    //删除该商品
    $.ajax({
        type:'post',
        url:'',
        dataType:'json',
        data:{"number":number},
        contentType:'application/json',
        success:function (data) {


        }
    });
}