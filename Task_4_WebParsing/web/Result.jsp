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
<head>
    <title>Title</title>
</head>
<body>
<table>

    <thead>
    <tr>
        <th><strong>1</strong></th>
        <th><strong>2</strong></th>
        <th><strong>3</strong></th>
        <th><strong>4</strong></th>
        <th><strong>5</strong></th>
        <th><strong>6</strong></th>
        <th><strong>7</strong></th>
        <th><strong>8</strong></th>
        <th><strong>9</strong></th>
        <th><strong>10</strong></th>
        <th><strong>11</strong></th>
        <th><strong>12</strong></th>
        <th><strong>13</strong></th>
        <th><strong>14</strong></th>
        <th><strong>15</strong></th>
    </thead>
    <tbody>

    <c:forEach items="${list}" var="entry">
        <tr>
            <td align="center">${entry}</td>
            <td align="center">${entry.name}</td>
            <td align="center">${entry.energy}</td>
            <td align="center">${entry.type.typeChocolate}</td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
