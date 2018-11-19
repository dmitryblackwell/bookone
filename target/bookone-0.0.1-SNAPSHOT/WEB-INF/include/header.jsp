<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form:form action="${pageContext.request.contextPath}/logout" method="POST" id="form"/>
<div id="header" class="row">
	<div class="col" id="header-title">
		<h1>
			<a href="${contextPath}/">BookOne</a>
		</h1>
	</div>
	<div class="col">
		<p id="username-text">
			<a href="${contextPath}/users/<security:authentication property="principal.username"/>"><security:authentication property="principal.username"/></a>
			
			<a href="#" onClick="formClick()">(logout)</a>
		</p>
	</div>
</div>
<!-- 
<div class="row">
	<img id="headerimg" src="<c:url value="/resources/img/bookshelf-45-cm.jpg"/>">
</div>
 -->