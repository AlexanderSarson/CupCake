
<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  Date: 2019-10-18
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Create User</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/uikit.min.css"/>
	<script src="${pageContext.request.contextPath}/js/validationScript.js"></script>
	<script src="${pageContext.request.contextPath}/js/uikit.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/uikit-icons.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/validationScript.js"></script>
</head>
<body>
<%@include file="/jsp/nav.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="uk-section uk-flex uk-animation-fade" uk-height-viewport>
	<div class="uk-width-1-1">
		<div class="uk-container">
			<div class="uk-grid-margin uk-grid uk-grid-stack" uk-grid>
				<div class="uk-width-1-1@m">
					<div class="uk-margin uk-width-large uk-margin-auto uk-card uk-card-default uk-card-body uk-box-shadow-large">
						<h3 class="uk-card-title uk-text-center">Create Account</h3>
						<form name="createAccountForm" action="<c:url value="/FrontController"/>" method="post"
							  onsubmit="event.preventDefault(); validateAccountCreation();">
							<input type="hidden" name="command" value="register">
							<div class="uk-margin">
								<div class="uk-inline uk-width-1-1">
									<span class="uk-form-icon" uk-icon="icon: user"></span>
									<input class="uk-input uk-form-large" id="name" type="text" name="userName"
										   placeholder="Name*">
								</div>
							</div>
							<div class="uk-margin">
								<div class="uk-inline uk-width-1-1">
									<span class="uk-form-icon" uk-icon="icon: mail"></span>
									<input class="uk-input uk-form-large" id="email" type="text" name="email"
										   placeholder="Email*">
								</div>
							</div>
							<div class="uk-margin">
								<div class="uk-inline uk-width-1-1">
									<span class="uk-form-icon" uk-icon="icon: lock"></span>
									<input class="uk-input uk-form-large" id="password" type="password" name="password"
										   placeholder="Password*">
								</div>
							</div>
							<div class="uk-margin">
								<div class="uk-inline uk-width-1-1">
									<span class="uk-form-icon" uk-icon="icon: lock"></span>
									<input class="uk-input uk-form-large" id="confirmPassword" type="password"
										   name="confirmPassword" placeholder="Confirm Password*">
								</div>
							</div>
							<div class="uk-margin">
								<button id="submitBtn" class="uk-button uk-button-primary uk-button-large uk-width-1-1">
									Create account
								</button>
							</div>
							<div class="uk-text-small uk-text-center">
								<p id="errorMessage" style="color: #DC143C"></p>
								<c:if test="${requestScope.error != null}">
									<h4><c:out value="${requestScope.error}"/></h4>
								</c:if>
							</div>
							<div class="uk-text-small uk-text-center uk-margin-small-top">
								Already have an account? <a id="loginLink" href="${contextPath}/FrontController?&command=redirect&target=jsp/user/login.jsp">Log in</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
