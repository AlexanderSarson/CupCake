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
									<th>Order No.</th>
									<th>Order Date</th>
									<th>Total price</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${sessionScope.ordersForUser}" var="order">
									<tr>
										<td> ${order.getId()} </td>
										<td> ${order.getOrderDate()} </td>
										<td> $${order.getTotal()} </td>
									</tr>
								</c:forEach>

								<%-- TODO Placeholders --%>
								<tr>
									<td> 123 </td>
									<td> 01-01-2019 </td>
									<td> $20 </td>
								</tr>
								<tr>
									<td> 123 </td>
									<td> 01-01-2019 </td>
									<td> $20 </td>
								</tr>
								<%-- Placeholders end --%>

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
								<c:out value="${sessionScope.user.getId()}" /> 123 </div>
						</div>
						<div class="uk-grid-small uk-grid">
							<div class="uk-width-1-2@m uk-text-bold uk-text-left"> Name: </div>
							<div class="uk-width-1-2@m">
								<c:out value="${sessionScope.user.getFirstName()}" /> John </div>
						</div>
						<div class="uk-grid-small uk-grid">
							<div class="uk-width-1-2@m uk-text-bold uk-text-left"> Email: </div>
							<div class="uk-width-1-2@m">
								<c:out value="${sessionScope.user.getEmail()}" /> email@nowhere.com </div>
						</div>
					</div>
					<div class="uk-card-footer">
						<div class="uk-grid-small uk-grid">
							<div class="uk-width-1-1@m">
								<button class="uk-button uk-button-primary">Edit Info</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>