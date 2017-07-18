﻿<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<title>博客</title>
</head>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="static/css/jquery.notebook.css" />
<link rel="stylesheet" href="static/css/write.css" />
<body>
<%@ include file="header.jsp" %>
<section id="main">
    <input id="write_title" class="write_input" placeholder="请输入标题"><hr>
    <input id="write_subject" class="write_input" placeholder="请输入主题"><hr>
    <div id="write_content"><h4>请输入正文</h4></div>
</section>
<div id="write_submit">
    <a type="button" class="btn btn-success navbar-btn" href="sign.do">发布</a>
    <a type="button" class="btn btn-danger navbar-btn" href="sign.do">清除</a>
</div>
<%@ include file="footer.jsp" %>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="static/js/jquery.notebook.js"></script>
<script src="static/js/write.js"></script>
</body>
</html>