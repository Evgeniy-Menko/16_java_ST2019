<%--
  Created by IntelliJ IDEA.
  User: Mavil
  Date: 10.11.2019
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="en_US"/>
<fmt:bundle basename="text">
    <html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/datepicker/0.6.5/datepicker.css">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/datepicker/0.6.5/datepicker.js"></script>

        <title>Panda-Disk</title>


    </head>
    <body>
    <%@ include file="menu.jsp" %>
    <div class="text-center" style="margin-top:30px;margin-left: 50px">
        <h1> Shopping Cart</h1>
        <br>
    </div>
    <div class="row col-md-12">
        <div class="col-md-2 "></div>
        <div class="col-lg-8 ">
            <table class="table border " style="margin-left: 4%">
                <thead>
                <tr>
                    <c:choose>
                        <c:when test="${error!=null}">
                            <th colspan="5" style="color: #b30300;text-align: center"><fmt:message key="${error}"/></th>
                        </c:when>
                        <c:when test="${fn:length(listDisk) == 0}">
                            <th colspan="5" style="text-align: center">Shopping cart is empty</th>
                        </c:when>
                    </c:choose>


                </tr>
                <tr>
                    <th></th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Time added</th>
                    <th>  <c:if test="${fn:length(listDisk) != 0}">
                        <a href="${pageContext.request.contextPath}/deleteAll.html"
                           class="nav-link">Delete All</a>
                    </c:if></th>
                </tr>
                </thead>
                <tbody>


                <c:forEach var="item" items="${listDisk}">
                    <tr>
                        <td><img src="${item.image}" height="100" width="100"></td>
                        <td>${item.nameDisk}</td>
                        <td>${item.price}</td>
                        <td><fmt:formatDate value="${item.timeAdded}"
                                            type="date" pattern="dd-MM-yyyy HH:mm"/></td>
                        <td><a href="${pageContext.request.contextPath}/showDisk.html?disk=${item.idEntity}"
                               class="nav-link">More</a>

                            <a href="${pageContext.request.contextPath}/deleteFromShopCart.html?disk=${item.idEntity}"
                               class="nav-link">Delete</a>
                        </td>
                    </tr>
                </c:forEach>




                </tbody>
            </table>
        </div>
    </div>

    </body>
    </html>
</fmt:bundle>