<%--
  Created by IntelliJ IDEA.
  User: madsbrandt
  Date: 2019-10-19
  Time: 01:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
	<title>Edit User</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/uikit.min.css" />
	<script src="${pageContext.request.contextPath}/js/validationScript.js"></script>
	<script src="${pageContext.request.contextPath}/js/uikit.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/uikit-icons.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/validationScript.js"></script>
</head>

<body>
	<%@include file="/jsp/nav.jsp"%>

	<div class="uk-section uk-flex uk-animation-fade" uk-height-viewport>
		<div class="uk-width-1-1">
			<div class="uk-container">
				<div class="uk-grid-margin uk-grid uk-grid-stack" uk-grid>
					<div class="uk-width-1-1@m">
						<div
							class="uk-margin uk-width-large uk-margin-auto uk-card uk-card-default uk-card-body uk-box-shadow-large">

							<h3 class="uk-card-title uk-text-center">Edit Account</h3>

							<form name="editAccountForm" action="#" method="post"
								onsubmit="event.preventDefault(); validateAccountEdit();">
								<input type="hidden" name="command" value="updateUser">
								<div class="uk-margin">
									<div class="uk-inline uk-width-1-1">
										<span class="uk-form-icon" uk-icon="icon: user"></span>
										<input class="uk-input uk-form-large" id="name" type="text" name="userName" value="John">
									</div>
								</div>
								<div class="uk-margin">
									<div class="uk-inline uk-width-1-1">
										<span class="uk-form-icon" uk-icon="icon: mail"></span>
										<input class="uk-input uk-form-large" id="email" type="text" name="text" value="john@john.com">
									</div>
								</div>
								<div class="uk-margin">
									<div class="uk-inline uk-width-1-1">
										<span class="uk-form-icon" uk-icon="icon: lock"></span>
										<input class="uk-input uk-form-large" id="oldPassword" type="password" name="oldPassword"
											placeholder="Old Password">
									</div>
								</div>
								<div class="uk-margin">
									<div class="uk-inline uk-width-1-1">
										<span class="uk-form-icon" uk-icon="icon: lock"></span>
										<input class="uk-input uk-form-large" id="newPassword" type="password" name="newPassword"
											placeholder="New Password">
									</div>
								</div>
								<div class="uk-margin">
									<div class="uk-inline uk-width-1-1">
										<span class="uk-form-icon" uk-icon="icon: lock"></span>
										<input class="uk-input uk-form-large" id="confirmPassword" type="password" name="confirmPassword"
											placeholder="Confirm New Password">
									</div>
								</div>
								<div class="uk-margin">
									<button class="uk-button uk-button-primary uk-button-large uk-width-1-1">
										Update Account
									</button>
								</div>
								<div class="uk-text-small uk-text-center">
									<p id="errorMessage" style="color: #DC143C"></p>
									<c:if test="${requestScope.error != null}">
										<h4>
											<c:out value="${requestScope.error}" />
										</h4>
									</c:if>
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