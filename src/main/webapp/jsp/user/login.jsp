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
	<title>Login</title>
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
							<h3 class="uk-card-title uk-text-center">Welcome back!</h3>
							<%@include file="loginForm.jsp"%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>