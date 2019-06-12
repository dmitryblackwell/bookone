<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="/WEB-INF/include/links.jsp"%>
<script src="<c:url value="/resources/js/rating.js"/>"></script>
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
							<a href="?edit=true">
								<div class="btn" style="margin-top: 20px;">
									<span>edit</span>
								</div>
							</a>
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
								<th>Authors</th>
								<td>${book.authorsNames}</td>
							</tr>
							<tr>
								<th>Price</th>
								<td>${book.price}</td>
							</tr>
							<tr>
								<th>Genres</th>
								<td>${book.genresNames}</td>
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
						<c:forEach items="${comments}" var="c">
							<div class="comment-post" id="comment${c.id}">

								<span class="comment-author"><a href="${contextPath}/users/${c.username}">${c.username}</a></span>
								<div class="col-xs-2"><img src="${contextPath}/resources/uploaded-images/users/${c.username}.jpg"/></div>

								<div class="col-xs-9">
									<p>
										<h3>
											${c.header}
											<security:authorize access="hasRole('ADMIN')">
												<span class="comment-time"><a href="#" onclick="return deleteComment('${book.isbn}', '${c.id}')">delete</a> </span>
											</security:authorize>
										</h3>

									<p>${c.score}</p>
									</p>
									<p class="comment-content">${c.body}</p>
								</div>

							</div>
						</c:forEach>

						<div class="col-xs-12">
							<form:form action="${book.isbn}/comments" method="post" id="addCommentForm" cssStyle="margin-top: 50px;">
								<input type="text" placeholder="Right your title here..." style="width: 100%;" name="header">
								<input type="hidden" id="commentScore" name="score" value="">
								<div class="stars" data-rating="0">
									<span class="star">&nbsp;</span>
									<span class="star">&nbsp;</span>
									<span class="star">&nbsp;</span>
									<span class="star">&nbsp;</span>
									<span class="star">&nbsp;</span>
								</div>
								<textarea placeholder="Right your comment here..." name="body"></textarea>
								<input type="hidden" name="username" value="<security:authentication property="principal.username"/>">
								<div class="btn" for="test" id="addcomment" onclick="document.getElementById('addCommentForm').submit();" style="margin-top: 20px; float: right;">
									<span>add comment</span>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<%@ include file="/WEB-INF/include/footer.jsp"%>
</body>
</html>