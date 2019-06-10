<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>${user.username}</title>

    <link rel="stylesheet" href="https://bootswatch.com/4/simplex/bootstrap.min.css"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <%@ include file="/WEB-INF/include/links.jsp"%>
    <link href="<c:url value="/resources/css/userview.css"/>" rel="stylesheet" />
    <script src="<c:url value="/resources/js/userview.js"/>"></script>


    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
<div class="container">
    <%@ include file="/WEB-INF/include/header.jsp"%>
    <div class="row">
        <div class="col-12">
            <div class="card">

                <div class="card-body">
                    <div class="card-title mb-4">
                        <div class="d-flex justify-content-start">
                            <div class="image-container">
                                <img src="${pageContext.request.contextPath}/resources/uploaded-images/users/${user.username}.jpg" id="imgProfile" style="width: 150px; height: 150px;" class="img-thumbnail" />
                                <div class="middle">
                                    <form:form action="upload-image" id="profile-photo-form" method="POST"  enctype="multipart/form-data">
                                        <input type="button" class="btn btn-secondary" id="btnChangePicture" value="Change" style="color:black;"/>
                                        <input type="hidden" name="username" accept="image/jpeg" value="${user.username}">
                                        <input type="file" style="display: none;" id="profilePicture" name="file" />
                                    </form:form>
                                </div>
                            </div>
                            <div class="userData ml-3">
                                <h2 class="d-block" style="font-size: 1.5rem; font-weight: bold"><a href="javascript:void(0);">${user.username}</a></h2>
                            </div>
                            <div class="ml-auto">
                                <input type="button" class="btn btn-primary d-none" id="btnDiscard" value="Discard Changes" />
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12">
                            <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" id="basicInfo-tab" data-toggle="tab" href="#basicInfo" role="tab" aria-controls="basicInfo" aria-selected="true">info</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="connectedServices-tab" data-toggle="tab" href="#connectedServices" role="tab" aria-controls="connectedServices" aria-selected="false">orders</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="orders-tab" data-toggle="tab" href="#comments" role="tab" aria-controls="comment" aria-selected="false">comments</a>
                                </li>
                            </ul>
                            <div class="tab-content ml-1" id="myTabContent">
                                <div class="tab-pane fade show active" id="basicInfo" role="tabpanel" aria-labelledby="basicInfo-tab">


                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;">Username</label>
                                        </div>
                                        <div class="col-md-8 col-6">
                                            ${user.username}
                                        </div>
                                    </div>
                                    <hr />

                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;">email</label>
                                        </div>
                                        <div class="col-md-8 col-6">
                                            ${user.email}
                                        </div>
                                    </div>
                                    <hr />

                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;">Roles</label>
                                        </div>
                                        <div class="col-md-8 col-6">
                                            <%-- ${user.authorities} --%>
                                        </div>
                                    </div>
                                    <hr />


                                    <div class="row">
                                        <div class="col-sm-3 col-md-2 col-5">
                                            <label style="font-weight:bold;">Books bought</label>
                                        </div>
                                        <div class="col-md-8 col-6">
                                            ${orders.size()}
                                        </div>
                                    </div>
                                    <hr />


                                </div>
                                <div class="tab-pane fade" id="connectedServices" role="tabpanel" aria-labelledby="ConnectedServices-tab">
                                    <table id="bookTable" class="table table-hover sortable "> <!-- table-striped -->
                                        <thead>
                                        <tr>
                                            <th>orderNo</th>
                                            <th>author</th>
                                            <th>name</th>
                                            <th>price</th>
                                            <th>status</th>
                                            <th>action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${orders}" var="o">
                                            <tr id="tr${o.id}" class="pointer"> <%--  onclick="window.location.href = 'books/${b.isbn}';" --%>
                                                <td>${o.id}</td>
                                                <td>${o.book.authorsNames}</td>
                                                <td>${o.book.name}</td>
                                                <td>${o.book.price}</td>
                                                <td>${o.status}</td>

                                                <td>
                                                    <%--<a href="#" onclick="return deleteOrder('${o.orderNo}');">delete</a>--%>
                                                    <a href="/book/${o.book.isbn}" >more</a>
                                                </td>

                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane fade" id="comments" role="tabpanel" aria-labelledby="Orders-tab">
                                    <div class="row" style="margin-top: 50px;">
                                        <div class="col-8">
                                            <div class="comments-section">
                                                <c:forEach items="${comments}" var="c">
                                                    <div class="comment-post" id="comment${c.id}">

                                                        <div class="col-xs-2"><img src="${contextPath}/resources/uploaded-images/users/${c.username}.jpg"/></div>
                                                        <div class="col-xs-9">
                                                            <p>
                                                                <span class="comment-author">${c.username}</span> commented book <a href="${contextPath}/book/${c.isbn}">${c.isbn}</a>
                                                            </p>
                                                            <p class="comment-content">${c.body}</p>
                                                        </div>

                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>
