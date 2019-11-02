<%--
  Created by IntelliJ IDEA.
  User: Mavil
  Date: 31.10.2019
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style type="text/css">
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
    .cwdtable {
        font-size: 12px;
        color: #fbfbfb;
        width: 100%;
        border-width: 1px;
        border-color: #686767;
        border-collapse: collapse;
    }

    .cwdtable th {
        color: #fff;
        font-size: 20px;
        background: #171515;
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #686767;

    }

    .cwdtable tr {
        background: #fff;
        color: #000
    }

    .cwdtable td {
        font-size: 12px;
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #686767;
    }

    .mobileTable {
        overflow: auto;
        width: 100%
    }

    .cwdtable th, .cwdtable td {
        text-align: center
    }

    .cwdtable tr:hover {
        background: #d5d5d5;
        color: #000;
    }

</style>
<style>
    .open {
        border: solid 1px black;
        border-right: none;
        border-bottom: none;
        text-align: center;
        font-weight: bold;
    }

    .close {
        border: solid 1px black;
        border-right: none;
        text-align: center;
        background: #cfd6d4;
    }

</style>
<head>

    <title>Result</title>
</head>
<body>
<div class="mobileTable">
    <table class="cwdtable" cellspacing="0" cellpadding="1" border="1">

        <thead>
        <tr>
            <th rowspan="2">№</th>
            <th rowspan="2">Name</th>
            <th colspan="2">Type</th>
            <th rowspan="2">Energy</th>
            <th colspan="6">Ingredients</th>
            <th colspan="3">Values</th>
            <th rowspan="2">Manufacturer</th>
            <th rowspan="2">Production date</th>
        </tr>
        <tr>
            <th>Type</th>
            <th>Filling</th>
            <th>Water</th>
            <th>Sugar</th>
            <th>Fructose</th>
            <th>Vanillin</th>
            <th>Taste</th>
            <th>Type chocolate</th>
            <th>Proteins</th>
            <th>Fats</th>
            <th>Carbohydrates</th>

        </tr>
        </thead>
        <tbody>

        <c:forEach items="${list}" var="entry">
            <tr>
                <td>${entry.id}</td>
                <td>${entry.name}</td>
                <td>${entry.type.candyTypes.name}</td>
                <td>${entry.type.candyTypes.filling}</td>
                <td>${entry.energy.count} ${entry.energy.ci}</td>
                <c:forEach items="${entry.type.ingredients}" var="item">
                    <td>${item.count} ${item.ci}</td>
                </c:forEach>
                <td>${entry.type.tasteCaramel}</td>
                <td>${entry.type.typeChocolate}</td>
                <c:forEach items="${entry.type.values}" var="item">
                    <td>${item.count} ${item.ci}</td>
                </c:forEach>
                <td>${entry.production}</td>
                <td>${entry.productionDate}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button class="btn"><a class="a1" href="${pageContext.request.contextPath}/home">Home</a></button>
</div>
</body>
</html>
