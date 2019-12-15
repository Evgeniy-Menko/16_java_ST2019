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
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}" />
<fmt:bundle basename="text">
    <html lang="${language}">

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
    <div class="text-center" style="margin-top:30px;margin-right: 20px">
        <h1 class="display-4">My announcements</h1>
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
                            <th colspan="5" style="text-align: center">Not found</th>
                        </c:when>
                    </c:choose>


                </tr>
                <tr>
                    <th></th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Time added</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>


                <c:forEach var="item" items="${listDisk}">
                    <tr>
                        <td><img src="${item.image}" alt=" " height="100" width="100"></td>
                        <td>${item.nameDisk}</td>
                        <td>${item.price}</td>


                        <td><fmt:formatDate value="${item.timeAdded}"
                                            type="date" pattern="dd-MM-yyyy HH:mm"/></td>
                            <form method="post">
                                <input type="hidden" name="disk" value="${item.idEntity}">
                        <td>
                            <button type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/showDisk.html">More</button>
                            <button type="submit" class="btn btn-primary" formaction="${pageContext.request.contextPath}/updateAnnouncement.html">Edit</button>
                            <button type="submit" class="btn btn-danger"  formaction="${pageContext.request.contextPath}/deleteDisk.html">Delete</button>
                        </td>
                        </form>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
        </div>
    </div>

    </body>
    </html>
</fmt:bundle>