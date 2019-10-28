<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  Date: 2019-09-27
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
	<title>Shopping cart</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/uikit.min.css" />
	<script src="${pageContext.request.contextPath}/js/uikit.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/uikit-icons.min.js"></script>
</head>

<body>
	<%@ include file="/jsp/nav.jsp" %>

	<div class="uk-container">
		<div class="uk-text-center" uk-grid>
			<div class="uk-width-2-3@m">

				<%@include file="cartProductList.jsp"%>

			</div>
			<div class="uk-width-1-3@m">
				<div class="uk-card uk-card-default">
					<div class="uk-card-header">
						<div class="uk-grid-small uk-flex-middle" uk-grid>
							<div class="uk-width-expand">
								<h3 class="uk-card-title uk-margin-remove-bottom">Order overview</h3>
							</div>
						</div>
					</div>

					<div class="uk-card-body">
						<div class="uk-grid-small uk-grid">
							<div class="uk-width-1-2@m">Subtotal</div>
							<div class="uk-width-1-2@m"> $
								<c:out value="${orderTotal}" />
							</div>
						</div>
						<div class="uk-grid-small uk-grid">
							<div class="uk-width-1-2@m">Discount</div>
							<div class="uk-width-1-2@m"> $0 </div>
						</div>

					</div>
					<div class="uk-card-footer">
						<div class="uk-grid-small uk-grid">
							<div class="uk-width-1-2@m">Total</div>
							<div class="uk-width-1-2@m"> $
								<c:out value="${orderTotal}" />
							</div>
							<div class="uk-width-expand@m">
								<a href="${pageContext.request.contextPath}/FrontController?&command=redirect&target=confirmOrder"
									class="uk-button uk-button-primary uk-button-large uk-margin-medium-top">Checkout
								</a>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>