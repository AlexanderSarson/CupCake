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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/uikit.min.css"/>
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
			<c:set var="orderTotal" value="0"/>

			<c:forEach items="${sessionScope.order.getOrderItems()}" var="item">
				<c:set var="orderTotal" value="${orderTotal + item.getBook().getPrice() * item.getQty() }"/>

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
								<div> $<c:out value="${item.getBook().getPrice() * item.getQty()}"/> </div>
							</div>
							<div>
								<div>
									<div></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
			</c:forEach>

			<%-- TODO Placeholders --%>
			<div class="uk-child-width-1-2 uk-text-center" uk-grid>
				<div>
					<div class="uk-child-width-1-2 uk-text-center" uk-grid>
						<div>image</div>
						<div>
							<a class="uk-link-heading" href="#">Chocolate</a>
							<div>
								<a class="uk-link-heading uk-text-muted uk-text-small" href="#">Vanilla</a>
							</div>
						</div>
					</div>
				</div>
				<div>
					<div class="uk-child-width-1-4 uk-text-center" uk-grid>
						<div>
							<div>$5</div>
						</div>
						<div>
							<div>2</div>
						</div>
						<div>
							<div> $10 </div>
						</div>
						<div>
							<div>
								<div></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<hr>

			<div class="uk-child-width-1-2 uk-text-center" uk-grid>
				<div>
					<div class="uk-child-width-1-2 uk-text-center" uk-grid>
						<div>image</div>
						<div>
							<a class="uk-link-heading" href="#">Chocolate</a>
							<div>
								<a class="uk-link-heading uk-text-muted uk-text-small" href="#">Vanilla</a>
							</div>
						</div>
					</div>
				</div>
				<div>
					<div class="uk-child-width-1-4 uk-text-center" uk-grid>
						<div>
							<div>$5</div>
						</div>
						<div>
							<div>2</div>
						</div>
						<div>
							<div> $10 </div>
						</div>
						<div>
							<div>
								<div></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%-- Placeholders end --%>

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
							<div></div>
						</div>
						<div>
							<div>
								<a href="${pageContext.request.contextPath}/FrontController?&command=redirect&target=/index"
								   class="uk-button uk-button-primary">Back
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
