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
<title>Home</title>
<%@ include file="/WEB-INF/include/links.jsp"%>
</head>
<body>

	<div class="container" id="wrap">
		<%@ include file="/WEB-INF/include/header.jsp"%>
		<div class="row">
			<%@ include file="/WEB-INF/include/slider.jsp"%>
		</div>
		<form>
			<fieldset class="form-fieldset ui-input __third">
				<input type="text" style="width:100%;text-align:center;margin-bottom:15px;margin-top:30px;" id="searchInput" onkeyup="search()" value="${searchValue}" placeholder="search...">
			</fieldset>
		</form>
		<div class="col" id="book-content">
		</div>
		<c:if test="${loadBooks == true}">
			<script>
				loadPage('0');
			</script>
		</c:if>

		<security:authorize access="hasRole('ADMIN')">
			<a href="book/0?edit=true">
				<div class="btn" style="margin-top: 20px; float: right;">
					<span>add book</span>
				</div>
			</a>
		</security:authorize>
	</div>

	<%@ include file="/WEB-INF/include/footer.jsp"%>
</body>
</html>