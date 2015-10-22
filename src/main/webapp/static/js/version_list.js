//$(function(){


$("#search").click(function(){
	var appId = $("#current_app").val();
	if (appId == undefined || appId == "0"){
		alert("请先选择一个项目!");
		return;
	}
	if ($.trim($("#from_version").val()) != "" && !Helper.validateVersion($("#from_version").val())){
		alert("第一个版本号不符合规范！");
		return; 
	}	
	if ($.trim($("#to_version").val()) != "" && !Helper.validateVersion($("#to_version").val())){
		alert("第二个版本号不符合规范！");
		return; 
	}
	var fromV = "";
	
	window.location.href="/UpdateServer/services/admin/version/query_versions?app_id=" + appId + "&version_start=" + $.trim($("#from_version").val()) + "&version_end=" + $.trim($("#to_version").val());
});

var loadVersionsByAppId = function(appId){
    if ($("#current_app").val() != ""+appId){
    	$("#current_app").val(""+appId);
    };
    
	var title = "<thead><tr><th>版本号</th><th>是否强制更新</th>" +
			"<th>是否启用</th><th>是否审核通过</th><th>服务访问路径</th>" +
			"<th>下载路径</th><th>版本描述</th><th>管理</th></tr></thead>";
		
	var raw = function(ver){
		var content = "<tr id=" + ver.id +"><td>";
		content += ver.version_string + "</td><td>";
		content += enable2str(ver.update_force) + "</td><td>";
		content += enable2str(ver.enable) + "</td><td>";
        content += enable2str(ver.audited) + "</td><td>";
		content += ver.server_url + "</td><td>";
		content += ver.download_url + "</td><td>";
		content += ver.remark + "</td>";
		content += "<td class='span3'>" +
				"<button class='btn btn-primary btn-small Jversion_update'>修改</button>" +
				"<button class='btn btn-small Jversion_del'>删除</button></td></tr>";

		return content;
	}	
	var page = new Page($("#main"));
	var json_url = "/UpdateServer/services/admin/version/query_versions";
	req_args = {"app_id":appId};
	req_args.version_start = $("#from_version").val();
	req_args.version_end = $("#to_version").val();
	page.setRequest(json_url, req_args);
	page.loadTabFrame(title, raw);
	page.regPageInternalEvents(
			function regPageInternalEvents(){
				$(".Jversion_del").click(function(){delVersion($(this).parents("tr").attr("id"))});
			    $(".Jversion_update").click(function(){
			    	  var raw = $(this).parents("tr");
			    	  $("#version_id").val(raw.attr("id"));
			         
			          $("#download_url").val(raw.find("td").eq(5).html());
			          $("#remark").val(raw.find("td").eq(6).html());
			          $("#server_url").val(raw.find("td").eq(4).html());

                      if($.trim(raw.find("td").eq(3).html()) == "是")
                        $("#audited").attr("checked", true);
                      else $("#audited").attr("checked", false);
			          if($.trim(raw.find("td").eq(2).html()) == "是") 
			            $("#enable").attr("checked", true);
                      else $("#enable").attr("checked", false);
			          if($.trim(raw.find("td").eq(1).html()) == "是")
			            $("#update_force").attr("checked", true);
                      else $("#update_force").attr("checked", false);
			          $("#version_name").html("修改版本"+raw.find("td").eq(0).html());
			          $('#version_update').modal();
			    });
			    $(".modal-footer>.btn-primary").click(function(){updateVersion()});
			}		
	);
	page.showPage();

	
	var delVersion = function(id){
		if(!confirm("确定要删除吗?")){
			return;
		}
		$.ajax({
			type : "POST",
			url : "/UpdateServer/services/admin/version/del_version",
			data : JSON.stringify({"id":id}),
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			async : false
		}).done(function(msg) { // 执行成功
			if ("RESULT_OK" === msg.result) {//
				alert("删除成功");
				page.showPage();
			} else // 用户会话已失效
			{
				alert(msg.result);
			}
		}).fail(function(jqXHR, textStatus) { // 执行失败
			alert("Request failed: " + textStatus);
		});				
	}
	
	var updateVersion = function(){
		req_args = {"id":$("#version_id").val()};
		if ($("#update_force").attr("checked")){
			req_args.update_force = 1;
		}else{
			req_args.update_force = 0;
		}
		if ($("#enable").attr("checked")){
			req_args.enable = 1;
		}else{
			req_args.enable = 0;
		}
        if ($("#audited").attr("checked")){
            req_args.audited = 1;
        }else{
            req_args.audited = 0;
        }
		if($("#server_url").val() !="" && !Helper.validateUrl($("#server_url").val())){
			alert("服务器路径不符合规范!");
			return;
		}
		if($("#download_url").val() !="" && !Helper.validateUrl($("#download_url").val())){
            if(!window.confirm("下载路径不符合规范,确定使用该地址")){return;}
		}
		req_args.download_url = $("#download_url").val();
		req_args.server_url = $("#server_url").val();
		req_args.remark = $("#remark").val();
		$.ajax({
			type : "POST",
			url : "/UpdateServer/services/admin/version/update_version",
			data : JSON.stringify(req_args),
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			async : false
		}).done(function(msg) { // 执行成功
			if ("RESULT_OK" === msg.result) {//
				$("#version_update_close").click();
				page.showPage(page.getCurrentIndex());
			} else // 用户会话已失效
			{
				alert(msg.result);
			}
		}).fail(function(jqXHR, textStatus) { // 执行失败
			alert("Request failed: " + textStatus);
		});				
	}	
	
	function enable2str(enable){
		if (enable == 1){
			return "是";
		}else if(enable == 0){
			return  "否";
		}
		return "无定义";
	}
}
//})
