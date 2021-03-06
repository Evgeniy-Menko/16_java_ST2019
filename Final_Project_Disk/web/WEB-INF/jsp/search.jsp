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
<%@ taglib prefix="disktable" uri="http://menko.by/diskTableTag" %>
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}"/>
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
        <h1 class="display-4">Catalog</h1>
        <br>
    </div>
    <div class="row col-md-12">
        <div class="col-md-2 border " style="margin-left: 4%; ">
            <form>
                <div class="form-group">

                    <br>
                    <label for="sel1">Type:</label>
                    <select class="form-control" id="sel1" name="type">
                        <option value="0">All</option>
                        <c:set var="ind" value="0"/>
                        <c:set var="types" value="${catalog[0].type}"/>
                        <c:forEach var="i" items="${catalog}">
                            <c:if test="${types== i.type && ind ==0}">
                                <c:set var="ind" value="1"/>
                                <option value="${i.idType}">${i.type}</option>
                            </c:if>
                            <c:if test="${types != i.type && ind ==1}">
                                <c:set var="types" value="${i.type}"/>
                                <option value="${i.idType}">${i.type}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <script>
                        $("#sel1").on("change", function () {
                            var type = $('#sel1').val();
                            var select2 = $('#sel2');
                            select2.empty();
                            select2.prop('disabled', true);

                            if (type !== "All") {
                                select2.append('<option value="0">All</option>');
                            }
                            <c:forEach var="item" items="${catalog}">
                            if (type === "${item.idType}") {
                                select2.prop('disabled', false);
                                select2.append('<option value="${item.idGenre}"> ${item.genre}</option>');
                            }
                            </c:forEach>
                        });

                    </script>
                </div>
                <div class="form-group">
                    <label for="sel2">Genre:</label>
                    <select class="form-control" id="sel2" name="genre" disabled="disabled"></select>
                </div>
                <div class="form-group">
                    <label for="price">Price:</label>
                    <input id="price" type="number" name="priceFrom" class="form-control"
                           placeholder="От" min="0" step="0.01">
                    <input id="price2" type="number" name="priceTo" class="form-control"
                           placeholder="До" min="1" step="0.01">
                    <div class="help-block with-errors error"></div>
                    <script>
                        $('#price').on('keyup', function () {


                            $("#price2").each(function () {

                                this.min = parseInt($('#price').val());
                            });
                        });
                    </script>
                </div>
                <div class="form-group">
                    <label for="inputDate">Date:</label>
                    <input type='number' step="1" min="1970" name="dateIn" id="inputDate" class="form-control"
                           placeholder="C"/>
                    <input type='number' step="1" min="1970" name="dateTo" id="inputDate2" class="form-control"
                           placeholder="По"/>
                    <script>
                        inputDate.max = new Date().getFullYear();
                        inputDate2.max = new Date().getFullYear();
                        $('#inputDate').on('keyup', function () {

                            $("#inputDate2").each(function () {

                                this.min = parseInt($('#inputDate').val());
                            });
                        });
                    </script>
                </div>
                <div class="row">

                    <div class="col-md-4"></div>
                    <button type="submit" class="btn  btn-primary btn-sm " id="submit1">
                        Search
                    </button>
                    <button type="reset" class="btn  btn-danger btn-sm" id="reset">Reset</button>

                </div>

            </form>
        </div>


        <c:choose>
            <c:when test="${error!=null}">
                <div class="col-lg-8 ">
                    <table class="table border " style="margin-left: 4%">
                        <thead>
                        <tr>
                            <th colspan="5" style="color: #b30300;text-align: center"><fmt:message key="${error}"/></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <disktable:diskTable disks="${requestScope.listDisk}"
                                     path="${pageContext.request.contextPath}"/>
            </c:otherwise>
        </c:choose>


    </div>

    </body>
    </html>
</fmt:bundle>