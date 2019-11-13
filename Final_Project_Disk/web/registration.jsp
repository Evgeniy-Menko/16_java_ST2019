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

            $('#contact-form').on('submit', function (e) {
                if (!e.isDefaultPrevented()) {
                    var url = $('#contact-form').attr('action');

                    $.ajax({
                        type: "POST",
                        url: url,
                        data: $(this).serialize(),
                        success: function (data) {
                       location.href="http://localhost:8080/Panda-Disk/";
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

    <form id="contact-form" method="post" action="${pageContext.request.contextPath}/index.jsp">

        <div class="messages"></div>

        <div class="controls">

            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="form_name">Firstname *</label>
                        <input id="form_name" type="text" name="name" class="form-control"
                               placeholder="Please enter your firstname *" required="required"
                               data-error="Firstname is required.">
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="form_lastname">Lastname *</label>
                        <input id="form_lastname" type="text" name="surname" class="form-control"
                               placeholder="Please enter your lastname *" required="required"
                               data-error="Lastname is required.">
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
            </div>

            <div class="row">

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="form_email">Email *</label>
                        <input id="form_email" type="email" name="email" class="form-control"
                               placeholder="Please enter your email *" required="required"
                               data-error="Valid email is required.">
                        <div class="help-block with-errors"></div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="form_phone">Phone</label>
                        <input id="form_phone" type="tel" name="phone" class="form-control"
                               placeholder="Please enter your phone">
                        <div class="help-block with-errors"></div>
                    </div>
                </div>
            </div>


            <div class="col-md-12">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>

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
</body>
</html>
