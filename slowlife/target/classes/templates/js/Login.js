$(function () {
    $("button").attr("disabled",true);
})
function login() {
    alert("登录");
    var id=$("#userId").val();
    var psw=$("#userPaw").val();
    if(id.lastIndexOf("@")==-1){
        var data={"userPhone":id,"userPassword":psw};
    }else {
        var data={"userEmail":id,"userPassword":psw};
    }
    alert(data);

    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/user-login',
        data:{loginData:JSON.stringify(data)} ,
        async:true,
        dataType:'json',
        success:function (data) {
            alert(data);
            var data=JSON.parse(data);
            var msg=data.msg;
            if(msg=='success'){
                var token=data.token;
                //alert("验证码"+token);
                var user=data.user;
                //alert("用户编号"+user.userNumber);
                localStorage.setItem("userNumber",user.userNumber);
                var url=location.href;
                var index=url.lastIndexOf("/")+1;
                url=url.substring(0,index);
                url+="First.html";
                location.href=url;
            }else {
                alert(data.result);
            }

        }
    });
}
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