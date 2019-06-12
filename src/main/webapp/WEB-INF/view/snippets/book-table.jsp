<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags"%>

<table id="bookTable" class="table table-hover sortable "> <!-- table-striped -->
    <thead>
    <tr>
        <th id="isbnTh" onclick="sortPage('isbn')">isbn</th>
        <th id="authorsTh" onclick="sortPage('authors')">author</th>
        <th id="nameTh" onclick="sortPage('name')">name</th>
        <th>score</th>
        <th id="priceTh" onclick="sortPage('price')">price</th>
        <th id="genresTh" onclick="sortPage('genres')">genre</th>
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
            <td style="display: inline-flex">
                <a href="book/${b.isbn}" ><img width="20px" src="https://img.icons8.com/dusk/64/000000/details-popup.png"></a>
                <a href="#" onclick="buyBook('<security:authentication property='principal.username'/>', '${b.isbn}');"><img width="20px" src="https://img.icons8.com/ultraviolet/40/000000/buy.png"></a>
                <security:authorize access="hasRole('ADMIN')">
                    <a href="/book/${b.isbn}?edit=true"><img width="20px" src="https://img.icons8.com/dusk/64/000000/pencil.png"></a>
                    <a href="#" onclick="return deleteBook('${b.isbn}');"><img width="20px" src="https://img.icons8.com/color/48/000000/close-window.png"></a>
                </security:authorize>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    $("#${sortColumn}Th").addClass("bookTh");
</script>

<nav data-pagination>
    <a href=# disabled><i class=ion-chevron-left></i></a>
    <ul>
        <c:forEach var="i" begin="0" end="${pagesCount}" step="1" varStatus ="status">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <li class=current><a href="#" onclick="return loadPage('${i}');">${i}</a>
                </c:when>
                <c:otherwise>
                    <li><a href="#" onclick="return loadPage('${i}');">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
    <a href=#2><i class=ion-chevron-right></i></a>
</nav>