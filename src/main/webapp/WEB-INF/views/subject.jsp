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
    <p class="text-center title">${subject.title}</p><hr>
    <p class="text-center author">
        <span class="label label-success">文章:${subject.numberOfArticles}</span>
        <span class="label label-danger">关注:${subject.numberOfConcerns}</span>
    </p><hr>
</section>
<%@ include file="footer.jsp" %>
<script src="static/js/subject.js"></script>
</body>
</html>
