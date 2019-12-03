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

                        window.location.href = "/Panda-Disk/home.html"
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
        <img src="images/905.jpg" alt="logo" style="width:200px;">
    </a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb"
            aria-expanded="true">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="navbar-collapse collapse show" id="navb" style="">
        <ul class="navbar-nav mr-sm-4 ">
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/home.html">Home</a>
            </li>


            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    Catalog
                </a>
                <ul class="dropdown-menu">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/search.html?type=0">All</a>
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

            <input class="form-control mr-sm-1" type="text" placeholder="Search">

            <button class="btn btn-light my-1 mr-sm-1" type="button">Search</button>
        </form>
        <c:choose>

            <c:when test="${authorizedUser.role == 'USER'}">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link
" href="${pageContext.request.contextPath}/profile.html"> My profile</a>
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
                        <a href="#myModal" class=" nav-link " data-toggle="modal" data-target="#myModal" id="rrr"
                           style="border: 0">Sing in</a>
                    </li>

                    <p class="text-white my-2">|</p>

                    <li class="nav-item">
                        <a class="nav-link
" href="${pageContext.request.contextPath}/registration.html">Sing Up</a>
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
                <h4 class="modal-title">Login form</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>

            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <span id="unknowLogin" style="color: #b30300"></span>
                <form id="login-form" action="${pageContext.request.contextPath}/login.html" method="post">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="pwd">Password:</label>
                        <input type="password" class="form-control" id="pwd" placeholder="Enter password"
                               name="password"
                               required>
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
