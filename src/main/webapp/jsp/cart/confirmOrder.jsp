<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  Date: 2019-10-05
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
	<title>Confirm Order</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/uikit.min.css" />
	<script src="${pageContext.request.contextPath}/js/uikit.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/uikit-icons.min.js"></script>
</head>

<body>
	<%@include file="/jsp/nav.jsp"%>

	<div class="uk-container">
		<div class="uk-card uk-card-default">
			<div class="uk-card-header">
				<h3>Please confirm your order</h3>
			</div>

			<div class="uk-card-body">
				<c:set var="orderTotal" value="${order.getPrice()}" />
				<c:forEach items="${sessionScope.order.getOrderItems()}" var="item">
					<div class="uk-child-width-1-2 uk-text-center" uk-grid>
						<div>
							<div class="uk-child-width-1-2 uk-text-center" uk-grid>
								<div>image</div>
								<div>
									<a class="uk-link-heading" href="#">${item.getBook().getTitle()}</a>
									<div>
										<a class="uk-link-heading uk-text-muted uk-text-small" href="#">by ${item.getBook().getAuthor()}</a>
									</div>
								</div>
							</div>
						</div>
						<div>
							<div class="uk-child-width-1-4 uk-text-center" uk-grid>
								<div>
									<div>$${item.getBook().getPrice()}</div>
								</div>
								<div>
									<div>${item.getQty()}</div>
								</div>
								<div>
									<div> $
										<c:out value="${item.getBook().getPrice() * item.getQty()}" />
									</div>
								</div>
								<div>
									<div>
										<a
											href="${pageContext.request.contextPath}/FrontController?&command=removeFromCart&topping=${item.getTopping}&bottom=${item.getBottom}">
											<span uk-icon="minus-circle" class="uk-icon"></span>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

				<%-- TODO Placeholders --%>
				<div class="uk-child-width-1-2 uk-text-center" uk-grid>
					<div>
						<div class="uk-child-width-1-2 uk-text-center" uk-grid>
							<div>image</div>
							<div>
								<a class="uk-link-heading" href="#">Vanilla</a>
								<div>
									<a class="uk-link-heading uk-text-muted uk-text-small" href="#">Chocolate topping</a>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="uk-child-width-1-4 uk-text-center" uk-grid>
							<div>
								<div>$20</div>
							</div>
							<div>
								<div>1</div>
							</div>
							<div>
								<div> $20 </div>
							</div>
							<div>
								<div>
									<a href="${pageContext.request.contextPath}/checkout?&action=deleteItem&id=${item.getBook().getId()}">
										<span uk-icon="minus-circle" class="uk-icon"></span>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="uk-child-width-1-2 uk-text-center" uk-grid>
					<div>
						<div class="uk-child-width-1-2 uk-text-center" uk-grid>
							<div>image</div>
							<div>
								<a class="uk-link-heading" href="#">Chocolate</a>
								<div>
									<a class="uk-link-heading uk-text-muted uk-text-small" href="#">Vanilla topping</a>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="uk-child-width-1-4 uk-text-center" uk-grid>
							<div>
								<div>$20</div>
							</div>
							<div>
								<div>1</div>
							</div>
							<div>
								<div> $20 </div>
							</div>
							<div>
								<div>
									<a href="${pageContext.request.contextPath}/checkout?&action=deleteItem&id=${item.getBook().getId()}">
										<span uk-icon="minus-circle" class="uk-icon"></span>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%-- Placeholders end--%>

			</div>
			<div class="uk-card-footer">
				<div class="uk-child-width-1-2 uk-text-center" uk-grid>
					<div>
						<div class="uk-child-width-1-2 uk-text-center" uk-grid>
							<div></div>
							<div>
								<div>
								</div>
							</div>
						</div>
					</div>
					<div>
						<div class="uk-child-width-1-4 uk-text-center" uk-grid>
							<div>
								<div></div>
							</div>
							<div>
								<div></div>
							</div>
							<div>
								<div>$40</div>
							</div>
							<div>
								<div>
									<a href="${pageContext.request.contextPath}/checkout?&action=orderConfirmed"
										class="uk-button uk-button-primary">Confirm
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>