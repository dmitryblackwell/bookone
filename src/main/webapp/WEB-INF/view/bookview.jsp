<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/links.jsp"%>
</head>
<body>



	<div class="container" id="wrap">
		<%@ include file="/WEB-INF/include/header.jsp"%>
		<div class="col">
			<div class="row">
				<div class="artwork col-xs-12 col-md-4">
					<img
						src="${pageContext.request.contextPath}/resources/uploaded-images/books/${book.isbn}.jpg"
						style="height: 400px; width: 290px; background-color: #dddddd" />
					<security:authorize access="hasRole('ADMIN')">
						<form:form method="POST" action="upload-image" enctype="multipart/form-data">
							<div id="photoWrapper">
								<label style="float:left;"> <input type="file" accept="image/jpeg" name="file"></input>
									<div class="newInputWrapper">
										<span id="photo"></span>
										<p class="filename">Add Picture</p>
									</div>
								</label>
								<input type="hidden" name="isbn" accept="image/jpeg" value="${book.isbn}">
								<input type="submit" value="Submit" style="float:right;" />
							</div>					
						</form:form>
					</security:authorize>
				</div>

				<div class="book-table col-xs-12 col-md-8">
					<table class="table">
						<tbody>
							<tr>
								<th>Name</th>
								<td>${book.name}</td>
							</tr>
							<tr>
								<th>ISBN</th>
								<th>${book.isbn}</th>
							</tr>
							<tr>
								<th>${book.author}</th>
								<td>author</td>
							</tr>
							<tr>
								<th>Price</th>
								<td>${book.price}</td>
							</tr>
							<tr>
								<th>Genre</th>
								<td>${book.genre.name}</td>
							</tr>
							<tr>
								<th>Description</th>
								<td><p>${book.description}
							</tr>
						</tbody>
					</table>
					<div class="btn" for="test"  onclick="buyBook('<security:authentication property='principal.username'/>', '${book.isbn}');" id="savebook" style="float: right;">
						<span>buy</span>
					</div>
				</div>

			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-8" style="float:right;">
					<div class="comments-section">
						<c:forEach items="${book.comments}" var="c">
							<div class="comment-post" id="comment${c.id}">

								<div class="col-xs-2"><img src="${contextPath}/resources/uploaded-images/users/${c.user.username}.jpg"/></div>
								<div class="col-xs-9">
									<p>
										<span class="comment-author"><a href="${contextPath}/users/${c.user.username}">${c.user.username}</a></span><span class="comment-time">${c.user.orders.size()} orders</span>
										<security:authorize access="hasRole('ADMIN')">
											<span class="comment-time"><a href="#" onclick="return deleteComment('${book.isbn}', '${c.id}')">delete</a> </span>
										</security:authorize>
									</p>
									<p class="comment-content">${c.comment}</p>
								</div>

							</div>
						</c:forEach>

						<div class="col-xs-12">
							<form:form action="${book.isbn}/comments" method="post" id="addCommentForm" cssStyle="margin-top: 50px;">
								<textarea name="comment"></textarea>
								<input type="hidden" name="username" value="<security:authentication property="principal.username"/>">
								<div class="btn" for="test" id="addcomment" onclick="document.getElementById('addCommentForm').submit();" style="float: right;">
									<span>add comment</span>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>