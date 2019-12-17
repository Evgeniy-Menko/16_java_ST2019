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
    $(function () {
        $('#reset').on('click', function () {

            $("#unknowLogin").html("");

        });
        $('#login-form').on('submit', function (e) {
            !e.isDefaultPrevented()
            var $form = $(this);
            $.ajax({
                type: $form.attr('method'),
                url: $form.attr('action'),
                data: $form.serialize(),
                success: function (response) {
                    var stringified = JSON.stringify(response);
                    var json = JSON.parse(stringified);

                    if (json['unknowLogin'] != null) {
                        $("#unknowLogin").html("");
                        $("#unknowLogin").append('<span><fmt:message key="unknowLogin"/></span>');
                    } else {
                        window.location.href = json['url'];

                    }

                },
                error: function (request, status, error) {
                    alert("<fmt:message key="errorAjax"/>")
                }

            });

            e.preventDefault();
        });
    });
</script>
<nav class="navbar navbar-expand-sm bg-primary navbar-dark">

    <a class="navbar-brand" href="${pageContext.request.contextPath}/home.html">
        <img src="${pageContext.request.contextPath}/images/905.jpg" alt="logo" style="width:200px;">
    </a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb"
            aria-expanded="true">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="navbar-collapse collapse show" id="navb" style="">
        <ul class="navbar-nav mr-sm-4 ">
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/home.html"><fmt:message
                        key="home"/> </a>
            </li>


            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    <fmt:message key="catalog"/>
                </a>
                <ul class="dropdown-menu">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/search.html?type=0">All</a>
                    <c:set var="type" value="${catalog[0].type}"/>
                    <c:set var="index" value="0"/>

                    <c:forEach var="item" items="${catalog}">

                        <c:if test="${type == item.type && index==0}">

                            <c:set var="index" value="1"/>
                            <li class="dropdown-submenu">
                            <a class="dropdown-item nav-link dropdown-toggle test text-dark" id="navbardrop2"
                               data-toggle="dropdown"
                               tabindex="-1" href="#">${item.type}<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/search.html?type=${item.idType}&genre=0">All</a>
                        </c:if>

                        <c:if test="${type == item.type && index==1}">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/search.html?type=${item.idType}&genre=${item.idGenre}">${item.genre}</a>
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


        </ul>
        <form class="form-inline my-2 my-lg-0 mr-auto">

        </form>
        <ul class="navbar-nav mr-sm-4 ">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="lang" data-toggle="dropdown">
                    <fmt:message key="language"/>
                </a>
                <ul class="dropdown-menu">
                    <a class="dropdown-item" href="?locale=en"><fmt:message key="english"/> </a>
                    <a class="dropdown-item" href="?locale=ru_RU"><fmt:message key="russian"/></a>
                </ul>
            </li>
        </ul>
        <c:choose>

            <c:when test="${authorizedUser.role == 'USER'}">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link
" href="${pageContext.request.contextPath}/profile.html"> <fmt:message key="myAcc"/></a>
                    </li>
                    <p class="text-white my-2">|</p>

                    <li class="nav-item">
                        <a class="nav-link
" href="${pageContext.request.contextPath}/logout.html"><fmt:message key="logout"/></a>
                    </li>
                </ul>
            </c:when>
            <c:when test="${authorizedUser.role == 'ADMINISTRATOR'}">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link
" href="${pageContext.request.contextPath}/complaints.html"><fmt:message key="complaints"/> </a>
                    </li>
                    <p class="text-white my-2">|</p>

                    <li class="nav-item">
                        <a class="nav-link
" href="${pageContext.request.contextPath}/logout.html"><fmt:message key="logout"/></a>
                    </li>
                </ul>

            </c:when>
            <c:otherwise>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="#myModal" class=" nav-link " data-toggle="modal" data-target="#myModal" id="rrr"
                           style="border: 0"><fmt:message key="singIn"/> </a>
                    </li>

                    <p class="text-white my-2">|</p>

                    <li class="nav-item">
                        <a class="nav-link
" href="${pageContext.request.contextPath}/registration.html"><fmt:message key="singUp"/> </a>
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>

    </div>

</nav>
<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title"><fmt:message key="singIn"/></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>

            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <span id="unknowLogin" style="color: #b30300"></span>
                <form id="login-form" action="${pageContext.request.contextPath}/login.html" method="post">
                    <div class="form-group">
                        <label for="email"><fmt:message key="email"/>:</label>
                        <input type="email" class="form-control" id="email" placeholder="<fmt:message key="enterEmail"/>" name="email"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="pwd"><fmt:message key="password"/>: </label>
                        <input type="password" class="form-control" id="pwd" placeholder="<fmt:message key="enterPass"/>"
                               name="password"
                               required>
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message key="submit"/></button>

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
