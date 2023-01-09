<%--
  Created by IntelliJ IDEA.
  User: wzh
  Date: 2023/1/1
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>系统主页v1.0</h1>
<a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
<ul>
    <li><a href="#">用户管理</a></li>
    <shiro:hasPermission name="user:*:*">
        <li><a href="#">商品管理</a></li>
    </shiro:hasPermission>

    <shiro:hasRole name="admin">
        <li><a href="#">订单管理</a></li>
        <li><a href="#">物流管理</a></li>
    </shiro:hasRole>


</ul>
</body>
</html>
