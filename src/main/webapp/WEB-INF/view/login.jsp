<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/links.jsp"%>
</head>
<body>
	<div class="form-container">
		<h1>BookOne</h1>
		<!-- <h2>login</h2>  -->
		<form:form action="${pageContext.request.contextPath}/authentication" method="POST" cssClass="form">

			<fieldset class="form-fieldset ui-input __first">
				<input type="text" id="username" name="username" placeholder="username" tabindex="0" />
				<!--  <label for="username"> <span data-text="Username">Username</span> </label>  -->
			</fieldset>


			<fieldset class="form-fieldset ui-input __third">
				<input type="password" id="new-password" name="password" placeholder="password" />
				<!--  <label for="new-password"> <span data-text="New Password">New Password</span></label>  -->
			</fieldset>


			<div class="form-footer">
				<button class="btn">login</button>
			</div>
		</form:form>
	</div>
</body>
</html>