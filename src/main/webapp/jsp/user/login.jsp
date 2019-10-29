<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Cake Love | Login</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

  <link rel=stylesheet href="<c:url value="/css/login.css"/>">
</head>

<body>
  <div class="top-menu"></div>

  <!-- ----------------------------------------------------------------------- -->
  <!--                                 Navbar                                  -->
  <!-- ----------------------------------------------------------------------- -->

  <%@include file="../nav.jsp"%>

  <!-- ----------------------------------------------------------------------- -->
  <!--                                  login                                  -->
  <!-- ----------------------------------------------------------------------- -->

  <section class="products mx-auto">
    <section id="store" class="store">
      <div class="container login-container">
        <div class="row">
          <div class="col-md-6 login-form">
            <h3>Login</h3>
            <form name="loginForm" action="<c:url value="/FrontController"/>" method="post">

              <input type="hidden" name="command" value="login">

              <div class="form-group">
                <input class="form-control" id="loginEmail" name="loginEmail" type="email" placeholder="Email *"
                  required />
              </div>

              <div class="form-group">
                <input class="form-control" id="loginPassword" name="loginPassword" type="password"
                  placeholder="Password *" minlength="8" required />
              </div>

              <div class="error-message" id="loginError"></div>
              <div class="form-group">

                <input id="loginBtn" type="submit" class="btnSubmit" value="Login" />
              </div>

              <div class="form-group">
                <a href="#" class="ForgetPwd">Forgot Password?</a>
              </div>
            </form>
          </div>

          <div class="col-md-6 register-form">
            <h3>Create Account</h3>

            <form name="createAccountForm" action="FrontController" method="post" autocomplete="off"
              onsubmit="event.preventDefault(); validateAccountCreation();">

              <input type="hidden" name="command" value="register">

              <div class="form-group">
                <input id="registerName" name="registerName" type="text" class="form-control" placeholder="Name *" />
              </div>

              <div class="form-group">
                <input id="registerEmail" name="registerEmail" type="text" class="form-control" placeholder="Email *" />
              </div>

              <div class="form-group">
                <input id="registerPassword" name="registerPassword" type="password" class="form-control"
                  placeholder="Password *" />
              </div>

              <div class="form-group has-danger">
                <input id="confirmPassword" name="confirmPassword" type="password" class="form-control"
                  placeholder="Confirm Password *" autocomplete="off" />
              </div>

              <div class="error-message" id="registerError"></div>

              <div class="form-group">
                <input type="submit" class="btnSubmit" value="Create Account" />
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
  </section>

  <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
    crossorigin="anonymous"></script>

  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
    crossorigin="anonymous"></script>

  <script src="<c:url value="/js/script.js"/>" async></script>

  <script src="<c:url value="/js/validation.js"/>" async></script>

</body>

</html>