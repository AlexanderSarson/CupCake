<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
  <title>Home</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/uikit.min.css" />
  <script src="js/uikit.min.js"></script>
  <script src="js/uikit-icons.min.js"></script>

</head>

<body>
  <%@include file="jsp/nav.jsp"%>
  <div class="uk-text-small uk-text-center">
    <p id="errorMessage" style="color: #DC143C"></p>
    <c:if test="${requestScope.error != null}">
      <h4><c:out value="${requestScope.error}"/></h4>
    </c:if>
  </div>
</body>

</html>