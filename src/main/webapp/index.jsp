
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cake Love | Since 2003</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel=stylesheet href="<c:url value="/css/style.css"/>">
<body>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <div class="top-menu"></div>

    <!-- ----------------------------------------------------------------------- -->
    <!--                                 Navbar                                  -->
    <!-- ----------------------------------------------------------------------- -->
    
    <%@include file="jsp/nav.jsp"%>

    <!-- ----------------------------------------------------------------------- -->
    <!--                                masthead                                 -->
    <!-- ----------------------------------------------------------------------- -->
    <header class="masthead">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12 text-center">
                    <h1 class="font-weight-light">Welcome to Cake Love</h1>
                    <p class="lead">We love cupcakes</p>
                </div>
            </div>
        </div>
    </header>


    <!-- ----------------------------------------------------------------------- -->
    <!--                            Triple Icons                                 -->
    <!-- ----------------------------------------------------------------------- -->

    <section class="tripple-icon">
        <div class="container text-center">
            <div class="row">
                <div class="col-sm">
                    <img src="<c:url value="/images/fastProd.png"/>" alt="Fast Production">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Enim officiis ipsum dolor omnis et,
                        atque explicabo molestiae at quo natus.</p>
                </div>
                <div class="col-sm">
                    <img src="<c:url value="/images/sameDay.png"/>" alt="Same Day Shipping">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt obcaecati quibusdam ut veniam
                        distinctio. Officia quos necessitatibus at sit veritatis.</p>
                </div>
                <div class="col-sm">
                    <img src="<c:url value="/images/freeShip.png"/>" alt="Free Shipping">
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Molestias veritatis ducimus perferendis
                        atque nihil dolore, error expedita officia dolorum unde?</p>
                </div>
            </div>
        </div>
    </section>

    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    <script src="<c:url value="/js/script.js"/>" async></script>
</body>

</html>