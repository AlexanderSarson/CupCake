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
        <table class="uk-table uk-table-middle uk-table-striped uk-table-hover">
            <thead>
                <h1 class="uk-h1"> Custom Cupcakes </h1>
                <tr>
                    <th>Topping</th>
                    <th>Bottom</th>
                    <th>Order</th>
                </tr>
            </thead>
            <tbody>
                <form name="customCupcakeForm" action="${pageContext.request.contextPath}/FrontController" method="post">
                    <input type="hidden" name="command" value="addToCart">
                    <tr>
                         <td>
                            <div class="uk-margin">
                                <div class="uk-form-controls">
                                    <select class="uk-select" name="customTopping">
                                        <c:forEach items="${applicationScope.toppings}" var="topping">
                                            <option name="toppingSelect" value="${topping}">${topping.getName()} - $${topping.getPrice()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </td>
                        <td>
                            <div class="uk-margin">
                                <div class="uk-form-controls">
                                    <select class="uk-select" name="customBottom">
                                        <c:forEach items="${applicationScope.bottoms}" var="bottom">
                                            <option name="bottomSelect" value="${bottom}">${bottom.getName()} - $${bottom.getPrice()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </td>
                        <td class="uk-table-middle">
                            <div class="uk-margin">
                                <button type="submit" class="uk-button uk-button-primary uk-button-large uk-width-1-1">Add to cart</button>
                            </div>
                        </td>
                    </tr>
                </form>
            </tbody>

        </table>
        <table class="uk-table uk-table-middle uk-table-striped uk-table-hover">
            <thead> <h1 class="uk-h1">Premade Cupcakes</h1>
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