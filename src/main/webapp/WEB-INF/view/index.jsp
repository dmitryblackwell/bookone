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
		
		<div class="col">
			<table id="bookTable" class="table table-hover sortable "> <!-- table-striped -->
				<thead>
					<tr>
						<th>isbn</th>
						<th>author</th>
						<th>name</th>
						<th>score</th>
						<th>price</th>
						<th>genre</th>
						<th>action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${books}" var="b">
						<tr id="tr${b.isbn}" class="pointer"> <%--  onclick="window.location.href = 'books/${b.isbn}';" --%>
							<td>${b.isbn}</td>
							<td>${b.authors}</td>
							<td>${b.name}</td>
							<td>${b.score}</td>
							<td>${b.price}</td>
							<td>${b.genres}</td>
							<td>
								<a href="book/${b.isbn}" ><img width="25px" src="https://img.icons8.com/dusk/64/000000/details-popup.png"></a>
								<a href="#" onclick="buyBook('<security:authentication property='principal.username'/>', '${b.isbn}');"><img width="25px" src="https://img.icons8.com/ultraviolet/40/000000/buy.png"></a>
								<security:authorize access="hasRole('ADMIN')">
									<a href="/book/${b.isbn}?edit=true"><img width="25px" src="https://img.icons8.com/dusk/64/000000/pencil.png"></a>
									<a href="#" onclick="return deleteBook('${b.isbn}');"><img width="25px" src="https://img.icons8.com/color/48/000000/close-window.png"></a>
								</security:authorize>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="book/0?edit=true">
			<div class="btn" style="margin-top: 20px; float: right;">
				<span>add book</span>
			</div>
			</a>
		</div>
	</div>

</body>
</html>