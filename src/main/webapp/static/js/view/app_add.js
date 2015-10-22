

function app_add_view(){
    var add_app_html="<form class='boarder offset1'>"
        +"<fieldset>"
        +"<div class='span6'>"
        +"<legend style='width:250px'>添加项目</legend>"
        +"<label>项目名：</label>"
        +"<input id='app_add_name' type='text' class='input-large'>"
        +"<span class='help-block'></span>"
        +"<label>项目描述：</label>"
        +"<textarea id='app_add_description'></textarea>"
        +"<span class='help-block'></span>"
        +"<button type='submit' id='app_add_btn' class='btn btn-primary'>提交</button> </div>"
        +"</fieldset>"
        +"</form>";
    $("#main").html(add_app_html);
    $("#app_add_btn").bind("click",function(){
        var app_name = $.trim($("#app_add_name").val());
        var app_description = $.trim($("#app_add_description").val());
        if(app_name == "")
        {
            alert("项目名不能为空");
            return false;
        }
        else if(app_name.length > 20)
        {
            alert("项目名不能超过20字");
            return false;
        }
        if(app_description.length > 100)
        {
            alert("项目描述不能超过100字");
            return false;
        }
        $.ajax({
            type : "POST",
            url : "/UpdateServer/services/admin/app/add",
            data : JSON.stringify({name:app_name,description:app_description}),
            dataType : "json",
            contentType : "application/json;charset=UTF-8"
        }).done(function(msg) {
            if ("RESULT_OK" === msg.result) {
                app_page.show();
            }else{
                alert(msg.value);
            }
        });
    });
};