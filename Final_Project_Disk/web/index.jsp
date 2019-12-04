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
        <h1 class="display-3">Welcome to the PANDA! </h1>
        <h5 class="display-5">Here you can find products for every taste!</h5 >
    </div>
    <br>
    <div class="container" style="margin-top:30px">
        <div class="row">

            <div class="col-sm-4">
                <h2 class="display-4">Games</h2>
                <h5 class="display-5">For all console and PC:</h5>

                <div id="myCarousel" class="carousel slide" data-ride="carousel">

                    <!-- Indicators -->
                    <ul class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                        <li data-target="#myCarousel" data-slide-to="3"></li>
                        <li data-target="#myCarousel" data-slide-to="4"></li>
                        <li data-target="#myCarousel" data-slide-to="5"></li>
                        <li data-target="#myCarousel" data-slide-to="6"></li>
                    </ul>

                    <!-- The slideshow -->
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="images/game1.jpg" alt="1" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/game2.jpg" alt="2" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/game3.png" alt="3" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/game4.png" alt="4" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/game5.png" alt="5" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/game6.jpg" alt="6" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/game7.png" alt="7" width="350" height="500">
                        </div>
                    </div>

                    <!-- Left and right controls -->
                    <a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                    </a>
                    <a class="carousel-control-next" href="#myCarousel" data-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </a>
                </div>


                <hr class="d-sm-none">
            </div>

            <div class="col-sm-4">
                <h2 class="display-4">Films</h2>
                <h5 class="display-5">Any genre:</h5>

                <div id="myCarousel2" class="carousel slide" data-ride="carousel">

                    <!-- Indicators -->
                    <ul class="carousel-indicators">
                        <li data-target="#myCarousel2" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel2" data-slide-to="1"></li>
                        <li data-target="#myCarousel2" data-slide-to="2"></li>
                        <li data-target="#myCarousel2" data-slide-to="3"></li>
                        <li data-target="#myCarousel2" data-slide-to="4"></li>
                        <li data-target="#myCarousel2" data-slide-to="5"></li>
                        <li data-target="#myCarousel2" data-slide-to="6"></li>
                    </ul>

                    <!-- The slideshow -->
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="images/film-1.jpg" alt="Los Angeles" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/film-2.jpg" alt="Chicago" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/film-3.jpg" alt="New York" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/film-11.jpg" alt="New York" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/film-12.jpg" alt="New York" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/film-13.jpg" alt="New York" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/film-14.jpg" alt="New York" width="350" height="500">
                        </div>
                    </div>

                    <!-- Left and right controls -->
                    <a class="carousel-control-prev" href="#myCarousel2" data-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                    </a>
                    <a class="carousel-control-next" href="#myCarousel2" data-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </a>
                </div>


                <hr class="d-sm-none">
            </div>


            <div class="col-sm-4">
                <h4 class="display-4">Music</h4>
                <h5 class="display-5"> Any singer:</h5>

                <div id="myCarousel1" class="carousel slide" data-ride="carousel">

                    <!-- Indicators -->
                    <ul class="carousel-indicators">
                        <li data-target="#myCarousel1" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel1" data-slide-to="1"></li>
                        <li data-target="#myCarousel1" data-slide-to="2"></li>
                        <li data-target="#myCarousel1" data-slide-to="3"></li>
                        <li data-target="#myCarousel1" data-slide-to="4"></li>
                        <li data-target="#myCarousel1" data-slide-to="5"></li>
                        <li data-target="#myCarousel1" data-slide-to="6"></li>
                    </ul>

                    <!-- The slideshow -->

                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="images/music1.jpg" alt="Los Angeles" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/music2.jpg" alt="Chicago" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/music3.jpg" alt="New York" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/music4.jpg" alt="New York" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/music5.jpg" alt="New York" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/music6.jpg" alt="New York" width="350" height="500">
                        </div>
                        <div class="carousel-item">
                            <img src="images/music7.jpg" alt="New York" width="350" height="500">
                        </div>
                    </div>


                    <!-- Left and right controls -->
                    <a class="carousel-control-prev" href="#myCarousel1" data-slide="prev">
                        <span class="carousel-control-prev-icon"></span>
                    </a>
                    <a class="carousel-control-next" href="#myCarousel1" data-slide="next">
                        <span class="carousel-control-next-icon"></span>
                    </a>
                </div>


                <hr class="d-sm-none">
            </div>


        </div>
    </div>

    </body>
    </html>
</fmt:bundle>