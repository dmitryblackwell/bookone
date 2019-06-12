<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form:form action="${pageContext.request.contextPath}/logout" method="POST" id="form"/>

<header class="header-fixed">

	<div class="header-limiter">

		<h1><a href="${contextPath}/">Book<span>ONE</span></a></h1>

		<nav>
			<a href="${contextPath}/book">books</a>
			<security:authorize access="hasRole('ADMIN')">
				<a href="${contextPath}/orders">orders</a>
			</security:authorize>
			<a href="${contextPath}/users/<security:authentication property="principal.username"/>" id="profilePageLink"><security:authentication property="principal.username"/></a>
			<a href="#" onClick="formClick()">logout</a>
		</nav>

	</div>

</header>
