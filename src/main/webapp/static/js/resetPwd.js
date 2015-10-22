$("#resetPwd").click(function(){
    $("#oldPwd").val("");
    $("#newPwd").val("");
    $('#resetPasswordModel').modal("show");
});

$("#resetPwdSubmit").click(function () {
    var oldP = $.trim($("#oldPwd").val());
    var newP = $.trim($("#newPwd").val());

    if (oldP == "" || oldP.length < 6) {
        alert("密码不能为空或小于6位！");
        return;
    }
    if (newP == "" || newP.length < 6) {
        alert("密码不能为空或小于6位！");
        return;
    }
    $.ajax({
        type : "POST",
        url : "/UpdateServer/resetPwd",
        data : {"oldPwd":oldP,"newPwd":newP},
        dataType : "json"
//            contentType : "application/json;charset=UTF-8",
//            async : false
    }).done(function(msg) { // 执行成功
        if ("RESULT_OK" === msg.result) {//
            alert("修改成功");
            window.location.href="/UpdateServer/login";
        } else // 用户会话已失效
        {
            alert(msg.value);
        }
    }).fail(function(jqXHR, textStatus) { // 执行失败
        alert("Request failed: " + textStatus);
    });
});