<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
	<title>Customer Details</title>
</head>
<body>
	<%@ include file="/jsp/nav.jsp" %>
	<div class="uk-container">
		<div class="uk-text-center" uk-grid>
			<div class="uk-width-3-5@m">
				<div class="uk-card uk-card-default">
					<div class="uk-card-header">
						<div class="uk-grid-small uk-flex-middle" uk-grid>
							<div class="uk-width-expand">
								<h3 class="uk-card-title uk-margin-remove-bottom">Orders</h3>
							</div>
						</div>
					</div>
					<div class="uk-card-body">
						<table class="uk-table uk-table-middle uk-table-striped uk-table-hover ">
							<thead>
								<tr>
									<th>Order ID</th>
									<th>Order Date</th>
									<th>Order Quantity</th>
									<th>Total price</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${sessionScope.userOrders}" var="order">
									<tr>
										<td> ${order.getId()} </td>
										<td> ${order.getDate()} </td>
										<td> ${order.getTotalQuantity()} </td>
										<td> $${order.getPrice()} </td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="uk-width-2-5@m">
				<div class="uk-card uk-card-default">
					<div class="uk-card-header">
						<div class="uk-grid-small uk-flex-middle" uk-grid>
							<div class="uk-width-expand">
								<h3 class="uk-card-title uk-margin-remove-bottom">Customer Details</h3>
							</div>
						</div>
					</div>
					<div class="uk-card-body">
						<div class="uk-grid-small uk-grid">
							<div class="uk-width-1-2@m uk-text-bold uk-text-left">Customer Number:</div>
							<div class="uk-width-1-2@m">
								<c:out value="${sessionScope.user.getId()}" /></div>
						</div>
						<div class="uk-grid-small uk-grid">
							<div class="uk-width-1-2@m uk-text-bold uk-text-left"> Name: </div>
							<div class="uk-width-1-2@m">
								<c:out value="${sessionScope.user.getName()}" /></div>
						</div>
						<div class="uk-grid-small uk-grid">
							<div class="uk-width-1-2@m uk-text-bold uk-text-left"> Email: </div>
							<div class="uk-width-1-2@m">
								<c:out value="${sessionScope.user.getMail()}" /></div>
						</div>
						<div class="uk-grid-small uk-grid">
							<div class="uk-width-1-2@m uk-text-bold uk-text-left"> Funds: $</div>
							<div class="uk-width-1-2@m">
								<c:out value="${sessionScope.user.getAccount().getBalance()}" /></div>
						</div>
					</div>
					<div class="uk-card-footer">
						<div class="uk-button-group">
							<div class="uk-width-1-1@m">
								<a class="uk-button uk-button-primary" href="${contextPath}/FrontController?&command=redirect&target=jsp/user/editUser.jsp">Edit Info</a>
							</div>
							<div class="uk-width-1-1@m">
								<a class="uk-button uk-button-primary" href="${contextPath}/FrontController?&command=addFundsCommand&value=100">Add $100</a>
							</div>
							<div class="uk-width-1-1@m">
								<a class="uk-button uk-button-primary" href="${contextPath}/FrontController?&command=addFundsCommand&value=200">Add $200</a>
							</div>
						</div>

						<!--<div class="uk-grid-small uk-grid">
						</div>-->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>