
//var app_page = {};
//app_page.show = function(){
//    $.ajax({
//        type : "GET",
//        url : "/UpdateServer/services/admin/app/get_all?"+new Date().getTime(),
//        dataType : "json",
//        contentType : "application/json;charset=UTF-8"
//        }).done(function(msg) {
//        	if ("RESULT_OK" === msg.result) {
//        		var app_list_html = "<table class='table table-bordered table-striped table-fluid'>"
//                    +"<thead><tr><th>项目名</th><th>项目描述</th><th>是否启用</th><th>管理</th></tr></thead><tbody>";
//	            var app_select_html = "<option value='0'>项目列表</option>";
//	            var app_list = msg.value;
//	            for(var i=0;i<app_list.length;i++){
//	                var enable_string = "是";
//	                if(app_list[i].enable == 0)
//	                {
//	                    enable_string = "否";
//	                }else{
//	                    app_select_html = app_select_html + "<option value='" + app_list[i].id +"'>"+app_list[i].name+"</option>";
//
//	                }
//	                app_list_html = app_list_html + "<tr data-id='" + app_list[i].id +"'>"
//	                        +"<td class='JVersion_show'><a href='#'>"+app_list[i].name+"</a></td>"
//	                        +"<td>"+app_list[i].description+"</td>"
//	                        +"<td>"+enable_string+"</td>"
//	                        +"<td><button class='btn btn-primary Japp_update'>修改</button></td></tr>";
//	            }
//	            app_list_html = app_list_html +"</tbody></table>";
//	            $("#main").html(app_list_html);
//	            $("#current_app").html(app_select_html);
//	            app_page_bind();
//        	}else{
//                alert(msg.value);
//            }
//    });
//    function app_page_bind(){
//          $(".Japp_update").bind("click",function(){
//            var current = $(this).parent();
//            current = current.prev();
//            if($.trim(current.html()) == "是")
//              $("#app_enable").attr("checked", true);
//            else $("#app_enable").attr("checked", false);
//            current = current.prev();
//            $("#app_description").html(current.html());
//            $("#app_description").val(current.html());
//            current = current.prev();
//            $("#app_name").val(current.find("a").html());
//            $("#app_id").val(current.parent().attr("data-id"));
//            $('#app_update').modal();
//          });
//        $(".JVersion_show").bind("click",function(){
//            var appId = $(this).parent().attr("data-id");
//            loadVersionsByAppId(appId);
//        });
//
//    }
//};

$("#app_update_btn").click(function(){
    var app_name = $.trim($("#app_name").val());
    var app_description = $.trim($("#app_description").val());
    var app_enable = 0;
    var app_id = $.trim($("#app_id").val());
    if($("#app_enable").attr("checked"))
    {
        app_enable = 1;
    }
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
        url : "/UpdateServer/services/admin/app/update",
        data : JSON.stringify({id:app_id,name:app_name,description:app_description,enable:app_enable}),
        dataType : "json",
        contentType : "application/json;charset=UTF-8"
    }).done(function(msg) {
        if ("RESULT_OK" === msg.result) {
            $('#app_update').modal("hide");
            window.location.reload(true);
        }else{
            alert(msg.value);
        }
    });
});