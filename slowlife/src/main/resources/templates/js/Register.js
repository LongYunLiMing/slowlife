var is_Phone=true;
$(function () {
    $("button").attr("disabled",true);
})
function register() {
    alert("注册");
    var id=$("#userId").val();
    var psw=$("#psw").val();
    if(is_Phone){//手机号注册
        var data={"userPhone":id,"userPassword":psw};
        alert("账号："+data.userPhone+"密码："+data.userPassword);
        $.ajax({
            type: 'get',
            url: 'http://127.0.0.1:9000/v1/user-register',
            data:{registerData:JSON.stringify(data)},
            async: true,
            dataType: 'text',
            success: function (data) {
                alert("返回数据"+data)
                var url=location.href;
                var index=url.lastIndexOf("/")+1;
                url=url.substring(0,index);
                url+="First.html";
                location.href=url;
            },
            error: function (response) {
                alert("error");
            }
        });
    }else {//邮箱注册
        var data={"userEmail":id,"userPassword":psw};
        $.ajax({
            type: 'get',
            url: 'http://127.0.0.1:9000/v1/user-register',
            data:{registerData:JSON.stringify(data)},
            async: true,
            dataType: 'text',
            success: function (data) {
                alert("返回数据"+data)
                var url=location.href;
                var index=url.lastIndexOf("/")+1;
                url=url.substring(0,index);
                url+="First.html";
                location.href=url;
            },
            error: function (response) {
                alert("error");
            }
        });
    }
}
function IDclick() {

}
function IDmousout() {
    var phone=$("#userId").val();
    if(phone!=''&&phone!=null){

        if(phone.lastIndexOf("@")==-1){
            //说明没有@符号，为手机号注册
            if(phone.length==11){
                if (!isPhone(phone)) {
                    alert("请输入正确的手机号");
                    $("#userId").val('');
                }else{
                    //手机号正确
                    $.ajax({
                        type:'POST',
                        url:'',
                        dataType:'json',
                        data:{"userPhone":phone},
                        contentTpye:'application/json',
                        success:function (data) {

                        }
                    });
                }
            }else if(phone.length>11){
                $("#userId").val(phone.substring(0,11));
                alert("手机号>11位");
            }
        }else {
           // alert("邮箱注册");
            //为邮箱注册，需要进行邮箱匹配
            if(!isEmail(phone)){//输入邮箱格式不正确
                alert("请输入正确的手机号");
                $("#userId").val();
            }else{
                //邮箱格式正确
                is_Phone=false;
                $.ajax({
                    type:'POST',
                    url:'',
                    dataType:'json',
                    data:{"userEmail":phone},
                    contentTpye:'application/json',
                    success:function (data) {

                    }
                });
            }
        }

    }
}

function PSWlick() {

    }
function PSWmousout() {
        var confpsw=$("#confirmPsw").val();
        var psw=$("#psw").val();
        if(confpsw!=psw&&confpsw!=''){
            alert("密码不匹配");
            $("#confirmPsw").val('');
        }else if(confpsw==psw&&confpsw!=''){
            if($("#userId").val()!=''||$("#userId")!=null){
                $("button").attr("disabled",false);
            }else {
                alert("手机号不能为空");
            }
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
function isEmail(email) {
    var emailReg=/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
    if(emailReg.test(email)){
        return true;
    }else {
        return false;
    }
}