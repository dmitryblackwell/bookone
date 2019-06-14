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
		<div class="row">
			<div class="col-4">
				<section class="tasks">
					<fieldset class="tasks-list">
						<c:forEach items="${genres}" var="g" varStatus="status">
							<label class="tasks-list-item">
								<input type="checkbox" name="${g.name}" value="${g.id}" class="tasks-list-cb"
									   onclick="filterWithGenres(this)">
								<span class="tasks-list-mark"></span>
								<span class="tasks-list-desc">${g.name}</span>
							</label>
						</c:forEach>
					</fieldset>
				</section>
			</div>
			<div class="col-8" id="book-content">
			</div>
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