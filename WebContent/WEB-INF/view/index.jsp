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
		<security:authorize access="hasRole('ADMIN')">
			<form:form method="POST" modelAttribute="book" class="bookform" id="bookform">
				<fieldset class="form-fieldset ui-input" id="fieldset-save">					
					<form:input path="isbn" placeholder="isbn"/>
					<form:input path="author" placeholder="author"/>
					<form:input path="name" placeholder="name"/>
					<form:input path="price" placeholder="price" style="width:158px;"/>
					<form:select path="genre">
						<form:options items="${genres}" itemLabel="name"/>
					</form:select>
					<div class="btn" for="test" id="savebook" onclick="document.getElementById('bookform').submit();">
						<span>save</span>
					</div>
				</fieldset>
			</form:form>
		</security:authorize>	
		
		<form>
			<fieldset class="form-fieldset ui-input __third">
				<input type="text" style="width:100%;text-align:center;margin-bottom:45px;" id="searchInput" onkeyup="tablesearch()" placeholder="search for books by name...">
			</fieldset>
		</form>
			<table id="bookTable" class="table table-hover sortable "> <!-- table-striped -->
				<thead>
					<tr>
						<th>isbn</th>
						<th>author</th>
						<th>name</th>
						<th>price</th>
						<th>genre</th>
						<th>action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${books}" var="b">
						<tr id="tr${b.isbn}" onclick="window.location.href = 'books/${b.isbn}';" class="pointer">
							<td>${b.isbn}</td>
							<td>${b.author}</td>
							<td>${b.name}</td>
							<td>${b.price}</td>
							<td>${b.genre.name}</td>
							<td><security:authorize access="hasRole('ADMIN')">
								<a href="#" onclick="return deleteBook('${b.isbn}');">delete</a>
								|
								<a href="#" onclick="return setFields('${b.isbn}', '${b.author}', '${b.name}', '${b.price}', '${b.genre.id}');">edit</a>
								|
							</security:authorize> 
							<a href="buy?isbn=${b.isbn}">buy</a></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>