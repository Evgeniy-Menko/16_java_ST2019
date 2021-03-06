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
    <jsp:useBean id="mapComplaint" scope="request" type="java.util.Map"/>


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
    <jsp:useBean id="listDisk" scope="request" type="java.util.List"/>

    <div class="text-center" style="margin-top:30px;margin-right: 20px">
        <h1 class="display-4">Complaints</h1>
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
                        <c:when test="${fn:length(mapComplaint) == 0}">
                            <th colspan="5" style="text-align: center">No Complaints</th>
                        </c:when>
                    </c:choose>


                </tr>
                <tr>
                    <th>Name user</th>
                    <th>Text complaint</th>
                    <th>Time added</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                <c:set var="ind" value="0"/>
                <c:forEach var="item" items="${mapComplaint}">
                    <tr>
                        <td>${item.key.nickname}</td>
                        <td>${item.value.textComplaint}</td>
                        <td><fmt:formatDate value="${item.value.timeAdded}"
                                            type="date" pattern="dd-MM-yyyy HH:mm"/></td>

                        <form method="post">
                            <input type="hidden" name="complaint" value="${item.value.idEntity}">
                            <input type="hidden" name="disk" value="${item.value.idDisk}">
                        <td><a href="${pageContext.request.contextPath}/showDisk.html?disk=${item.value.idDisk}"
                               class="nav-link">Show announcement</a>
                            <button type="submit" class="btn btn-link" formaction="${pageContext.request.contextPath}/deleteComplaint.html">Delete complaint</button><br>
                            <c:if test="${listDisk[ind].flagBlocked == 'false'}">
                                <button type="submit" class="btn btn-link" formaction="${pageContext.request.contextPath}/block.html">Block</button></c:if>
                            <c:if test="${listDisk[ind].flagBlocked == 'true'}">
                                <button type="submit" class="btn btn-link" style="color:#b30300;" formaction="${pageContext.request.contextPath}/unlock.html">Unlock</button></c:if>

                        </td>
                        </form>
                    </tr>
                    <c:set var="ind" value="${ind+1}"/>
                </c:forEach>


                </tbody>
            </table>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>