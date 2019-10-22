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
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="uk-navbar-container uk-sticky">
	<div class="uk-container uk-navbar">
		<div class="nav-overlay uk-navbar-left">
			<a class="uk-navbar-item uk-logo" href="${contextPath}/FrontController?&command=redirect&target=index.jsp">Cupcake</a>
			<ul class="uk-navbar-nav">
				<li class="uk-active">
					<a href="${contextPath}/FrontController?&command=redirect&target=index.jsp">Home</a>
				</li>
				<li>
					<a href="#">About us</a>
				</li>
				<li>
					<a href="${contextPath}/FrontController?&command=redirect&target=jsp/shop/mainShop.jsp">Shop</a>
				</li>
				<li>
					<a href="#">Contact</a>
				</li>
			</ul>
		</div>

		<div class="nav-overlay uk-navbar-right">
			<ul class="uk-navbar-nav">
				<li>
					<a uk-icon="user" id="userNav"></a>
					<div uk-dropdown>
						<ul class="uk-nav uk-dropdown-nav">
							<c:choose>
								<c:when test="${sessionScope.user != null}">
									<li>
										<a href="${contextPath}/FrontController?&command=redirect&target=jsp/user/showUser.jsp">My Page</a>
									</li>
									<li class="uk-nav-divider"></li>
									<li>
										<a href="${contextPath}/FrontController?&command=logout">Logout</a>
									</li>
								</c:when>

								<c:otherwise>
									<%@include file="user/loginForm.jsp"%>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</li>
				<li>
					<a href="#offcanvas-usage" uk-toggle>
						<span uk-icon="cart" class="uk-icon"></span>
						<c:set var="productsInCart" value="0"/>
						<c:if test="${sessionScope.cart != null}">
							<c:set var="productsInCart" value="${sessionScope.cart.size()}"/>
						</c:if>
						<span class="uk-badge"> <c:out value="${productsInCart}"/> </span>
					</a>
				</li>
			</ul>
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
							<a href="${contextPath}/FrontController?&command=redirect&target=/cart/showCart" uk-icon="cart" class="uk-button uk-button-default"> View Cart </a>
							<a href="${contextPath}/FrontController?&command=redirect&target=/cart/confirmOrder" uk-icon="cart" class="uk-button uk-button-primary"> Checkout </a>
						</div>

					</c:when>

				</c:choose>

			</div>
		</div>
	</div>

</nav>
</body>

</html>