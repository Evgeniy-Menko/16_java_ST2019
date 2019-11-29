<%--
  Created by IntelliJ IDEA.
  User: Mavil
  Date: 10.11.2019
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <style>
        .dropdown-submenu {
            position: relative;
        }

        .dropdown-submenu .dropdown-menu {
            top: 0;
            left: 100%;
            margin-top: -1px;
        }
    </style>

</head>
<body>
<nav class="navbar navbar-expand-sm bg-primary navbar-dark">

    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
        <img src="images/905.jpg" alt="logo" style="width:200px;">
    </a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb"
            aria-expanded="true">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="navbar-collapse collapse show" id="navb" style="">
        <ul class="navbar-nav mr-sm-4 ">
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/">Home</a>
            </li>


            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    Catalog
                </a>
                <ul class="dropdown-menu">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/search.jsp">All</a>
                    <c:set var="type" value="${firstType}"/>
                    <c:set var="index" value="0"/>

                    <c:forEach var="item" items="${catalog}">

                        <c:if test="${type == item.type && index==0}">

                            <c:set var="index" value="1"/>
                            <li class="dropdown-submenu">
                            <a class="dropdown-item nav-link dropdown-toggle test text-dark" id="navbardrop2"
                               data-toggle="dropdown"
                               tabindex="-1" href="#">${item.type}<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                            <a class="dropdown-item" href="#">All</a>
                        </c:if>

                        <c:if test="${type == item.type && index==1}">
                            <a class="dropdown-item" href="#">${item.genre}</a>
                        </c:if>
                        <c:if test="${type != item.type }">
                            </ul>
                            </li>
                            <c:set var="type" value="${item.type}"/>
                            <c:set var="index" value="0"/>
                        </c:if>

                    </c:forEach>
                    <c:if test="${index==1}">
                </ul>
            </li>
            </c:if>

        </ul>
        </li>


            <li class="nav-item">
                <a class="nav-link" href="javascript:void(0)">Company</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0 mr-auto">

            <input class="form-control mr-sm-1" type="text" placeholder="Search">

            <button class="btn btn-light my-1 mr-sm-1" type="button">Search</button>
        </form>
        <c:choose>

            <c:when test="${authorizedUser.role == 'USER'}">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link
" href="javascript:void(0)"> My profile</a>
                    </li>
                    <p class="text-white my-2">|</p>

                    <li class="nav-item">
                        <a class="nav-link
" href="${pageContext.request.contextPath}/logout.html">Logout</a>
                    </li>
                </ul>
            </c:when>
            <c:when test="${sessionScope.redirectedData.authorizedUser.role == 'ADMINISTRATOR'}">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link
" href="javascript:void(0)"> My profile</a>
                    </li>
                    <p class="text-white my-2">|</p>

                    <li class="nav-item">
                        <a class="nav-link
" href="${pageContext.request.contextPath}/logout.html">Logout</a>
                    </li>
                </ul>

            </c:when>
            <c:otherwise>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="javascript:void(0)" class=" nav-link " data-toggle="modal" data-target="#myModal"
                           style="border: 0">Sing in</a>
                    </li>

                    <p class="text-white my-2">|</p>

                    <li class="nav-item">
                        <a class="nav-link
" href="${pageContext.request.contextPath}/registration.jsp">Sing Up</a>
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>

    </div>

</nav>
<div class="text-center" style="margin-top:30px;margin-right: 20px">
    <h1>Catalog</h1>
    <br>
</div>
<div class="row col-md-12">
    <div class="col-md-2 border " style="margin-left: 4%; ">
        <form>
            <div class="form-group">
                <br>
                <label for="sel1">Type:</label>
                <select class="form-control" id="sel1" name="type">
                    <option value="All">All</option>
                    <option value="Film">Films</option>
                    <option value="Game">Games</option>
                    <option value="Music">Music</option>
                </select>
                <script>
                    $("#sel1").on("change", function () {
                        var type = $('#sel1').val();
                        var select2 = $('#sel2');
                        select2.prop('disabled', true);
                        select2.empty();
                        if (type === "Game") {
                            select2.prop('disabled', false);
                            select2.append('<option value="All">All</option>');
                            select2.append('<option value="  Action">Action</option>');
                            select2.append('<option value="Adventure">Adventure</option>');
                            select2.append('<option value="Arcade">Arcade</option>');
                            select2.append('<option value="Fighting">Fighting</option>');
                            select2.append('<option value="MMORPG">MMORPG</option>');
                            select2.append('<option value="Quest">Quest</option>');
                            select2.append('<option value="Racing">Racing</option>');
                            select2.append('<option value="Shooter">Shooter</option>');
                            select2.append('<option value="Simulator">Simulator</option>');
                            select2.append('<option value="Sport">Shooter</option>');
                            select2.append('<option value="Strategy">Simulator</option>');

                        } else if (type === "Film") {
                            select2.prop('disabled', false);
                            select2.append('<option value="All">All</option>');
                            select2.append('<option value="Action">Action</option>');
                            select2.append('<option value="Westerns">Westerns</option>');
                            select2.append('<option value="Military">Military</option>');
                            select2.append('<option value="Fighting">Fighting</option>');
                            select2.append('<option value="Detectives">Detectives</option>');
                            select2.append('<option value="Documentary">Documentary</option>');
                            select2.append('<option value="Drama">Drama</option>');
                            select2.append('<option value="Historical">Historical</option>');
                            select2.append('<option value="Comedy">Comedy</option>');
                            select2.append('<option value="Crime">Crime</option>');
                            select2.append('<option value="Romance">Romance</option>');
                            select2.append('<option value="Horror">Horror</option>');
                            select2.append('<option value="Fantasy">Fantasy</option>');
                        } else if (type === "Music") {
                            select2.prop('disabled', false);
                            select2.append('<option value="All">All</option>');
                            select2.append('<option value="Country">Country</option>');
                            select2.append('<option value="Blues">Blues</option>');
                            select2.append('<option value="Jazz">Jazz</option>');
                            select2.append('<option value="Chanson, Romance">Chanson, Romance</option>');
                            select2.append('<option value="Electronic music">Electronic music</option>');
                            select2.append('<option value="Rock">Rock</option>');
                            select2.append('<option value="Hip Hop">Hip Hop</option>');
                            select2.append('<option value="Reggae">Reggae</option>');
                            select2.append('<option value="Pop">Pop</option>');
                        }

                    });

                </script>
            </div>
            <div class="form-group">
                <label for="sel2">Genre:</label>
                <select class="form-control" id="sel2" name="genre" disabled="disabled"></select>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input id="price" type="number" name="price" class="form-control"
                       placeholder="От" min="0" step="0.01">
                <input id="price2" type="number" name="price" class="form-control"
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
                <input type='number' step="1" min="1970" id="inputDate" class="form-control" placeholder="C"/>
                <input type='number' step="1" min="1970" id="inputDate2" class="form-control" placeholder="По"/>
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


    <div class="col-lg-8 ">
        <table class="table border " style="margin-left: 4%">
            <thead>
            <tr>
                <th colspan="3"></th>
            </tr>
            </thead>
            <tbody>
          
            </tbody>
        </table>
    </div>
</div>

<!-- The Modal -->
<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Login form</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/index.jsp" method="post">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pswd">
                    </div>
                    <div class="custom-control custom-checkbox mb-3">
                        <input type="checkbox" class="custom-control-input" id="customCheck" name="example1">
                        <label class="custom-control-label" for="customCheck">Custom checkbox</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <button type="reset" class="btn btn-danger ">Reset</button>
                </form>
            </div>

        </div>
    </div>
</div>


<script>
    $(document).ready(function () {
        $('.dropdown-submenu a.test').on("click", function (e) {
            $(this).next('ul').toggle();
            e.stopPropagation();
            e.preventDefault();
        });
    });

</script>
</body>
</html>
