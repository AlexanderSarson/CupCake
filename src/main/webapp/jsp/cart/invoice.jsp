<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  Date: 2019-10-05
  Time: 20:49
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
	<%@include file="/jsp/nav.jsp"%>
	<div class="uk-container uk-animation-fade">
		<div class="uk-card uk-card-default">
			<div class="uk-card-header">
				<h3>Order #${sessionScope.order.getId()} Confirmed!</h3>
			</div>

			<div class="uk-card-body">
				<c:set var="orderTotal" value="${order.getPrice()}" />
				<c:forEach items="${sessionScope.order.getLineItems()}" var="item">
					<div class="uk-child-width-1-2 uk-text-center" uk-grid>
						<div>
							<div class="uk-child-width-1-2 uk-text-center" uk-grid>
								<div>
									<a class="uk-link-heading" href="#">${item.getCupcake().getTopping().getName()}</a>
									<a class="uk-link-heading" href="#">${item.getCupcake().getBottom().getName()}</a>
								</div>
							</div>
						</div>
						<div>
							<div class="uk-child-width-1-4 uk-text-center" uk-grid>
								<div>
									<div>$${item.getCupcakePrice()}</div>
								</div>
								<div>
									<div>${item.getQuantity()}</div>
								</div>
								<div>
									<div> $
										<c:out value="${item.getPrice()}" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<hr>
				</c:forEach>
			</div>
			<div class="uk-card-footer">
				<div class="uk-child-width-1-2 uk-text-center" uk-grid>
					<div>
						<div class="uk-child-width-1-2 uk-text-center" uk-grid>
							<div>
								<a class="uk-link-heading" href="#">Order subtotal:</a>
							</div>
						</div>
					</div>
					<div>
						<div class="uk-child-width-1-4 uk-text-center" uk-grid>
							<div>$
								${order.getPrice()}
							</div>
						</div>
					</div>
				</div>
				<div class="uk-child-width-1-2 uk-text-center" uk-grid>
					<div>
						<div class="uk-child-width-1-2 uk-text-center" uk-grid>
							<div>
								<a class="uk-link-heading" href="#">Order Discount:</a>
							</div>
						</div>
					</div>
					<div>
						<div class="uk-child-width-1-4 uk-text-center" uk-grid>
							<div>
								0.0 %
							</div>
						</div>
					</div>
				</div>
				<div class="uk-child-width-1-2 uk-text-center" uk-grid>
					<div>
						<div class="uk-child-width-1-2 uk-text-center" uk-grid>
							<div>
								<a class="uk-link-heading" href="#">Order Total:</a>
							</div>
						</div>
					</div>
					<div>
						<div class="uk-child-width-1-4 uk-text-center" uk-grid>
							<div>$
								${order.getPrice()}
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>