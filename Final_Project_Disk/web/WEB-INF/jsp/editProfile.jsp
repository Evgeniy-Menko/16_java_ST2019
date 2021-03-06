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
<c:set var="language"
       value="${not empty param.locale ? param.locale : not empty cookie['lang'].value ? cookie['lang'].value : 'en'}"/>
<fmt:setLocale value="${language}" />
<fmt:bundle basename="text">
    <html lang="${language}">

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

                });
                $("#contact-form").on("change", function () {

                    var fileName = $(".custom-file-input").val().split("\\").pop();
                    var ex = fileName.substring(fileName.lastIndexOf("."));

                    $(".custom-file-input").siblings(".custom-file-label").addClass("selected").html(fileName);
                    $(".custom-file-input").each(function () {
                        if (ex == ".png" | ex == ".jpg" | ex == ".jpeg") {
                            readURL(this);

                        } else {
                            $('#blah').attr('src', "${userInfo.image}");
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
                            }else if (json['unknowPassword'] != null) {

                                $("#errorPasswordOld").append('<span><fmt:message key="unknowPassword"/></span>');
                            } else {

                                window.location.href ="${pageContext.request.contextPath}/profile.html"
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
        <h1 class="display-4">Edit profile</h1>
        <span style="color: #b30300" id="errorLoginNick"></span>
    </div>

    <div class="container" style="margin-top:30px">

        <form id="contact-form" enctype="multipart/form-data" method="post"
              action="${pageContext.request.contextPath}/editProfileResult.html" novalidate="true">

            <div class="messages"></div>


            <div class="row">
                <div class=" col-md-1"></div>
                <div class=" col-md-5">
                    <div class="form-group">
                        <div class="card cl-md-6" style="width:350px; height:203px">

                            <img id="blah" class="card-img-top"
                                 src="${userInfo.image}"
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
                        <label for="form_name"><fmt:message key="firstName"/> *</label>
                        <input id="form_name" type="text" name="name" class="form-control d" value="${userInfo.firstName}"
                               placeholder="Please enter your firstname *" required="required"
                               data-error="<fmt:message key="errorFirstName"/>" pattern="[A-zА-яЁё]*">
                        <div class="help-block with-errors" id="errorFirst" style="color: #b30300;">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="form_lastname"><fmt:message key="LastName"/> *</label>
                        <input id="form_lastname" type="text" name="surname" class="form-control d" value="${userInfo.lastName}"
                               placeholder="Please enter your lastname *" required="required"
                               data-error="<fmt:message key="errorLastName"/>" pattern="[A-zА-яЁё]*">
                        <div class="help-block with-errors" id="errorLast" style="color: #b30300;"></div>
                    </div>
                    <div class="form-group">
                        <label for="form_phone"><fmt:message key="phone"/></label>
                        <input id="form_phone" type="tel" name="phone" class="form-control" value="${userInfo.phone}"
                               placeholder="Please enter your phone"
                        >
                        <div class="help-block with-errors" id="errorPhone" style="color: #b30300;"></div>
                    </div>
                </div>

            </div>

            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-5">

                    <div class="form-group">
                        <label for="passwordOld">Old password *</label>
                        <input id="passwordOld" type="password" name="oldPassword" class="form-control password3"
                               placeholder="Please enter your old password *"
                               data-error="<fmt:message key="errorPassword"/>"   minlength="4" maxlength="20">
                        <div class="help-block with-errors " id="errorPasswordOld" style="color: #b30300;">
                        </div>
                    </div>
                </div>
                <div class="col-md-5">

                    <div class="form-group">
                        <label for="form_nickname"><fmt:message key="nickname"/> *</label>
                        <input id="form_nickname" type="text" name="nickname" class="form-control" value="${userInfo.nickname}"
                               placeholder="Please enter your nickname *" required="required"
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
                               placeholder="Please enter your password *"
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
                               placeholder="Please repeat your password *"
                               data-error="<fmt:message key="errorPassword"/>" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,20}$"
                               minlength="4" maxlength="20">
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


    </body>
    </html>
</fmt:bundle>