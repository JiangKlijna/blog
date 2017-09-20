<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
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
    <article id="articles"></article>
    <aside id="subjects"></aside>
</section>
<%@ include file="footer.jsp" %>
<script src="static/js/index.js"></script>
</body>
</html>
