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
    <p class="text-center title">${name}</p>
    <c:choose><c:when test="${username != vu.username}">
        <p class="text-center">
            <button class="follow" data-name="${vu.username}"><c:choose><c:when test="${isFollow}">取消关注</c:when><c:otherwise>关注</c:otherwise></c:choose></button>
        </p>
    </c:when></c:choose>
    <p class="text-center">
        <span class="label label-primary pointer">文章:${vu.numberOfArticles}</span>
        <span class="label label-info pointer">粉丝:${vu.numberOfFans}</span>
        <span class="label label-warning pointer">关注:${vu.numberOfConcerns}</span>
        <span class="label label-success">喜欢:${vu.favoriteNumber}</span>
        <span class="label label-danger">字数:${vu.numberOfWords}</span>
    </p>
    <hr>
    <div id="listview"></div>
    <div id="loadmore">
        <p id="load_more_btn" class="text-center pointer">查看更多</p><hr>
    </div>
    </c:when>
    <c:otherwise>
        <p class="text-center" style="font-weight: 900; font-size: 20px;padding: 20px;">此用户不存在, <a href="./" class="text-primary">返回主页</a></p>
    </c:otherwise>
    </c:choose>
</section>
<%@ include file="footer.jsp" %>
<%@ include file="dialog.jsp" %>
<script>isExist = ${isExist};name = "${name}"</script>
<script src="static/js/person.js"></script>
</body>
</html>
