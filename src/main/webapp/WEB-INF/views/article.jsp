<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<title>博客</title>
</head>
<%@ include file="js.jsp" %>
<link rel="stylesheet" href="static/css/article.css" />
<body>
<%@ include file="header.jsp" %>
<section id="main">
    <c:choose>
    <c:when test="${isExist}">
    <p class="text-center title">${article.title}</p><hr>
    ${article.content}
    </c:when>
    <c:otherwise>
        <p class="text-center" style="font-weight: 900; font-size: 20px;padding: 20px;">此文章不存在, <a href="./" class="text-primary">返回主页</a></p>
    </c:otherwise>
    </c:choose>
</section>
<%@ include file="footer.jsp" %>
<script src="static/js/article.js"></script>
</body>
</html>
