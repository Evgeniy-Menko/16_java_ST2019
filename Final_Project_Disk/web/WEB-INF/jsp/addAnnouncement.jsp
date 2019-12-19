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
<fmt:setLocale value="${language}"/>
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

                $('#contact-form').on('submit', function (e) {
                    !e.isDefaultPrevented();

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
                            $("#errorValue").html("");
                            $(".help-block").html("");
                            if (json['errorCountry'] != null) {
                                $("#errorCountry").append('<span><fmt:message key="errorCountry"/></span>');
                            } else if (json['errorTime'] != null) {
                                $("#errorTime").append('<span><fmt:message key="errorTime"/></span>');
                            } else if (json['errorFormatImage'] != null) {
                                $("#errorImage").append('<span><fmt:message key="errorFormatImage"/></span>');
                            } else if (json['errorYear'] != null) {
                                $("#errorYear").append('<span><fmt:message key="errorYear"/></span>');
                            } else if (json['errorAge'] != null) {
                                $("#errorAge").append('<span><fmt:message key="errorAge"/></span>');
                            } else if (json['errorNameDisk'] != null) {
                                $("#errorNameDisk").append('<span><fmt:message key="errorNameDisk"/></span>');
                            } else if (json['errorFormatImage'] != null) {
                                $("#errorImage").append('<span><fmt:message key="errorFormatImage"/></span>');
                            } else if (json['errorFormatImage'] != null) {
                                $("#errorImage").append('<span><fmt:message key="errorFormatImage"/></span>');
                            } else if (json['errorRequired'] != null) {
                                $("#errorPrice").append('<span><fmt:message key="errorRequired"/></span>');
                            } else if (json['incorrectNumber'] != null) {
                                $("#errorValue").append('<span><fmt:message key="incorrectNumber"/></span>');
                            } else if (json['incorrectComment'] != null) {
                                $("#incorrectComment").append('<span><fmt:message key="incorrectComment"/></span>');
                            } else {
                                window.location.href = "/Panda-Disk/myAnnouncements.html"
                            }


                        },
                        error: function (request, status, error) {
                            alert("<fmt:message key="errorAjax"/>")
                        }


                    });

                });
            });</script>
    </head>
    <body>
    <%@ include file="menu.jsp" %>
    <div class="text-center" style="margin-top:30px;margin-right: 20px">
        <h1 class="display-4">Add announcement</h1>
        <span style="color: #b30300" id="errorValue"></span>
    </div>
    <div class="container" style="margin-top:30px">

        <form id="contact-form" enctype="multipart/form-data" method="post"
              action="${pageContext.request.contextPath}/announcementResult.html" novalidate="true">

            <div class="messages"></div>


            <div class="row">
                <div class=" col-md-1"></div>
                <div class=" col-md-5">
                    <div class="form-group">
                        <div class="card cl-md-6" style="width:350px; height:203px">

                            <img id="blah" class="card-img-top"
                                 src="${pageContext.request.contextPath}/images/no.png"
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
                               placeholder="Please enter your name disk *" required="required"
                               data-error="<fmt:message key="errorNameDisk"/>" pattern="^(?!\s*$)[A-zА-яЁё0-9\S_ ]*$">
                        <div class="help-block with-errors" id="errorNameDisk" style="color: #b30300;">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sel1">Type:</label>
                        <select class="form-control" id="sel1" name="type" required>
                            <c:set var="ind" value="0"/>
                            <c:set var="types" value="${catalog[0].type}"/>
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
                                var film = $("#Music");
                                var game = $("#Game");
                                var music = $("#Film");

                                select2.empty();
                                <c:forEach var="item" items="${catalog}">
                                if (type === "${item.type}") {
                                    select2.append('<option value="${item.genre}"> ${item.genre}</option>');
                                }
                                </c:forEach>
                                film.each(function () {
                                    this.style.display = "none";
                                })
                                game.each(function () {
                                    this.style.display = "none";
                                })
                                music.each(function () {
                                    this.style.display = "none";
                                })
                                if (type === "Film") {
                                    $("#Film").each(function () {

                                        this.style.display = "block";
                                    });
                                }
                                if (type === "Game") {

                                    $("#Game").each(function () {
                                        this.style.display = "block";
                                    });
                                }
                                if (type === "Music") {
                                    $("#Music").each(function () {
                                        this.style.display = "block";
                                    });
                                }
                            });

                        </script>
                    </div>
                    <div class="form-group">
                        <label for="sel2">Genre:</label>
                        <select class="form-control" id="sel2" name="genre" required>

                            <c:set var="typess" value="${catalog[0].type}"/>
                            <c:forEach var="item" items="${catalog}">
                                <c:if test="${typess == item.type}">
                                    <option value="${item.genre}"> ${item.genre}</option>
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
                        <input id="price" type="number" name="price" class="form-control price" step="0.01" min="0"
                               placeholder="Please enter price *" required
                               data-error="<fmt:message key="errorRequired"/>"
                        >
                        <div class="help-block with-errors " id="errorPrice" style="color: #b30300;"></div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="form-group">
                        <label for="inputYear">Year </label>
                        <input id="inputYear" type="number" name="year" class="form-control"
                               placeholder="Please enter year "
                               data-error="<fmt:message key="incorrectNumber"/>">
                        <div class="help-block with-errors" id="errorYear" style="color: #b30300;"></div>
                        <script>
                            inputYear.max = new Date().getFullYear();
                        </script>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class=" col-md-1"></div>
                <div class="form-group col-md-10">
                    <label for="comment">Description:</label>
                    <textarea class="form-control" rows="5" id="comment" title="Only leters,number and ,.!@#?:()"
                              name="comment"></textarea>
                    <div class="help-block with-errors " id="incorrectComment" style="color: #b30300;"></div>
                </div>
                <div class=" col-md-1"></div>
                <div class=" col-md-1"></div>
                <div class="form group col-md-10" id="Film">
                    <div class="form-group ">
                        <label for="country">Country </label>
                        <input id="country" type="text" name="country" class="form-control country"
                               placeholder="Please enter country " pattern="[A-zА-яЁё]*"
                               data-error="<fmt:message key="errorCountry"/>"
                        >
                        <div class="help-block with-errors " id="errorCountry" style="color: #b30300;"></div>
                    </div>


                    <div class="form-group">
                        <label for="time">Running time</label>
                        <input id="time" type="text" name="time" class="form-control"
                               placeholder="Please enter running time(hh:mm) *" pattern="[0-9:0-9]{4,5}"
                               data-error="<fmt:message key="errorTime"/>">
                        <div class="help-block with-errors" id="errorTime" style="color: #b30300;"></div>
                    </div>
                </div>

                <div class="form group col-md-10" id="Game" style="display: none">
                    <div class="form-group">
                        <label for="age">Age limit </label>
                        <input id="age" type="number" name="age" class="form-control country"
                               placeholder="Please enter age limit *" min="0" max="18"
                               data-error="<fmt:message key="errorAge"/>"
                        >
                        <div class="help-block with-errors " id="errorAge" style="color: #b30300;"></div>
                    </div>


                    <div class="form-group">
                        <label for="developer">Developer</label>
                        <input id="developer" type="text" name="developer" class="form-control"
                               placeholder="Please enter developer's name " pattern="[A-zА-яЁё]*"
                               data-error="">
                        <div class="help-block with-errors" id="errorDeveloper" style="color: #b30300;"></div>
                    </div>

                </div>

                <div class="form group col-md-10" id="Music" style="display: none">
                    <div class="form-group">
                        <label for="singer">Singer </label>
                        <input id="singer" type="text" name="singer" class="form-control singer"
                               placeholder="Please enter singer " pattern="[A-zА-яЁё]*"
                               data-error=""
                        >
                        <div class="help-block with-errors " id="errorSinger" style="color: #b30300;"></div>
                    </div>

                    <div class="form-group">
                        <label for="albom">Albom</label>
                        <input id="albom" type="text" name="albom" class="form-control albom"
                               placeholder="Please enter albom's name " pattern="[A-zА-яЁё]*"
                               data-error="">
                        <div class="help-block with-errors" id="errorAlbom" style="color: #b30300;"></div>
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