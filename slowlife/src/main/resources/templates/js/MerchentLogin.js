$(function () {
    $("button").attr("disabled",true);
})
$("button").click(function () {
    var id=$("#userId").val();
    var psw=$("#userPaw").val();
    $.ajax({
        type:'POST',
        url:'',
        dataType:'json',
        data:{"userId":id,"userPsw":psw} ,
        contentType:'application/json',
        success:function (data) {


            var url=location.href;
            var index=url.lastIndexOf("/")+1;
            url=url.substring(0,index);
            url+="First.html";
            location.href=url;
        }
    });
})
function IDout() {
    var phone=$("#userId").val();
    if(phone!=''||phone!=null){
        if(phone.length==11){
            if (!isPhone(phone)) {
                alert("请输入正确的手机号");
                $("#userId").val('');
            }
        }else if(phone.length>11){
            $("#userId").val(phone.substring(0,11));
            alert("手机号>11位");
        }
        $.ajax({
            type:'POST',
            url:'',
            dataType:'json',
            data:{"userId":phone},
            contentTpye:'application/json',
            success:function (data) {

            }
        });
    }
}
function PSWout() {
    if($("#userId").val()!=''&&$("#userPaw").val()!=''){
        $("button").attr("disabled",false );
    }
}
function isPhone(phone) {
    var phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/;
    if (phoneReg.test(phone)) {
        return true;
    } else {
        return false;
    }
}