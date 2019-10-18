<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  Date: 2019-09-27
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Search Result</title>
</head>
<body>
	<%@ include file="/jsp/nav.jsp" %>

	<div class="uk-container">
		<table class="uk-table uk-table-middle uk-table-striped uk-table-hover">
			<thead>
			<tr>
				<th>Author</th>
				<th>Title</th>
				<th>Price</th>
				<th>In Stock</th>
				<th>Order</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${requestScope.books}" var = "book">
				<tr>
					<td>${book.getAuthor()}</td>
					<td>${book.getTitle()}</td>
					<td>${book.getPrice()}</td>
					<td>${book.getInStock()}</td>
					<td class="uk-table-middle">
						<a class="uk-button uk-button-default" href="${pageContext.request.contextPath}/cart?&action=buy&id=${book.getId()}">
							<span uk-icon="plus-circle" class="uk-icon"></span>
							<span>Add to Cart</span>
						</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>
