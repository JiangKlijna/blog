﻿<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<title>${article.title}</title>
</head>
<%@ include file="js.jsp" %>
<link rel="stylesheet" href="static/css/article.css" />
<body>
<%@ include file="header.jsp" %>
<section id="main">
    <div id="article">
        <c:choose>
        <c:when test="${isExist}">
        <p class="text-center title">${article.title}</p><hr>
        <p class="text-center author">
            <a href="person.do?name=${article.username}"><span class="label label-primary">${article.username}</span></a>
            <span class="label label-success" id="articletime">${article.createtime}</span>
            <a href="subject.do?id=${article.subjectid}"><span class="label label-info">${article.subjectname}</span></a>
            <span class="label label-danger" id="favnum"><span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;<b>${article.favoritenumber}</b></span>
        </p><hr>
        ${article.content}
        </c:when>
        <c:otherwise>
            <p class="text-center" style="font-weight: 900; font-size: 20px;padding: 20px;">此文章不存在, <a href="./" class="text-primary">返回主页</a></p>
        </c:otherwise>
        </c:choose>
    </div>
    <hr>
    <div>
        <c:choose>
        <c:when test="${isLogin}">
            <div>
                <input id="article-comment" placeholder="写下你的评论...">&nbsp;&nbsp;
                <button id="article_comment_submit" type="button" class="btn btn-primary">评论</button>
            </div>
        </c:when>
        <c:otherwise>
            <div class="text-center"><a href="sign.do" class="btn btn-primary">登录</a><span>&nbsp;后发表评论</span></div>
        </c:otherwise>
        </c:choose>
    </div>
    <hr>
    <div id="comments"></div>
    <div id="loadmore">
        <p id="load_more_btn" class="text-center">查看更多</p>
        <hr>
    </div>
</section>
<%@ include file="footer.jsp" %>
<%@ include file="dialog.jsp" %>
<script>articleid=${article.id}</script>
<script src="static/js/article.js"></script>
</body>
</html>
