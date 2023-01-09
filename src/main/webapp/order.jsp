<%--
  Created by IntelliJ IDEA.
  User: wzh
  Date: 2023/1/1
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <shiro:hasPermission name="order:delete:*">
        <li><a href="#">删除订单</a></li>
    </shiro:hasPermission>
    <shiro:hasPermission name="order:find:*">
        <li><a href="#">查看订单</a></li>
    </shiro:hasPermission>
    <shiro:hasPermission name="order:update:*">
        <li><a href="#">修改订单</a></li>
    </shiro:hasPermission>
    <shiro:hasPermission name="order:add:*">
        <li><a href="#">增加订单</a></li>
    </shiro:hasPermission>
</ul>
</body>
</html>
