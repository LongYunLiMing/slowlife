function AddEmployeeshow() {
    //员工添加栏显示
    if($("#AddShow").css("display")=="none"){
        $("#AddShow").css("display","block");
    }else if($("#AddShow").css("display")=="block"){
        $("#AddShow").css("display","none");
    }
}
function AddEmployee() {
    //员工添加函数
    var sex=$('#sex input:checked').val();
    alert("性别："+sex);
    var name=$("#name").val();
    alert("姓名;"+name);
    var phone=$("#phone").val();
    alert("联系方式："+phone);
    var permission=$('#permission input:checked');
    var pem=[];
    alert("选择了"+permission.length+"个权限");
    for(var i=0;i<permission.length;i++){
        pem[i]=$('#permission input:checked:eq('+i+')').val();
        alert(pem[i]);
    }
    alert("权限："+pem.toString());
    $.ajax({
        type:'POST',
        url:'',
        dataType:'json',
        data:{"name":name,"phone":phone,"sex":sex,"permission":pem.toString()},
        contentType:'application/json',
        success:function (data) {


            $("#AddShow").css("display","none");
        }
    });
}
function updataShow(number) {
    //联系方式修改显示   phoneUpdatanumber
    if($("#phoneUpdata"+number).css("display")=="block"){
        $("#phoneUpdata"+number).css("display","none");
    }else if($("#phoneUpdata"+number).css("display")=="none"){
        $("#phoneUpdata"+number).css("display","block");
    }
}
function updata(number) {
    //联系方式修改  phoneInputNumber
    var phone=$("#phoneUpdata"+number).val();
    alert("修改后联系方式为："+phone);
    $.ajax({
        type:'POST',
        url:'',
        dataType:'json',
        data:{"number":number,"phone":phone},
        contentType:'application/json',
        success:function (data) {

            $("#phoneUpdata"+number).css("display","none");
        }
    });
}
function permissionUpdata(number) {
    //员工权限修改 checkboxnumber
    var permission=$('#checkbox'+number+' :input:checked');
    var pem=[];
    for(var i=0;i<permission.length;i++){
        pem[i]=$('#checkbox'+number+' input:checked:eq('+i+')').val();
        alert(pem[i]);
    }
    alert("修改后权限为："+pem.toString());
    $.ajax({
        type:'POST',
        url:'',
        dataType:'json',
        data:{"number":number,"permission":pem.toString()},
        contentType:'application/json',
        success:function (data) {

            $("#permissionUpdata"+number).css("display","none");
        }
    });
}
function permissionUpdataShow(number) {
    //员工权限修改显示   permissionUpdataNumber
    if($("#permissionUpdata"+number).css("display")=="block"){
        $("#permissionUpdata"+number).css("display","none")
    }else if($("#permissionUpdata"+number).css("display")=="none"){
        $("#permissionUpdata"+number).css("display","block")
    }
}