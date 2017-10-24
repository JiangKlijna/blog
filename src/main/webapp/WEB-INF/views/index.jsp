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
    <article id="articles">
        <div>
            <p id="article_load_more" class="text-center pointer">查看更多</p><hr>
        </div>
    </article>
    <aside id="subjects">
        <div>
            <p id="subject_load_more" class="text-center pointer">查看更多</p><hr>
        </div>
    </aside>
</section>
<%@ include file="footer.jsp" %>
<script>type='${type}'</script>
<script src="static/js/index.js"></script>
</body>
</html>
