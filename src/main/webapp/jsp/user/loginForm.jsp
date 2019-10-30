<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  Date: 2019-10-21
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<title>Login</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/uikit.min.css" />
	<script src="${pageContext.request.contextPath}/js/validationScript.js"></script>
	<script src="${pageContext.request.contextPath}/js/uikit.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/uikit-icons.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/validationScript.js"></script>
</head>

<body>

	<form name="loginForm" action="${pageContext.request.contextPath}/FrontController" method="post">
		<input type="hidden" name="command" value="login">
		<div class="uk-margin">
			<div class="uk-inline uk-width-1-1">
				<span class="uk-form-icon" uk-icon="icon: mail"></span>
				<input class="uk-input uk-form-large" type="email" name="email" placeholder="Email" required>
			</div>
		</div>
		<div class="uk-margin">
			<div class="uk-inline uk-width-1-1">
				<span class="uk-form-icon" uk-icon="icon: lock"></span>
				<input class="uk-input uk-form-large" type="password" name="password" minlength="8"
					placeholder="Password" required>
			</div>
		</div>
		<div class="uk-margin">
			<button type="submit" class="uk-button uk-button-primary uk-button-large uk-width-1-1">Login</button>
		</div>
		<div class="uk-text-small uk-text-center">
			<c:if test="${requestScope.error != null}">
				<h4>
					<c:out value="${requestScope.error}" />
				</h4>
			</c:if>
		</div>
		<div class="uk-text-small uk-text-center uk-margin-small-top">
			Not registered? <a id="registerLink"
				href="${pageContext.request.contextPath}/FrontController?&command=redirect&target=jsp/user/createUser.jsp">Create
				an account</a>
		</div>
	</form>

</body>

</html>