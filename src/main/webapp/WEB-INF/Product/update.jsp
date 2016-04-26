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
    <title>Update product</title>
</head>
<body>
<form  action="/Product/update/" method="post">
    <label>
        ID:
        <input type="text" name="id" id="id">

    </label>
    <label>
        UpdatedProductName:
        <input type="text" name="name" id="name">

    </label>
    <label>
        updatedDescription:
        <input type="text" name="description" id="description">
    </label>

    <input type="submit" value="Submit">
</form>
</body>
</html>

