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
			<a class="uk-navbar-item uk-logo"
			   href="${contextPath}/FrontController?&command=redirect&target=index.jsp">Cupcake</a>
			<ul class="uk-navbar-nav">
				<li class="uk-active">
					<a href="${contextPath}/FrontController?&command=redirect&target=index.jsp">Home</a>
				</li>
				<li>
					<a href="${contextPath}/FrontController?&command=redirect&target=jsp/shop/mainShop.jsp">Shop</a>
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
									<c:choose>
										<c:when test="${sessionScope.user.isAdmin()}">
											<li>
												<a href="${contextPath}/FrontController?&command=showAdminPanel">Admin
													Panel</a>
											</li>
										</c:when>
									</c:choose>
									<li>
										<a href="${contextPath}/FrontController?&command=showUser">My
											Page</a>
									</li>
									<li class="uk-nav-divider"></li>
									<li>
										<a href="${contextPath}/FrontController?&command=logout">Logout</a>
									</li>
								</c:when>
								<c:otherwise>
									<%@include file="user/loginForm.jsp" %>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</li>
				<li>
					<a href="#cart-modal" uk-toggle>
						<span uk-icon="cart" class="uk-icon"></span>
						<c:set var="productsInCart" value="0"/>
						<c:if test="${sessionScope.shoppingCart != null}">
							<c:set var="productsInCart" value="${sessionScope.shoppingCart.getTotalQuantity()}"/>
						</c:if>
						<span class="uk-badge">
								<c:out value="${productsInCart}"/> </span>
					</a>
				</li>
			</ul>
		</div>

		<div id="cart-modal" uk-modal uk-overflow-auto>
			<div class="uk-modal-dialog">
				<button class="uk-modal-close-default" type="button" uk-close></button>
				<div class="uk-modal-header">
					<h2 class="uk-modal-title">Cart</h2>
				</div>

				<c:choose>
					<c:when test="${sessionScope.shoppingCart == null || sessionScope.shoppingCart.getSize() == 0}">
						<div class="uk-modal-body">
							<h4>Wow.. Such emptiness.. Start shopping!</h4>
						</div>
						<div class="uk-modal-footer uk-text-right">
							<button class="uk-button uk-button-default uk-modal-close" type="button">Cancel</button>
							<button class="uk-button uk-button-primary" type="button" disabled>Checkout</button>
						</div>
					</c:when>

					<c:when test="${sessionScope.shoppingCart != null}">
						<c:set var="cartQty" value="0"/>
						<c:set var="cartTotal" value="0"/>


						<div class="uk-modal-body">
							<table class="uk-table uk-table-middle uk-table-striped uk-table-hover ">
								<thead>
								<tr>
									<th>Topping</th>
									<th>Bottom</th>
									<th>Quantity</th>
									<th>Price</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${sessionScope.shoppingCart.getLineItems()}" var="lineItem">
									<c:set var="cartQty" value="${cartQty + lineItem.getQuantity()}"/>
									<c:set var="cartTotal" value="${cartTotal + lineItem.getPrice()}"/>

									<tr>
										<td>${lineItem.getCupcake().getTopping().getName()}</td>
										<td>${lineItem.getCupcake().getBottom().getName()}</td>
										<td>${lineItem.getQuantity()}</td>
										<td>${lineItem.getPrice()}</td>
										<td>
											<a id="modalRemoveFromCartBtn" href="${pageContext.request.contextPath}/FrontController?&command=removeFromCart&topping=${lineItem.getCupcake().getTopping()}&bottom=${lineItem.getCupcake().getBottom()}">
												<span uk-icon="minus-circle" class="uk-icon"></span>
											</a>
										</td>
									</tr>
								</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td></td>
										<td></td>
										<th>${cartQty}</th>
										<th>$${cartTotal}</th>
									</tr>
								</tfoot>
							</table>
						</div>
						<div class="uk-modal-footer uk-text-right">
							<button class="uk-button uk-button-default uk-modal-close" type="button">Close</button>
							<a class="uk-button uk-button-primary" type="button" href="${contextPath}/FrontController?&command=redirect&target=jsp/cart/showCart.jsp">
								Checkout
							</a>
						</div>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>

</nav>
</body>

</html>