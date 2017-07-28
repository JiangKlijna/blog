<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>${name}的博客</title>
</head>
<%@ include file="js.jsp" %>
<link rel="stylesheet" href="static/css/person.css" />
<body>
<%@ include file="header.jsp" %>
<section id="main">
    <c:choose>
    <c:when test="${isExist}">
    <p class="text-center title">${name}</p><hr>
    <table class="subtitle text-center"><tr>
        <td>${vu.numberOfConcerns}<br>关注</td>
        <td>${vu.numberOfFans}<br>粉丝</td>
        <td>${vu.numberOfArticles}<br>文章</td>
        <td>${vu.favoriteNumber}<br>喜欢</td>
        <td>${vu.numberOfWords}<br>字数</td>
    </tr></table>
    <hr>

    </c:when>
    <c:otherwise>
        <p class="text-center" style="font-weight: 900; font-size: 20px;padding: 20px;">此用户不存在, <a href="./" class="text-primary">返回主页</a></p>
    </c:otherwise>
    </c:choose>
</section>
<%@ include file="footer.jsp" %>
<script src="static/js/person.js"></script>
</body>
</html>
