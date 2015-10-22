<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>欢迎使用版本信息管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="/UpdateServer/static/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 100px auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
      .input-block-level{
        width: auto;
      }
      label.tit{
        width:60px;
        display: inline;
        padding-bottom: 10px;
      }
      h2 {
        border-bottom: 1px solid #999999;
        padding-bottom: 10px;
        margin-bottom: 10px;
      }
      label.error{
          display:inline;
          margin-left:10px;
          color:red;
      }

    </style>
  </head>

  <body>

    <div class="container">

      <form class="form-signin" method="post" action="/UpdateServer/login">
        <h2 class="form-signin-heading">版本信息管理</small></h2>
        <label for="" class="tit">用户名:&nbsp;&nbsp;&nbsp;</label><input name="name" id="name" type="text" class="input-block-level" value="">
        <br>
        <label for="" class="tit">密&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;&nbsp;</label><input name="password" id="pwd" type="password" class="input-block-level" >

          <input type="submit" class="btn btn-large btn-primary" value="登录"/>
          <label for="" class="error">${errorMsg!}</label>
      </form>

    </div>
    <script src="/UpdateServer/static/js/jquery.min.js"></script>
    <script type="text/javascript">
//        $(".btn").click(function(){
//            var name = $.trim($("#name").val());
//            if(name == "")
//                return false;
//            var pwd = $.trim($("#pwd").val());
//            if(pwd == "")
//                return false;
//            var mark = 0;
//            if($("#mark").attr("checked")){
//                mark = 1;
//            }
//            $.ajax({
//                type : "POST",
//                url : "/UpdateServer/login",
//                data : {username:name,password:pwd},
//                dataType : "json"
//            }).done(function(msg) { // 执行成功
//                if ("RESULT_OK" === msg.result) {
//                    window.location.href="/UpdateServer"
//                }else{
//                    $(".error").html(msg.value);
//                }
//            }).fail(function(jqXHR, textStatus) { // 执行失败
//            	alert("服务器异常，请稍后再试");
//            });
//        });

        document.onkeydown = function (e) {
            var theEvent = window.event || e;
            var code = theEvent.keyCode || theEvent.which;
            if (code == 13) {
                $(".btn").click();
            }
        };
    </script>
  </body>
</html>
