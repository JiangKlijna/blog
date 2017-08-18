<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<title>${subject.title}</title>
</head>
<%@ include file="js.jsp" %>
<link rel="stylesheet" href="static/css/index.css" />
<body>
<%@ include file="header.jsp" %>
<section id="main">
    <h1 id="jqtest">${subject.title}</h1>
</section>
<%@ include file="footer.jsp" %>
<script src="static/js/subject.js"></script>
</body>
</html>
