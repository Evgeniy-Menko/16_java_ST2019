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
<fmt:setLocale value="en_US"/>
<fmt:bundle basename="text">
    <html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">

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
                color: #ca0000;
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

                    $(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("Choose file");
                    $(".help-block").html("");
                    $("#errorLoginNick").html("");
                    $('#blah').attr('src', "images/no.png");

                });
                $("#contact-form").on("change", function () {

                    var fileName = $(".custom-file-input").val().split("\\").pop();
                    var ex = fileName.substring(fileName.lastIndexOf("."));

                    $(".custom-file-input").siblings(".custom-file-label").addClass("selected").html(fileName);
                    $(".custom-file-input").each(function () {
                        if (ex == ".png" | ex == ".jpg" | ex == ".jpeg") {
                            readURL(this);

                        } else {
                            $('#blah').attr('src', "images/no.png");
                            $(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("Choose file");

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
            });</script>
    </head>
    <body>
    <%@ include file="menu.jsp" %>
    <div class="text-center" style="margin-top:30px;margin-right: 20px">
        <h1 class="display-4">Add announcement</h1>
        <span style="color: #b30300" id="errorLoginNick"></span>
    </div>
    <div class="container" style="margin-top:30px">

        <form id="contact-form" enctype="multipart/form-data" method="post"
              action="${pageContext.request.contextPath}/" novalidate="true">

            <div class="messages"></div>


            <div class="row">
                <div class=" col-md-1"></div>
                <div class=" col-md-5">
                    <div class="form-group">
                        <div class="card cl-md-6" style="width:350px; height:203px">

                            <img id="blah" class="card-img-top"
                                 src="images/no.png"
                                 alt="your image" style="width:100%;height:100%">

                        </div>
                        <div class="custom-file cl-md-6" style="width:350px;">
                            <label class="custom-file-label" for="customFile" id="image" style="margin: auto">Choose
                                file</label>
                            <input type="file" class="custom-file-input" id="customFile" name="image"
                                   accept="image/jpeg,image/png,/image/gif,image/jpg"
                                   data-error="<fmt:message key="errorFormatImage"/>">
                            <div class="help-block with-errors error2 has-error"></div>
                        </div>
                        <div class="help-block with-errors " id="errorImage" style="color: #b30300;"></div>
                    </div>
                </div>


                <div class="col-md-5">
                    <div class="form-group">
                        <label for="form_name">Name disk *</label>
                        <input id="form_name" type="text" name="name" class="form-control d"
                               placeholder="Please enter your firstname *" required="required"
                               data-error="<fmt:message key="errorFirstName"/>">
                        <div class="help-block with-errors" id="errorNameDisk" style="color: #b30300;">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sel1">Type:</label>
                        <select class="form-control" id="sel1" name="type" >
                            <c:set var="ind" value="0"/>
                            <c:set var="types" value="${firstType}"/>
                            <c:forEach var="i" items="${catalog}">
                                <c:if test="${types== i.type && ind ==0}">
                                    <c:set var="ind" value="1"/>
                                    <option value="${i.type}">${i.type}</option>
                                </c:if>
                                <c:if test="${types != i.type && ind ==1}">
                                    <c:set var="types" value="${i.type}"/>
                                    <option value="${i.type}">${i.type}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        <script>
                            $("#sel1").on("change", function () {
                                var type = $('#sel1').val();
                                var select2 = $('#sel2');
                                select2.empty();
                                <c:forEach var="item" items="${catalog}">
                                if (type === "${item.type}") {
                                   
                                    select2.append('<option value="${item.idGenre}"> ${item.genre}</option>');
                                }
                                </c:forEach>
                            });

                        </script>
                    </div>
                    <div class="form-group">
                        <label for="sel2">Genre:</label>
                        <select class="form-control" id="sel2" name="genre">

                            <c:set var="typess" value="${firstType}"/>
                            <c:forEach var="item" items="${catalog}">
                                <c:if test="${typess == item.type}">
                                    <option value="${item.idGenre}"> ${item.genre}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>

                </div>
            </div>


            <div class="row ">
                <div class="col-md-1"></div>
                <div class="col-md-5">

                    <div class="form-group">
                        <label for="price">Price *</label>
                        <input id="price" type="number" name="price" class="form-control price"
                               placeholder="Please enter price *" required
                               data-error="Required"
                        >
                        <div class="help-block with-errors " id="errorPrice" style="color: #b30300;"></div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="form-group">
                        <label for="inputYear">Year *</label>
                        <input id="inputYear" type="number" name="year" class="form-control"
                               placeholder="Please enter year *" required
                               data-error="<fmt:message key="errorNickname"/>">
                        <div class="help-block with-errors" id="errorNick" style="color: #b30300;"></div>
                        <script>
                            inputYear.max = new Date().getFullYear();
                        </script>
                    </div>

                </div>

            </div>

            <div class="row">

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

    </body>
    </html>
</fmt:bundle>