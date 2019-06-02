<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>error</title>
<link href="<c:url value="/resources/css/errorpage.css"/>" rel="stylesheet" />
<%@ include file="/WEB-INF/include/links.jsp"%>
</head>
<body>

<div class="col">
	<%@ include file="/WEB-INF/include/robot.jsp"%>
  <h1>ER0R</h1>

  <p>
  	someone got fired... <a href="${pageContext.request.contextPath}/">back</a>
  	<br>
  	<%-- ${message} --%>
  </p>
  
</div>
</body>
</html>