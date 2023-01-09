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
<form action="${pageContext.request.contextPath}/user/login" method="post">
    用户名：<input type="text" name="username"><br/>
    密码  : <input type="password" name="password"><br/>
    <input type="submit" value="登录">
</form>
<script >
    $.onsubmit()
</script>
</body>
</html>
