<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  Date: 2019-10-03
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title></title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/uikit.min.css"/>
	<script src="js/uikit.min.js"></script>
	<script src="js/uikit-icons.min.js"></script>
</head>
<body>
<div class="uk-card uk-card-default">
	<div class="uk-card-header uk-text-uppercase uk-text-muted uk-text-center uk-text-small uk-visible@m">
		<div class="uk-grid-small uk-child-width-1-2 uk-grid" uk-grid>
			<div class="uk-first-column">Product</div>
			<div class="uk-grid-small uk-child-width-expand uk-grid" uk-grid>
				<div class="uk-first-column">price</div>
				<div>quantity</div>
				<div>sum</div>
				<div class="uk-width-auto">
					<div style="width: 20px;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="uk-card-body">

		<c:set var="orderTotal" value="0"/>

		<c:forEach items="${sessionScope.cart}" var="item">
			<c:set var="orderTotal" value="${orderTotal + item.getBook().getPrice() * item.getQty() }"/>
			<div class="uk-grid-small uk-child-width-1-2 uk-grid" uk-grid>
				<div class="uk-first-column">
						<a class="uk-link-heading" href="#">${item.getBook().getTitle()}</a>
				</div>
				<div class="uk-grid-small uk-child-width-1-1 uk-child-width-expand@s uk-text-center uk-grid" uk-grid>
					<div class="uk-first-column">
						<div>$${item.getBook().getPrice()}</div>
					</div>
					<div>${item.getQty()}</div>
					<div> $<c:out value="${item.getBook().getPrice() * item.getQty()}"/> </div>
					<div class="uk-width-auto">
						<div style="width: 20px;">
							<a href="${pageContext.request.contextPath}/cart?&action=remove&id=${item.getBook().getId()}">
								<span uk-icon="minus-circle" class="uk-icon"></span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>

	</div>
</div>
</body>
</html>
