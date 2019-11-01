<%--
  Created by IntelliJ IDEA.
  User: Mavil
  Date: 31.10.2019
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>

<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/home?action=result" method="post">
    <p><input type="file" name="file">
    <p><input type="text" name="parse" value="DOM">
        <button>Отправить</button>
</form>
</body>
</html>
