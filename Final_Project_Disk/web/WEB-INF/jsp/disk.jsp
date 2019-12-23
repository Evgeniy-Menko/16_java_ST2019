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
    <jsp:useBean id="disk" scope="request" type="by.menko.finalproject.entity.Disk"/>


    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Panda-Disk</title>

    </head>
    <body>
    <%@ include file="menu.jsp" %>
    <div class="text-center" style="margin-top:30px;margin-right: 20px">
        <h1 class="display-3">${disk.nameDisk}</h1>
        <span style="color: #b30300" id="errorValue"></span>
    </div>
    <div class="container">
        <div class="row col-md-12">
            <div class="col-md-1"></div>
            <div class="col-md-4">
                <br>
                <img src="${disk.image}" class="img-thumbnail img-fluid " alt="no image" width="350" height="400">
            </div>
            <c:set var="film" value="Film"/>
            <c:set var="game" value="Game"/>
            <c:set var="music" value="Music"/>
            <div class="col-md-6">
                <br>
                <div class="text-right"><fmt:formatDate value="${disk.timeAdded}"
                                                        type="date" pattern="dd-MM-yyyy HH:mm"/></div>
                <h6>Type: ${disk.type} </h6><br>

                <h6>Genre: ${disk.genre}</h6><br>
                <h6>Price: ${disk.price}</h6><br>
                <h6>Year: ${disk.year}</h6><br>
                <c:if test="${disk.type == film}">
                    <h6>Country: ${disk.country}</h6><br>
                    <h6>Running time: ${disk.runningTime}</h6><br>
                </c:if>
                <c:if test="${disk.type == game}">
                    <h6>Age limit: ${disk.ageLimit}</h6><br>
                    <h6>Developer: ${disk.developer}</h6><br>
                </c:if>
                <c:if test="${disk.type == music}">
                    <h6>Singer: ${disk.singer}</h6><br>
                    <h6>Albom: ${disk.albom}</h6><br>
                </c:if>
            </div>
        </div>
        <br>
        <div class="row  col-md-12">
            <div class="col-md-1"></div>
            <div class="col-md-10 border-bottom">
                <h5> Description: <br>${disk.description}</h5>
            </div>
        </div>
        <div class="row col-md-12 ">
            <div class="col-md-1"></div>
            <div class="col-md-10 border-bottom">
                <c:if test="${authorizedUser.role == 'USER'}">
                    <table class="table table-borderless">
                        <tr><c:set var="ind" value="0"/>
                            <c:forEach var="item" items="${shopCart}">
                                <c:if test="${item.diskId==disk.idEntity}">
                                    <c:set var="ind" value="1"/>
                                </c:if>
                            </c:forEach>
                            <c:if test="${authorizedUser.idEntity!=disk.idUser && ind==1}">
                                <th>
                                    Disk is in your shopping cart
                                </th>
                            </c:if>
                            <c:if test="${authorizedUser.idEntity!=disk.idUser && ind==0}">

                                <form method="post">
                                    <th>
                                        <input type="hidden" name="disk" value="${disk.idEntity}">
                                        <button type="submit" class="btn btn-link"
                                                formaction="${pageContext.request.contextPath}/addShoppingCart.html"><strong>Add
                                            to shopping cart</strong>
                                        </button>
                                    </th>
                                </form>

                            </c:if>

                            <th><a  class="nav-link" href="#commentDiv">Write comment</a></th>
                            <c:if test="${authorizedUser.idEntity!=disk.idUser}">
                                <th class="text-center"><a class="nav-link" href="#complaint" data-toggle="modal"
                                                           data-target="#complaint">Complaint</a>
                                </th>
                            </c:if>
                        </tr>
                    </table>
                </c:if>
                <c:if test="${authorizedUser.role != 'USER'}">
                    <table class="table table-borderless">
                        <tr>

                            <th class="text-center"><a href="#myModal" data-toggle="modal" data-target="#myModal">Sign
                                In</a> or <a
                                    href="${pageContext.request.contextPath}/registration.html">Sign Up</a> to leave a
                                comment or add to
                                cart
                            </th>
                        </tr>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="text-center" style="margin-top:30px;margin-right: 20px">
            <h4>Comments</h4>
            <span style="color: #b30300" id="d"></span>
        </div>

        <div class="row col-md-12" id="com">
            <div class="col-md-1"></div>
            <c:choose>
            <c:when test="${fn:length(mapComment)>3}">
            <div class="col-md-10" style="overflow-y: auto; height:400px">
                </c:when>
                <c:otherwise>
                <div class="col-md-10">
                    </c:otherwise>
                    </c:choose>


                    <c:if test="${fn:length(mapComment)<=0}">
                        <p style="text-align: center">No comments</p>
                    </c:if>

                    <c:forEach items="${mapComment}" var="item">
                        <div class="media border p-3">
                            <img src="${item.key.image}" alt="" class="mr-3 mt-3 rounded-circle"
                                 style="width:60px;">
                            <div class="media-body">
                                <h4>${item.key.nickname} <small><em>Posted
                                    on
                                    <fmt:formatDate value="${item.value.timeAdded}"
                                                    type="date" pattern="dd-MM-yyyy HH:mm"/>

                                </em></small>
                                </h4>
                                <p>${item.value.commentText}</p>
                                <c:if
                                        test="${item.value.idUserCommented == authorizedUser.idEntity}">
                                    <form method="post">
                                        <input type="hidden" name="disk" value="${disk.idEntity}">
                                        <input type="hidden" name="com" value="${item.value.idEntity}">
                                        <p style="text-align: right">
                                            <button type="submit" class="btn btn-link"
                                                    formaction="${pageContext.request.contextPath}/deleteComment.html">
                                                Delete
                                            </button>
                                        </p>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>


                </div>
            </div>
        </div>
        <c:if test="${authorizedUser.role == 'USER'}">
        <div class="container" id="commentDiv">
            <div class="row col-md-12">
                <div class="col-md-1"></div>
                <div class="col-md-10">

                    <form action="${pageContext.request.contextPath}/addComment.html" method="post">
                        <input type="hidden" name="idDisk" value="${disk.idEntity}">
                        <label for="comment">Write Comment:</label>
                        <textarea class="form-control" rows="5" id="comment" name="comment" title="Only leters,number and ,.!@#?:()" required></textarea>

                        <div class="row">
                            <div class="col-md-6"></div>
                            <button type="submit" class="btn  btn-primary btn-sm " id="submit1">
                                Submit
                            </button>

                        </div>

                    </form>

                </div>
            </div>
        </div>
        </c:if>


        <!-- The Modal -->
        <div class="modal" id="complaint">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Write complaint</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="${pageContext.request.contextPath}/addComplaint.html">
                            <input type="hidden" name="idDisk" value="${disk.idEntity}">
                            <input type="hidden" name="idUser" value="${disk.idUser}">

                            <textarea class="form-control" rows="5" id="compl" name="complaint" title="Only leters,number and ,.!@#?:()" required></textarea>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>

                </div>
            </div>
        </div>


    </body>
    </html>
</fmt:bundle>