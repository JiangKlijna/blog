<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<title>${subject.title}</title>
</head>
<%@ include file="js.jsp" %>
<link rel="stylesheet" href="static/css/subject.css" />
<body>
<%@ include file="header.jsp" %>
<section id="main">
    <p class="text-center title">${subject.title}</p>
    <p class="text-center">
        <button id="follow"><c:choose><c:when test="${isFollow}">取消关注</c:when><c:otherwise>关注</c:otherwise></c:choose></button>
    </p>
    <p class="text-center author">
        <span class="label label-success">文章:${subject.numberOfArticles}</span>
        <span class="label label-danger">关注:<span id="numofollow">${subject.numberOfConcerns}</span></span>
    </p><hr>
    <div id="articles"></div>
    <div id="loadmore">
        <p id="load_more_btn" class="text-center">查看更多</p>
    </div>
</section>
<%@ include file="footer.jsp" %>
<%@ include file="dialog.jsp" %>
<script>subjectid=${subject.id}</script>
<script src="static/js/subject.js"></script>
</body>
</html>