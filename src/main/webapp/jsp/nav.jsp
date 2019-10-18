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
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/uikit.min.css"/>
	<script src="js/uikit.min.js"></script>
	<script src="js/uikit-icons.min.js"></script>

</head>

<body>
<nav class="uk-navbar-container uk-sticky">

	<div class="uk-container uk-navbar">
		<div class="nav-overlay uk-navbar-left">
			<a class="uk-navbar-item uk-logo" href="home">Cupcake</a>
			<ul class="uk-navbar-nav">
				<li class="uk-active"><a href="home">Home</a></li>
			</ul>
		</div>

		<div class="nav-overlay uk-navbar-right">
			<ul class="uk-navbar-nav">
				<li>
					<a href="viewCustomer" uk-icon="user"></a>
					<div uk-dropdown>
						<ul class="uk-nav uk-dropdown-nav">
							<c:choose>
								<c:when test="${sessionScope.user != null}">
									<li><a href="#">Customer Page</a></li>
									<li class="uk-nav-divider"></li>
									<li><a href="#">Logout</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="#">No User</a></li>
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