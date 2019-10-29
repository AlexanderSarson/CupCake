<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  Date: 2019-09-26
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/uikit.min.css" />
    <script src="${pageContext.request.contextPath}/js/uikit.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/uikit-icons.min.js"></script>

</head>

<body>
    <%@ include file="/jsp/nav.jsp" %>

    <div class="uk-container">
        <thead> Custom Cupcakes
        </thead>
        <tbody>
            <div class="uk-margin">
                <label class="uk-form-label" for="customTopping">Topping:</label>
                <div class="uk-form-controls">
                    <select class="uk-select" id="customTopping">
                        <c:forEach items="${applicationScope.toppings}" var="topping">
                            <option>${topping.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="uk-margin">
                <label class="uk-form-label" for="customTopping">Bottom:</label>
                <div class="uk-form-controls">
                    <select class="uk-select" id="customBottom">
                        <c:forEach items="${applicationScope.bottoms}" var="topping">
                            <option>${topping.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </tbody>
        <table class="uk-table uk-table-middle uk-table-striped uk-table-hover">
            <thead> Premade Cupcakes
                <tr>
                    <th>Topping</th>
                    <th>Bottom</th>
                    <th>Price</th>
                    <th>Order</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${applicationScope.cupcakes}" var="cupcake">
                    <tr>
                        <td>${cupcake.getTopping().getName()}</td>
                        <td>${cupcake.getBottom().getName()}</td>
                        <td>$${cupcake.getPrice()}</td>
                        <td class="uk-table-middle">
                            <a class="uk-button uk-button-default"
                                href="${pageContext.request.contextPath}/FrontController?&command=addToCart&topping=${cupcake.getTopping()}&bottom=${cupcake.getBottom()}">
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