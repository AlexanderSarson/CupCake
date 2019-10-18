<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  Date: 2019-09-28
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/uikit.min.css" />
	<script src="${pageContext.request.contextPath}/js/script.js"></script>
	<script src="${pageContext.request.contextPath}/js/uikit.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/uikit-icons.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
	<%@include file="/jsp/nav.jsp"%>

		<div class="uk-section uk-flex uk-animation-fade" uk-height-viewport>
			<div class="uk-width-1-1">
				<div class="uk-container">
					<div class="uk-grid-margin uk-grid uk-grid-stack" uk-grid>
						<div class="uk-width-1-1@m">
							<div class="uk-margin uk-width-large uk-margin-auto uk-card uk-card-default uk-card-body uk-box-shadow-large">
								<h3 class="uk-card-title uk-text-center">Welcome back!</h3>

								<form name="loginForm" action="" method="post" onsubmit="return validateLogin()">
									<input type="hidden" name="command" value="login">
									<div class="uk-margin">
										<div class="uk-inline uk-width-1-1">
											<span class="uk-form-icon" uk-icon="icon: mail"></span>
											<input class="uk-input uk-form-large" id="loginEmail" type="text" name="email">
										</div>
									</div>
									<div class="uk-margin">
										<div class="uk-inline uk-width-1-1">
											<span class="uk-form-icon" uk-icon="icon: lock"></span>
											<input class="uk-input uk-form-large" id="loginPassword" type="password" name="password">
										</div>
									</div>
									<div class="uk-margin">
										<button class="uk-button uk-button-primary uk-button-large uk-width-1-1">Login</button>
									</div>
									<div class="uk-text-small uk-text-center">
										<c:if test="${requestScope.error != null}">
											<h4><c:out value="${requestScope.error}"/></h4>
										</c:if>
									</div>
									<div class="uk-text-small uk-text-center uk-margin-small-top">
										Not registered? <a href="#">Create an account</a>
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
