<%--
  Created by IntelliJ IDEA.
  User: bhawna
  Date: 19/04/16
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New product</title>
</head>
<body>
<form  action="/api/register/" method="post">
    <label>
        Name:
        <input type="text" name="name" id="name">

    </label>
    <label>
        username:
        <input type="text" name="username" id="username">
    </label>
    <label>
        Password:
        <input type="password" name="password" id="password">
    </label>

    <input type="submit" value="Submit">
</form>
</body>
</html>

