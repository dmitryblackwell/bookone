<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/include/links.jsp"%>
</head>
<body>
    <div class="container" id="wrap">
        <%@ include file="/WEB-INF/include/header.jsp"%>

        <table id="orderTable" class="table table-hover sortable "> <!-- table-striped -->
            <thead>
            <tr>
                <th>orderNo</th>
                <th>author</th>
                <th>title</th>
                <th>price</th>
                <th>username</th>
                <th>user email</th>
                <th>status</th>
                <th>action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="o">
                <tr id="tr${o.orderNo}" class="pointer"> <%--  onclick="window.location.href = 'books/${b.isbn}';" --%>
                    <td>${o.orderNo}</td>
                    <td>${o.book.author}</td>
                    <td>${o.book.name}</td>
                    <td>${o.book.price}</td>
                    <td>${o.user.username}</td>
                    <td>${o.user.email}</td>
                    <td id="status-${o.orderNo}">${o.status}</td>
                    <td>
                        <a href="#" onclick="return approveOrder('${o.orderNo}');">approve</a>
                        |
                        <a href="#" onclick="return deleteOrder('${o.orderNo}');">delete</a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

</body>
</html>
