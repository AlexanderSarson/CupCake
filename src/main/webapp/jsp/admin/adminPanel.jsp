<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
	<title>Admin Panel</title>
</head>
<body>
	<%@ include file="/jsp/nav.jsp" %>
	<div class="uk-container">
		<div class="uk-text-center" uk-grid>
			<div class="uk-width-2-4@m">
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
								<c:forEach items="${sessionScope.allOrders}" var="order">
									<tr>
										<td> ${order.getId()} </td>
										<td> ${order.getDate()} </td>
										<td> $${order.getPrice()} </td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="uk-width-2-4@m">
				<div class="uk-card uk-card-default">
					<div class="uk-card-header">
						<div class="uk-grid-small uk-flex-middle" uk-grid>
							<div class="uk-width-expand">
								<h3 class="uk-card-title uk-margin-remove-bottom">Users</h3>
							</div>
						</div>
					</div>
					<div class="uk-card-body">
						<table class="uk-table uk-table-middle uk-table-striped uk-table-hover ">
							<thead>
							<tr>
								<th>User No.</th>
								<th>User Name</th>
								<th>User Mail</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${sessionScope.allUsers}" var="user">
								<tr>
									<td> ${user.getId()} </td>
									<td> ${user.getName()} </td>
									<td> ${user.getMail()} </td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>