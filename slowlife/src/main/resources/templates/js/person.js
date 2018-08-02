$(function () {
    var data={userNumber:localStorage.getItem("userNumber")};
    $.ajax({
        type:'get',
        url:'http://127.0.0.1:9000/v1/getPerson',
        data:{personData:JSON.stringify(data)},
        dataType:'text',
        async:true,
        success:function (data) {
            alert(data);
            data=JSON.parse(data);
            if(data.msg='success'){
                $("#picture").attr("src",data.result.userPicture);
                $("#name").text(data.result.userName);
            }
        }
    });
})