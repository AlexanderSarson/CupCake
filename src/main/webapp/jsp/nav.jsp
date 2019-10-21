<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  Date: 2019-09-26
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<title></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/uikit.min.css"/>
	<script src="${pageContext.request.contextPath}/js/uikit.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/uikit-icons.min.js"></script>

</head>

<body>
<nav class="uk-navbar-container uk-sticky">
	<div class="uk-container uk-navbar">
		<div class="nav-overlay uk-navbar-left">
			<a class="uk-navbar-item uk-logo" href="home">Cupcake</a>
			<ul class="uk-navbar-nav">
				<li class="uk-active">
					<a href="${pageContext.request.contextPath}/FrontController?&command=redirect&target=index">Home</a>
				</li>
				<li>
					<a href="#">About us</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/FrontController?&command=redirect&target=shop">Shop</a>
				</li>
				<li>
					<a href="#">Contact</a>
				</li>
			</ul>
		</div>

		<div class="nav-overlay uk-navbar-right">
			<ul class="uk-navbar-nav">
				<li>
					<a uk-icon="user"></a>
					<div uk-dropdown>
						<ul class="uk-nav uk-dropdown-nav">
							<c:choose>
								<c:when test="${sessionScope.user != null}">
									<li>
										<form id="customerPageButton" action="FrontController" method="get">
											<input name="command" value="redirect" type="hidden">
											<input name="target" value="user/showUser.jsp" type="hidden">
											<button class="uk-button uk-button-default uk-button-small" type="submit">Customer Page</button>
										</form>
									</li>
									<li class="uk-nav-divider"></li>
									<li>
										<form id="logoutButton" action="FrontController" method="get">
											<input name="command" value="logout" type="hidden">
											<button class="uk-button uk-button-default uk-button-small" type="submit">Logout</button>
										</form>
									</li>
								</c:when>
								<c:otherwise>
									<form name="loginForm" action="FrontController" method="post" onsubmit="event.preventDefault(); validateLogin();">
										<input type="hidden" name="command" value="login">
										<div class="uk-margin">
											<div class="uk-inline uk-width-1-1">
												<span class="uk-form-icon" uk-icon="icon: mail"></span>
												<input class="uk-input uk-form-large" id="email" type="email" name="email" placeholder="Email" required>
											</div>
										</div>
										<div class="uk-margin">
											<div class="uk-inline uk-width-1-1">
												<span class="uk-form-icon" uk-icon="icon: lock"></span>
												<input class="uk-input uk-form-large" id="password" type="password" name="password" minlength="8" placeholder="Password" required>
											</div>
										</div>
										<div class="uk-margin">
											<button type="submit" class="uk-button uk-button-primary uk-button-large uk-width-1-1">Login</button>
										</div>
										<div class="uk-text-small uk-text-center">
											<c:if test="${requestScope.error != null}">
												<h4><c:out value="${requestScope.error}"/></h4>
											</c:if>
										</div>
										<div class="uk-text-small uk-text-center uk-margin-small-top">
											Not registered? <a href="#">Create an account</a>
										</div>
									</form>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</li>
				<li>
					<a class="uk-navbar-toggle" uk-search-icon uk-toggle="target: .nav-overlay; animation: uk-animation-fade" href="#"></a>
				</li>
				<li>
					<a href="#offcanvas-usage" uk-toggle>
						<span uk-icon="cart" class="uk-icon"></span>
						<c:set var="booksInCart" value="0"/>
						<c:if test="${sessionScope.cart != null}">
							<c:set var="booksInCart" value="${sessionScope.cart.size()}"/>
						</c:if>
						<span class="uk-badge"> <c:out value="${booksInCart}"/> </span>
					</a>
				</li>
			</ul>
		</div>

		<div class="nav-overlay uk-navbar-left uk-flex-1" hidden>
			<div class="uk-navbar-item uk-width-expand">
				<form class="uk-search uk-search-navbar uk-width-1-1" action="search" method="get">
					<input class="uk-search-input" name="search" type="search" placeholder="Search..." autofocus>
				</form>
			</div>
			<a class="uk-navbar-toggle" uk-close uk-toggle="target: .nav-overlay; animation: uk-animation-fade"
			   href="#"></a>
		</div>

		<div id="offcanvas-usage" uk-offcanvas="overlay: true; flip: true">
			<div class="uk-offcanvas-bar">

				<button class="uk-offcanvas-close" type="button" uk-close></button>

				<h3>Cart</h3>

				<c:choose>
					<c:when test="${sessionScope.cart == null || sessionScope.cart.isEmpty()}">
						<h4>Your shopping cart is empty. Start shopping!</h4>
					</c:when>
					<c:when test="${sessionScope.cart != null}">
						<table class="uk-table uk-table-middle uk-table-striped uk-table-hover ">
							<thead>
							<tr>
								<th>Author</th>
								<th>Title</th>
								<th>Price</th>
								<th>Quantity</th>
							</tr>
							</thead>
							<tbody>
							<c:set var="orderTotal" value="0"/>
							<c:forEach items="${sessionScope.cart}" var="item">
								<c:set var="orderTotal" value="${orderTotal + item.getBook().getPrice() * item.getQty() }"/>
								<tr>
									<td>${item.getBook().getAuthor()}</td>
									<td>${item.getBook().getTitle()}</td>
									<td>${item.getBook().getPrice()}</td>
									<td>${item.getQty()}</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>

						<div class="uk-button-group">
							<a href="cart" uk-icon="cart" class="uk-button uk-button-default"> View Cart </a>
							<a href="#" uk-icon="cart" class="uk-button uk-button-primary"> Checkout </a>
						</div>
					</c:when>
				</c:choose>

			</div>
		</div>
	</div>

</nav>
</body>

</html>