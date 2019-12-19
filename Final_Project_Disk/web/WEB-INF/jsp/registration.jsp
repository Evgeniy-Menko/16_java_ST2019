<%--
  Created by IntelliJ IDEA.
  User: Mavil
  Date: 10.11.2019
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

                    $('#blah').attr('src', "images/no.png");
                    $(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("  <fmt:message key="choose"/>");
                    $(".help-block").html("");
                    $("#errorLoginNick").html("");

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
                            $(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("  <fmt:message key="choose"/> ");
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
                        $(".error").html("<fmt:message key="errorRepeatPassword"/>"); // Выводим сообщение
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
                        $(".error").html("<fmt:message key="errorRepeatPassword"/>"); // Выводим сообщение
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
                    !e.isDefaultPrevented()

                    var url = $('#contact-form').attr('action');
                    e.preventDefault(); // делаем отмену действия браузера и формируем ajax
                    var formData = new FormData($('#contact-form')[0]);
                    // данные с формы завернем в переменную для ajax

                    $.ajax({
                        type: "POST",
                        url: url,
                        enctype: 'multipart/form-data',
                        processData: false,
                        contentType: false,
                        cache: false,
                        data: formData,
                        success: function (response) {
                            var stringified = JSON.stringify(response);
                            var json = JSON.parse(stringified);
                            $("#errorLoginNick").html("");
                            $(".help-block").html("");
                            if (json['errorFirstName'] != null) {
                                $("#errorFirst").append('<span><fmt:message key="errorFirstName"/></span>');
                            } else if (json['errorLastName'] != null) {
                                $("#errorLast").append('<span><fmt:message key="errorLastName"/></span>');
                            } else if (json['errorNickname'] != null) {
                                $("#errorNick").append('<span><fmt:message key="errorNickname"/></span>');
                            } else if (json['errorPhone'] != null) {
                                $("#errorPhone").append('<span><fmt:message key="errorPhone"/></span>');
                            } else if (json['errorEmail'] != null) {
                                $("#errorEmail").append('<span><fmt:message key="errorEmail"/></span>');
                            } else if (json['errorPassword'] != null) {
                                $("#errorPassword").append('<span><fmt:message key="errorPassword"/></span>');
                            } else if (json['errorFormatImage'] != null) {
                                $("#errorImage").append('<span><fmt:message key="errorFormatImage"/></span>');
                            } else if (json['errorLoginNick'] != null) {

                                $("#errorLoginNick").append('<span><fmt:message key="errorLoginNick"/></span>');
                            } else {

                               window.location.href ="/Panda-Disk/profile.html"
                            }

                        },
                        error: function (request, status, error) {
                            alert("<fmt:message key="errorAjax"/>")
                        }


                    });

                });
            });
        </script>
    </head>
    <body>
    <%@ include file="menu.jsp" %>
    <div class="text-center" style="margin-top:30px;margin-right: 20px">
        <h1 class="display-4"><fmt:message key="registration"/> </h1>
        <span style="color: #b30300" id="errorLoginNick"></span>
    </div>

    <div class="container" style="margin-top:30px">

        <form id="contact-form" enctype="multipart/form-data" method="post"
              action="${pageContext.request.contextPath}/registr.html" novalidate="true">

            <div class="messages"></div>


            <div class="row">
                <div class=" col-md-1"></div>
                <div class=" col-md-5">
                    <div class="form-group">
                        <div class="card cl-md-6" style="width:350px; height:203px">

                            <img id="blah" class="card-img-top "
                                 src="${pageContext.request.contextPath}/images/no.png"
                                 alt="your image" style="width:100%;height:100%">

                        </div>
                        <div class="custom-file cl-md-6" style="width:350px;">
                            <label class="custom-file-label" for="customFile" id="image" style="margin: auto">
                                <fmt:message key="choose"/> </label>
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
                        <label for="form_name"><fmt:message key="firstName"/> *</label>
                        <input id="form_name" type="text" name="name" class="form-control d"
                               placeholder="<fmt:message key="please"/> <fmt:message key="firstName"/> *" required="required"
                               data-error="<fmt:message key="errorFirstName"/>" pattern="[A-zА-яЁё]*">
                        <div class="help-block with-errors" id="errorFirst" style="color: #b30300;">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="form_lastname"><fmt:message key="LastName"/> *</label>
                        <input id="form_lastname" type="text" name="surname" class="form-control d"
                               placeholder="<fmt:message key="please"/> <fmt:message key="LastName"/> *" required="required"
                               data-error="<fmt:message key="errorLastName"/>" pattern="[A-zА-яЁё]*">
                        <div class="help-block with-errors" id="errorLast" style="color: #b30300;"></div>
                    </div>
                    <div class="form-group">
                        <label for="form_phone"><fmt:message key="phone"/></label>
                        <input id="form_phone" type="tel" name="phone" class="form-control"
                               placeholder="<fmt:message key="please"/> <fmt:message key="phone"/>"
                        >
                        <div class="help-block with-errors" id="errorPhone" style="color: #b30300;"></div>
                    </div>
                </div>

            </div>

            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-5">

                    <div class="form-group">
                        <label for="form_email"><fmt:message key="email"/> *</label>
                        <input id="form_email" type="email" name="email" class="form-control"
                               placeholder="<fmt:message key="please"/> <fmt:message key="email"/> *" required="required"
                               data-error="<fmt:message key="errorEmail"/>">
                        <div class="help-block with-errors" id="errorEmail" style="color: #b30300;"></div>
                    </div>
                </div>
                <div class="col-md-5">

                    <div class="form-group">
                        <label for="form_nickname"><fmt:message key="nickname"/> *</label>
                        <input id="form_nickname" type="text" name="nickname" class="form-control"
                               placeholder="<fmt:message key="please"/> <fmt:message key="nickname"/> *" required="required"
                               data-error="<fmt:message key="errorNickname"/>" pattern="[A-zА-яЁё]*">
                        <div class="help-block with-errors" id="errorNick" style="color: #b30300;"></div>
                    </div>

                </div>

            </div>
            <div class="row">
                <div class="col-md-1 "></div>
                <div class="col-md-5">
                    <div class="form-group">
                        <label for="password"><fmt:message key="password"/> *</label>
                        <input id="password" type="password" name="password" class="form-control password"
                               placeholder="<fmt:message key="please"/> <fmt:message key="password"/> *" required="required"
                               data-error="<fmt:message key="errorPassword"/>" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,20}$"
                               minlength="4" maxlength="20">
                        <div class="help-block with-errors error" id="errorPassword" style="color: #b30300;">
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="form-group">
                        <label for="repeat_password"><fmt:message key="repeatPass"/> *</label>
                        <input id="repeat_password" type="password" name="password2" class="form-control cor_password"
                               placeholder="<fmt:message key="please"/> <fmt:message key="password"/> *" required="required"
                               data-error="<fmt:message key="errorPassword"/>" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,20}$"
                               minlength="6" maxlength="20">
                        <div class="help-block with-errors error "></div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <button type="submit" class="btn btn-block btn-primary " id="submit1">
                        <fmt:message key="submit"/>
                    </button>
                </div>
            </div>

            <div class="row">
                <div class="col-md-3"></div>

            </div>
        </form>
    </div>


    <script>
        // Add the following code if you want the name of the file appear on select


    </script>
    </body>
    </html>
</fmt:bundle>