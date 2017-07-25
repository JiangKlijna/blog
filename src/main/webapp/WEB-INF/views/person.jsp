<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>博客</title>
</head>
<%@ include file="js.jsp" %>
<link rel="stylesheet" href="static/css/index.css" />
<body>
<%@ include file="header.jsp" %>
<section id="main">
    <c:choose>
    <c:when test="${isExist}">
    </c:when>
    <c:otherwise>
    </c:otherwise>
    </c:choose>
</section>
<%@ include file="footer.jsp" %>
<script src="static/js/person.js"></script>
</body>
</html>
