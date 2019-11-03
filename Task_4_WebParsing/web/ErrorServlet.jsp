<%--
  Created by IntelliJ IDEA.
  User: Mavil
  Date: 02.11.2019
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <style>
        .btn {
            border-radius: 3px;

            height: 50px;
            background-color: #000000;
            border: none;
            color: #fff;
            font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande", "Lucida Sans", Arial, sans-serif;
            font-size: 14px;
            cursor: pointer;

        }
        .a1{
            color: #ffffff; text-decoration: none;
        }
    </style>
    <title>Error</title>
</head>
<body>
<h1>Invalid request,  please go to the home page!</h1>

<button class="btn"><a class="a1" href="${pageContext.request.contextPath}/home">Home</a></button>
</body>
</html>
