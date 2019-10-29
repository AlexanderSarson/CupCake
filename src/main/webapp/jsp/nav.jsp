<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  Date: 2019-09-26
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<title></title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
		  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel=stylesheet href="<c:url value="/css/style.css"/>">
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!-- ----------------------------------------------------------------------- -->
<!--                                 Navbar                                  -->
<!-- ----------------------------------------------------------------------- -->

<nav class="navbar navbar-expand-lg navbar-custom">
	<div class="navbar-collapse collapse w-100 dual-collapse2 order-1 order-md-0">
		<ul class="navbar-nav ml-auto text-center">
			<li class="nav-item active">
				<a class="nav-link" href="${contextPath}/FrontController?&command=redirect&target=index.jsp">Home</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">About us</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">Contact</a>
			</li>
		</ul>
	</div>
	<div class="mx-auto my-2 order-0 order-md-1 position-relative">
		<a class="mx-auto" href="${contextPath}/FrontController?&command=redirect&target=index.jsp">
			<img src="<c:url value="/images/Logo.png"/>" class="rounded-circle">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".dual-collapse2">
			<span class="navbar-toggler-icon"></span>
		</button>
	</div>
	<div class="navbar-collapse collapse w-100 dual-collapse2 order-2 order-md-2">
		<ul class="navbar-nav mr-auto text-center">
			<li class="nav-item">
				<a class="nav-link"
				   href="${contextPath}/FrontController?&command=redirect&target=jsp/shop/mainShop.jsp">Shop</a>
			</li>
			<li class="nav-item cart-icon">
				<a class="nav-link" href="#" data-toggle="modal" data-target="#cart">
                        <span>
                            <i class="fa fa-shopping-cart"></i>
                            Cart
                        </span>
					<span class="badge cart-items">0</span>
				</a>
			</li>
			<li class="nav-item">
				<c:choose>
					<c:when test="${sessionScope.user != null}">
						<div class="dropdown">
							<button class="btn btn-secondary dropdown-toggle" type="button"
									id="dropdownMenu2" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">
								<c:out value="${sessionScope.user.getName()}"/>
							</button>
							<div class="dropdown-menu" aria-labelledby="dropdownMenu2">
								<a class="dropdown-item" href="${contextPath}/FrontController?&command=logout">Logout</a>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<span>
							<a class="nav-link"
							   href="${contextPath}/FrontController?&command=redirect&target=jsp/user/login.jsp">
								<i class="fa fa-user"></i>
								Login
							</a>
						</span>
					</c:otherwise>
				</c:choose>
			</li>
		</ul>
	</div>
</nav>

<script src="<c:url value="/js/shop.js"/>" async></script>
</body>

</html>