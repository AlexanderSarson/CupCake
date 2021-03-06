<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/uikit.min.css" />
	<script src="${pageContext.request.contextPath}/js/uikit.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/uikit-icons.min.js"></script>
</head>

<body>
	<div class="uk-card uk-card-default">
		<div class="uk-card-header uk-text-uppercase uk-text-muted uk-text-center uk-text-small uk-visible@m">
			<div class="uk-grid-small uk-child-width-expand uk-grid" uk-grid>
				<div class="uk-first-column">Topping</div>
				<div>Bottom</div>
				<div>Price</div>
				<div>quantity</div>
				<div>sum</div>
				<div class="uk-width-auto">
					<div style="width: 20px;"></div>
				</div>
			</div>
		</div>
		<div class="uk-card-body">
			<c:set var="orderTotal" value="${sessionScope.shoppingCart.getPrice()}" />
			<c:forEach items="${sessionScope.shoppingCart.getLineItems()}" var="lineItem">
				<div class="uk-grid-small uk-child-width-expand uk-grid" uk-grid>
					<div class="uk-first-column">${lineItem.getCupcake().getTopping().getName()}</div>
					<div>${lineItem.getCupcake().getBottom().getName()}</div>
					<div>$${lineItem.getCupcake().getPrice()}</div>
					<div>${lineItem.getQuantity()}</div>
					<div>$${lineItem.getPrice()}</div>
					<div class="uk-width-auto">
						<div style="width: 20px;">
							<a
								href="${pageContext.request.contextPath}/FrontController?&command=removeFromCart&topping=${lineItem.getCupcake().getTopping()}&bottom=${lineItem.getCupcake().getBottom()}">
								<span uk-icon="minus-circle" class="uk-icon"></span>
							</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>

</html>