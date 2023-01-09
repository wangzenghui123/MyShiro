<%--
  Created by IntelliJ IDEA.
  User: wzh
  Date: 2023/1/1
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/register">
    用户名:<input type="text" name="username"><br/>
    密码：<input type="password" name="password"><br/>
    <input type="submit" value="注册">
</form>

</body>
</html>
