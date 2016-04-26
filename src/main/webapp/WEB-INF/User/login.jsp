<%--
  Created by IntelliJ IDEA.
  User: suruchi
  Date: 23/4/16
  Time: 1:48 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form  action="/api/login/" method="post">
    <label>
        UserName:
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

