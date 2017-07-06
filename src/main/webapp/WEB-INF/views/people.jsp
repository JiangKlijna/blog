<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<title>博客</title>
</head>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" href="static/css/index.css" />
<body>
<%@ include file="header.jsp" %>
<section id="main">
    <h1 id="jqtest">${username}</h1>
</section>
<%@ include file="footer.jsp" %>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="static/js/index.js"></script>
</body>
</html>
