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
        <div class="row">
            <div class="artwork col-xs-12 col-md-4">
                <img src="${pageContext.request.contextPath}/resources/uploaded-images/books/${book.isbn}.jpg"
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
                <form:form method="POST" action="/book/" modelAttribute="book" class="bookform" id="bookform">
                    <fieldset class="form-fieldset ui-input" id="fieldset-save">
                        <table class="table">
                            <tbody>
                            <tr>
                                <th>Name</th>
                                <td><form:input path="isbn" placeholder="isbn" value="${book.name}"/></td>
                            </tr>
                            <tr>
                                <th>ISBN</th>
                                <th><form:input path="authors" placeholder="authors" value="${book.isbn}"/></th>
                            </tr>
                            <tr>
                                <th>authors</th>
                                <td>${book.authorsNames}</td>
                            </tr>
                            <tr>
                                <th>Price</th>
                                <td><form:input path="name" placeholder="name" value="${book.price}"/></td>
                            </tr>
                            <tr>
                                <th>Genre</th>
                                <td>
                                    <form:select path="genres">
                                        <form:options items="${genres}" itemLabel="name"/>
                                    </form:select>
                                </td>
                            </tr>
                            <tr>
                                <th>Description</th>
                                <td><p><form:textarea path="description" placeholder="description" value="${book.description}"/></td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <div class="btn" for="test" id="savebook" onclick="document.getElementById('bookform').submit();">
                                        <span>save</span>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </form:form>
            </div>

        </div>
    </div>
</div>

</body>
</html>