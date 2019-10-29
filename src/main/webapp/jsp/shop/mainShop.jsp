
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Cake Love | Shop</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel=stylesheet href="<c:url value="/css/mainShop.css"/>">
</head>

<body>
  <div class="top-menu"></div>
  <!-- ----------------------------------------------------------------------- -->
  <!--                                 Navbar                                  -->
  <!-- ----------------------------------------------------------------------- -->

  <%@include file="../nav.jsp"%>

  <!-- ----------------------------------------------------------------------- -->
  <!--                                masthead                                 -->
  <!-- ----------------------------------------------------------------------- -->
  <header class="masthead">
    <div class="container h-100">
      <div class="row h-100 align-items-center">
        <div class="col-12 text-center">
          <h1 class="font-weight-light">Welcome to our store</h1>
          <p class="lead">Check out out frosty selection below!</p>
        </div>
      </div>
    </div>
  </header>

  <!-- ----------------------------------------------------------------------- -->
  <!--                                Products                                 -->
  <!-- ----------------------------------------------------------------------- -->

  <section class="products mx-auto">
    <section id="store" class="store py-5">
      <div class="container">
        <!-- section title -->
        <div class="row">
          <div class="col-10 mx-auto col-sm-6 text-center">
            <h1 class="text-capitalize">our <strong class="banner-title">selection</strong></h1>
          </div>
        </div>
        <!-- end of section title -->

        <!-- store  items-->
        <div class="row" class="store-items" id="store-items">

          <!-- single item -->
          <div class="col-10 col-sm-6 col-lg-4 mx-auto my-3 store-item">
            <div class="card ">
              <div class="img-container">
                <img src="<c:url value="/images/cupcakes/rasberry-choc.jpg"/>" class="card-img-top store-img" alt="">
                <button class="addToCart-Btn" data-topId="1" data-top="Blueberry" data-bottomId="1"
                  data-bottom="Chocolate" data-price="5.0">
                  <i class="fas fa-shopping-cart"></i>
                  Add to cart
                </button>
              </div>
              <div class="card-body">
                <div class="card-text d-flex justify-content-between text-capitalize">
                  <h5 id="store-item-name">Rasberry Chocolate</h5>
                  <h5 class="store-item-value">$ <strong id="store-item-price" class="font-weight-bold">5</strong></h5>
                </div>
              </div>
            </div>
            <!-- end of card-->
          </div>
          <!--end of single store item-->
    </section>

    <!-- ----------------------------------------------------------------------- -->
    <!--                               Cart Modal                                -->
    <!-- ----------------------------------------------------------------------- -->

    <!-- Modal -->
    <div class="modal fade" id="cart" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
      aria-hidden="true">
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Cart</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <table class="show-cart table">

            </table>
            <div>Total price: $<span class="total-cart"></span></div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button id="submitOrder" type="button" class="btn btn-primary">Order now</button>
          </div>
        </div>
      </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"></script>
    <script src="<c:url value="/js/shop.js"/>" async></script>
</body>

</html>