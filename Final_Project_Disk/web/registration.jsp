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
    <script src="https://bootstraptema.ru/snippets/form/2017/recaptcha/validator.js"></script>

    <link href='https://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css'>
    <link href='https://bootstraptema.ru/snippets/form/2017/recaptcha/custom.css' rel='stylesheet' type='text/css'>

    <title>Panda-Disk</title>
    <style>
        .has-error .checkbox, .has-error .checkbox-inline, .has-error .control-label, .has-error .help-block, .has-error .radio, .has-error .radio-inline, .has-error.checkbox label, .has-error.checkbox-inline label, .has-error.radio label, .has-error.radio-inline label {
            color: #ca0000;
        }

        .has-error .form-control {
            border-color: #b30300;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
        }

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


        $(function () {

            $('#contact-form').validator();
            $('#reset').on('click', function () {

                $('#blah').attr('src', "https://winnote.ru/uploads/posts/2016-01/1454222417_del_recent_avatar1.png");
                $(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("Choose file");

            });
            $("#contact-form").on("change", function () {

                var fileName = $(".custom-file-input").val().split("\\").pop();
                var ex = fileName.substring(fileName.lastIndexOf("."));

                $(".custom-file-input").siblings(".custom-file-label").addClass("selected").html(fileName);
                $(".custom-file-input").each(function () {
                    if (ex == ".png" | ex == ".jpg" | ex == ".jpeg") {
                        readURL(this);

                    } else {
                        $('#blah').attr('src', "https://winnote.ru/uploads/posts/2016-01/1454222417_del_recent_avatar1.png");
                        $(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("Choose file");
                        $(".custom-file-input").reset();
                    }
                });

                function readURL(input) {
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();

                        reader.onload = function (e) {
                            $('#blah').attr('src', e.target.result);
                        };

                        reader.readAsDataURL(input.files[0]);
                    }
                }

            });

            $("#contact-form").on("keyup", function () { // Выполняем скрипт при изменении содержимого 2-го поля

                var value_input1 = $(".password").val(); // Получаем содержимое 1-го поля
                var value_input2 = $(".cor_password").val(); // Получаем содержимое 2-го поля

                if (value_input1 != value_input2) { // Условие, если поля не совпадают
                    var pass = document.getElementById('password').value;

                    $(".cor_password").each(function () {
                        this.style.borderColor = '#b30300';
                        this.style.boxShadow = 'inset 0 1px 1px rgba(0, 0, 0, .075)';
                        this.style.webkitBoxShadow = 'inset 0 1px 1px rgba(0, 0, 0, .075)';
                    });
                    $(".error").each(function () {
                        this.style.color = '#b30300';
                    });
                    $(".password").each(function () {
                        this.style.borderColor = '#b30300';
                        this.style.boxShadow = 'inset 0 1px 1px rgba(0, 0, 0, .075)';
                        this.style.webkitBoxShadow = 'inset 0 1px 1px rgba(0, 0, 0, .075)';

                    });
                    $(".error").html("Пароли не совпадают!"); // Выводим сообщение
                    $("#submit1").attr("disabled", "disabled"); // Запрещаем отправку формы

                } else { // Условие, если поля совпадают
                    $(".cor_password").each(function () {
                        this.style.borderColor = '';
                        this.style.boxShadow = '';
                        this.style.webkitBoxShadow = '';
                    });
                    $(".password").each(function () {
                        this.style.borderColor = '';
                        this.style.boxShadow = "";
                        this.style.webkitBoxShadow = '';
                    });
                    $("#submit1").removeAttr("disabled");  // Разрешаем отправку формы
                    $(".error").html(""); // Скрываем сообщение

                }

            });
            $("#contact-form").on("keyup", function () { // Выполняем скрипт при изменении содержимого 2-го поля

                var value_input1 = $(".cor_password").val(); // Получаем содержимое 1-го поля
                var value_input2 = $(".password").val(); // Получаем содержимое 2-го поля

                if (value_input1 != value_input2) { // Условие, если поля не совпадают
                    var pass = document.getElementById('password').value;
                    $(".password").each(function () {
                        this.style.borderColor = '#b30300';
                        this.style.boxShadow = 'inset 0 1px 1px rgba(0, 0, 0, .075)';
                        this.style.webkitBoxShadow = 'inset 0 1px 1px rgba(0, 0, 0, .075)';
                    });

                    $(".error").each(function () {
                        this.style.color = '#b30300';
                    });
                    $(".cor_password").each(function () {
                        this.style.borderColor = '#b30300';
                        this.style.boxShadow = 'inset 0 1px 1px rgba(0, 0, 0, .075)';
                        this.style.webkitBoxShadow = 'inset 0 1px 1px rgba(0, 0, 0, .075)';
                    });
                    $(".error").html("Пароли не совпадают!"); // Выводим сообщение
                    $("#submit1").attr("disabled", "disabled"); // Запрещаем отправку формы

                } else { // Условие, если поля совпадают
                    $(".password").each(function () {
                        this.style.borderColor = '';
                        this.style.boxShadow = '';
                        this.style.webkitBoxShadow = '';
                    });

                    $(".cor_password").each(function () {
                        this.style.borderColor = '';
                        this.style.boxShadow = "";
                        this.style.webkitBoxShadow = '';
                    });
                    $("#submit1").removeAttr("disabled");  // Разрешаем отправку формы
                    $(".error").html(""); // Скрываем сообщение

                }

            });


            $('#contact-form').on('submit', function (e) {
                if (!e.isDefaultPrevented()) {

                    var url = $('#contact-form').attr('action');

                    $.ajax({
                        type: "POST",
                        url: url,
                        data: $(this).serialize(),
                        success: function (data) {
                            location.href = "http://localhost:8080/Panda-Disk/";
                        }
                    });
                    return false;
                }
            })
        });
    </script>
</head>
<body>
<!-- The nav -->
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

<div class="text-center" style="margin-top:30px;margin-right: 20px">
    <h1>Registration Form</h1>
</div>

<div class="container" style="margin-top:30px">

    <form id="contact-form" method="post" action="/Panda-Disk/index.jsp" novalidate="true">

        <div class="messages"></div>


        <div class="row">
            <div class=" col-md-1"></div>
            <div class=" col-md-5">
                <div class="form-group">
                    <div class="card cl-md-6" style="width:350px; height:203px">

                        <img id="blah" class="card-img-top"
                             src="https://winnote.ru/uploads/posts/2016-01/1454222417_del_recent_avatar1.png"
                             alt="your image" style="width:100%;height:100%">

                    </div>
                    <div class="custom-file cl-md-6" style="width:350px;">
                        <label class="custom-file-label" for="customFile" id="image" style="margin: auto">Choose
                            file</label>
                        <input type="file" class="custom-file-input" id="customFile" name="filename"
                               accept="image/jpeg,image/png" data-error="Only jpeg,png format.">
                        <div class="help-block with-errors error2"></div>
                    </div>
                    <div class="help-block with-errors error2"></div>
                </div>
            </div>


            <div class="col-md-5">
                <div class="form-group">
                    <label for="form_name">Firstname *</label>
                    <input id="form_name" type="text" name="name" class="form-control d"
                           placeholder="Please enter your firstname *" required="required"
                           data-error="Firstname is required.">
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <label for="form_lastname">Lastname *</label>
                    <input id="form_lastname" type="text" name="surname" class="form-control d"
                           placeholder="Please enter your lastname *" required="required"
                           data-error="Lastname is required.">
                    <div class="help-block with-errors"></div>
                </div>
                <div class="form-group">
                    <label for="form_phone">Phone</label>
                    <input id="form_phone" type="tel" name="phone" class="form-control"
                           placeholder="Please enter your phone">
                    <div class="help-block with-errors"></div>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-5">

                <div class="form-group">
                    <label for="form_email">Email *</label>
                    <input id="form_email" type="email" name="email" class="form-control"
                           placeholder="Please enter your email *" required="required"
                           data-error="Valid email is required.">
                    <div class="help-block with-errors"></div>
                </div>
            </div>
            <div class="col-md-5">

                <div class="form-group">
                    <label for="form_nickname">Nickname *</label>
                    <input id="form_nickname" type="text" name="nickname" class="form-control"
                           placeholder="Please enter your nickname *" required="required"
                           data-error="Valid nickname is required.">
                    <div class="help-block with-errors"></div>
                </div>

            </div>

        </div>
        <div class="row">
            <div class="col-md-1 "></div>
            <div class="col-md-5">
                <div class="form-group">
                    <label for="password">Password *</label>
                    <input id="password" type="password" name="password" class="form-control password"
                           placeholder="Please enter your password *" required="required"
                           data-error="Valid password is required.">
                    <div class="help-block with-errors error "></div>
                </div>
            </div>
            <div class="col-md-5">
                <div class="form-group">
                    <label for="repeat_password">Repeat password *</label>
                    <input id="repeat_password" type="password" name="password2" class="form-control cor_password"
                           placeholder="Please repeat your password *" required="required"
                           data-error="Valid password is required.">
                    <div class="help-block with-errors error "></div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <button type="submit" class="btn btn-block btn-primary " id="submit1">
                    Submit
                </button>
            </div>
        </div>

        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <button type="reset" class="btn btn-block btn-danger" id="reset">Reset</button>
            </div>
        </div>
    </form>
</div>

</form>
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
<script>
    // Add the following code if you want the name of the file appear on select


</script>
</body>
</html>
