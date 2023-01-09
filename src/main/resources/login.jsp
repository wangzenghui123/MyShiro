<%--
  Created by IntelliJ IDEA.
  User: wzh
  Date: 2023/1/1
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登陆页面</h1>
<form id="loginForm" action="">
    用户名：<input type="text" name="username"><br/>
    密码  : <input type="password" name="password"><br/>
    <input type="submit" id="submit" lay-submit lay-filter="LoginForm" value="登录">
</form>
<script src="layui/layui.js">
    layui.use(['form,jquery'],function (){
        var form = layui.form;
        var $ = layui.jquery;
        $("#submit").onclick(function (){
            alert(2)
        })
        form.on('submit(LoginForm)',function (data){
            alert(1)
            var arry = $('form').serializeArray();
            $.ajax({
                type : "post"
                ,url : "/user/login"
                ,data : {"loginReqVO" : JSON.stringify(arry)}
                ,dataType : "json"
                ,async : false
                ,contentType : "application/json;charset=UTF-8"
                ,success : function(){
                    alert("chenggong")
                }
            });
            return false;
        })
    })
    $.onsubmit()
</script>
</body>
</html>
