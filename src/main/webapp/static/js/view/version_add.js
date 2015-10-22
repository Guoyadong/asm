
//function version_add_view(){
//
//    function upload(){
//
//    }
//
//    var version_add_html = "<form class='boarder offset1'><fieldset><div class='span6'>"
//        +"<legend style='width:250px'>添加版本</legend>"
//        +"<select id='version_add_app_id'>"
//        +$("#current_app").html()
//        +"</select> <label>版本号：</label>"
//        +"<div class=''>"
//        +"<input class='span1' id='version_add_1' type='text'> "
//        +"<span class='add-on'>-</span> "
//        +"<input class='span1' id='version_add_2' type='text'>"
//        +"<span class='add-on'>-</span>"
//        +"<input class='span1' id='version_add_3' type='text'>"
//        +"<span class='add-on'>-</span>"
//        +"<input class='span2' id='version_add_4' type='text'>"
//        +" </div>"
//        +"<span class='help-block'>版本号格式：3位-3位-3位-8位（只能是数字）</span>"
//        +"<label>服务访问路径（以“http://”开始）：</label> <input id='version_add_server_url' type='text' class='input-large'>"
//        +"<label>版本下载路径（以“http://”开始）：</label> <input id='version_add_download_url' type='text' class='input-large'>"
//        +"<label>版本描述：</label> <textarea id='version_add_remark' rows='3' cols='20'></textarea>"
//        +"<label>上传文件（安卓客户端需要上传文件）：</label> <input id='version_file' name='file' type='file' class='input-large'>"
//        +"<span class='help-block' id='tishi' style='display:none'></span>"
//        +"<label class='checkbox'> <input id='version_add_update_force' type='checkbox'> 是否强制更新 </label>"
//        +"<label class='checkbox'> <input id='version_add_enable' type='checkbox'> 是否启用 </label>"
//        +"<label class='checkbox'> <input id='version_add_audited' type='checkbox'> 是否审核通过（ios） </label>"
//        +"<button type='submit' id='version_add_btn'  class='btn btn-primary'>提交</button>"
//        +"</div> </fieldset> </form>";
//    $("#main").html(version_add_html);
//}

$("#version_file").live("change",function(){
    $.ajaxFileUpload(
        {
            url:"/UpdateServer/upload",
            secureuri:false,
            fileElementId:"version_file",
            dataType: "txt",
            success: function (result)
            {
                var msg = result;
                msg = result.replace("<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">","").replace("</pre>","");
                msg = eval("("+msg +")");
                if ("RESULT_OK" === msg.result) {
                    file_name = msg.value;
                    $("#tishi").html("成功上传文件：" + file_name.split("/")[1]);
                    $("#tishi").css("color","green");
                    $("#tishi").show();
                }else if("RESULT_SKIP" === msg.result){
                    file_name = "";
                }else{
                    file_name = "";
                    alert(msg.value);
                }
            },
            error:  function ()
            {
                file_name = "";
            }
        });

    var getType = function(object) {
        var _t;
        return ((_t = typeof(object)) == "object" ? object == null && "null" || Object.prototype.toString.call(object).slice(8, -1) : _t).toLowerCase();
    }
    function isString(o) {
        return getType(o) == "string";
    }
});


