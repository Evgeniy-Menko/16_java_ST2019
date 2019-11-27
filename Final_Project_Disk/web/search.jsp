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
    <script>
        $("#sel1").on("change", function () {
            var type = $('#sel1').val();
            var select2 = $('#sel2');
            select2.prop('disabled', true);
            select2.empty();
            if (type === "Game") {
                select2.prop('disabled', false);
                select2.append('<option value="All">All</option>');
                select2.append('<option value="Action">Action</option>');
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
                    <a class="dropdown-item" href="#">All</a>
                    <li class="dropdown-submenu">
                        <a class="dropdown-item nav-link dropdown-toggle test text-dark" id="navbardrop2"
                           data-toggle="dropdown"
                           tabindex="-1" href="#">Games<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <a class="dropdown-item" href="#">All</a>
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Adventure</a>
                            <a class="dropdown-item" href="#">Arcade</a>
                            <a class="dropdown-item" href="#">Fighting</a>
                            <a class="dropdown-item" href="#">MMORPG</a>
                            <a class="dropdown-item" href="#">Quest</a>
                            <a class="dropdown-item" href="#">Racing</a>
                            <a class="dropdown-item" href="#">RPG</a>
                            <a class="dropdown-item" href="#">Shooter</a>
                            <a class="dropdown-item" href="#">Simulator</a>
                            <a class="dropdown-item" href="#">Sport</a>
                            <a class="dropdown-item" href="#">Strategy</a>
                        </ul>
                    </li>
                    <li class="dropdown-submenu">
                        <a class="dropdown-item nav-link dropdown-toggle test text-dark" id="navbardrop3"
                           data-toggle="dropdown"
                           tabindex="-1" href="#">Films<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <a class="dropdown-item" href="#">All</a>
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Westerns</a>
                            <a class="dropdown-item" href="#">Military</a>
                            <a class="dropdown-item" href="#">Detectives</a>
                            <a class="dropdown-item" href="#">Document</a>
                            <a class="dropdown-item" href="#">Drama</a>
                            <a class="dropdown-item" href="#">Historical</a>
                            <a class="dropdown-item" href="#">Comedy</a>
                            <a class="dropdown-item" href="#">Crime</a>
                            <a class="dropdown-item" href="#">Romance</a>
                            <a class="dropdown-item" href="#">Horror</a>
                            <a class="dropdown-item" href="#">Fantasy</a>
                        </ul>
                    </li>
                    <li class="dropdown-submenu">
                        <a class="dropdown-item nav-link dropdown-toggle test text-dark" id="navbardrop4"
                           data-toggle="dropdown"
                           tabindex="-1" href="#">Music<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <a class="dropdown-item" href="#">All</a>
                            <a class="dropdown-item" href="#">Country</a>
                            <a class="dropdown-item" href="#">Blues</a>
                            <a class="dropdown-item" href="#">Jazz</a>
                            <a class="dropdown-item" href="#">Chanson, Romance</a>
                            <a class="dropdown-item" href="#">Electronic music</a>
                            <a class="dropdown-item" href="#">Rock</a>
                            <a class="dropdown-item" href="#">Hip Hop</a>
                            <a class="dropdown-item" href="#">Reggae</a>
                            <a class="dropdown-item" href="#">Pop</a>
                        </ul>
                    </li>

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
        <ul class="navbar-nav">
            <li class="nav-item">
                <a href="javascript:void(0)" class=" nav-link " data-toggle="modal" data-target="#myModal"
                   style="border: 0">Sing in</a>
            </li>
            <p class="text-white my-2">|</p>

            <li class="nav-item">
                <a class="nav-link " href="${pageContext.request.contextPath}/registration.jsp">Sing Up</a>
            </li>
        </ul>
    </div>

</nav>
<div class="text-center" style="margin-top:30px;margin-right: 20px">
    <h1>Catalog</h1>
    <br>
</div>
<div class="row col-md-12">
    <div class="col-md-2 border " style="margin-left: 4%; ">
        <div class="form-group">
            <br>
            <label for="sel1">Type:</label>
            <select class="form-control" id="sel1" name="type">
                <option value="All">All</option>
                <option value="Film">Films</option>
                <option value="Game">Games</option>
                <option value="Music">Music</option>
            </select>
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
                $("#price2").on("keyup", function () {
                    var price1 = $("#price").val();
                    var price2 = $("#price2").val();

                    if (price2 === "") {
                        $("#price2").each(function () {
                            this.style.borderColor = '';
                            this.style.boxShadow = "";
                            this.style.webkitBoxShadow = '';
                        });
                        $("#submit1").removeAttr("disabled");  // Разрешаем отправку формы
                        $(".error").html("");
                    } else if (price2 < price1) {

                        $(".error").each(function () {
                            this.style.color = '#b30300';
                        });
                        $("#price2").each(function () {
                            this.style.borderColor = '#b30300';
                            this.style.boxShadow = 'inset 0 1px 1px rgba(0, 0, 0, .075)';
                            this.style.webkitBoxShadow = 'inset 0 1px 1px rgba(0, 0, 0, .075)';

                        });
                        $(".error").html("Цена до не может быть меньше"); // Выводим сообщение
                        $("#submit1").attr("disabled", "disabled"); // Запрещаем отправку формы

                    } else { // Условие, если поля совпадают

                        $("#price2").each(function () {
                            this.style.borderColor = '';
                            this.style.boxShadow = "";
                            this.style.webkitBoxShadow = '';
                        });
                        $("#submit1").removeAttr("disabled");  // Разрешаем отправку формы
                        $(".error").html(""); // Скрываем сообщение

                    }
                });

                $("#price").on("keyup", function () {
                    var price1 = $("#price").val();
                    var price2 = $("#price2").val();
                   
                    if (price2 === "") {
                        return;
                    } else if (price2 < price1) {

                        $(".error").each(function () {
                            this.style.color = '#b30300';
                        });
                        $("#price2").each(function () {
                            this.style.borderColor = '#b30300';
                            this.style.boxShadow = 'inset 0 1px 1px rgba(0, 0, 0, .075)';
                            this.style.webkitBoxShadow = 'inset 0 1px 1px rgba(0, 0, 0, .075)';

                        });
                        $(".error").html("Цена от не может быть больше"); // Выводим сообщение
                        $("#submit1").attr("disabled", "disabled"); // Запрещаем отправку формы

                    } else { // Условие, если поля совпадают

                        $("#price2").each(function () {
                            this.style.borderColor = '';
                            this.style.boxShadow = "";
                            this.style.webkitBoxShadow = '';
                        });
                        $("#submit1").removeAttr("disabled");  // Разрешаем отправку формы
                        $(".error").html(""); // Скрываем сообщение

                    }
                });
            </script>
        </div>
    </div>
    <div class="col-lg-8 ">
        <table class="table border " style="margin-left: 4%">
            <thead>
            <tr>
                <th colspan="3"></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>John</td>
                <td>Doe</td>
                <td>john@example.com</td>
            </tr>
            <tr>
                <td>Mary</td>
                <td>Moe</td>
                <td>mary@example.com</td>
            </tr>
            <tr>
                <td>July</td>
                <td>Dooley</td>
                <td>july@example.com</td>
            </tr>
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
                    <button type="reset" class="btn btn-danger">Reset</button>
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
