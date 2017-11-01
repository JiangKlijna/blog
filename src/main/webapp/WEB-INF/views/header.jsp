<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!-- header的css -->
<style>
* {
    padding: 0;
    margin: 0;
}
html, body, .hundred {
    width: 100%;
    height: 100%;
}
#header_nav {
    width: 100%;
    position: fixed;
    top: 0;
    z-index: 1000;
}
#header_menu {
    width: 80%;
}
.navbar-nav {
    padding-left: 100px;
    padding-right: 100px;
    width: 100%;
}
#nav_right {
    float: right;
}
.input-group {
    margin-left: 50px;
    width: 280px;
    margin-top: 3%;
}
.nav_middle {
    margin-top: 6%;
}
.nav_title:hover {
    color: black;
    background-color: #e7e7e7;
}
</style>
<nav class="navbar navbar-default"></nav>
<nav id="header_nav" class="navbar navbar-default">
    <div class="container-fluid" id="header_menu">
        <div>
            <ul class="nav navbar-nav">

                <li class="nav_title"><a href="index.do">主页</a></li>
                <li class="nav_title"><a href="follow.do">关注</a></li>
                <li>
                    <form action="search.do"><div class="input-group">
                        <input id="nav_text" type="text" class="form-control" name="query">
                        <span class="input-group-btn">
                        <button id="nav_search" class="btn btn-info" type="button"><span class="glyphicon glyphicon-search"></span>搜索</button>
                        </span>
                    </div></form>
                </li>
                <div id="nav_right">
                <c:choose>
                <c:when test="${isLogin}">
                    <div class="btn-group nav_middle">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-bell"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu"></ul>
                    </div>
                    <div class="btn-group nav_middle">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-user"></span>&nbsp;${username}&nbsp;<span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="person.do?name=${username}">个人中心</a></li>
                            <li class="divider"></li>
                            <li><a href="write.do">写文章</a></li>
                            <li class="divider"></li>
                            <li><a href="#" id="nav_logout">注销</a></li>
                        </ul>
                    </div>
                </c:when>
                <c:otherwise>
                    <a type="button" class="btn btn-success navbar-btn" href="sign.do">登陆</a>
                    <a type="button" class="btn btn-danger navbar-btn" href="sign.do?type=signup">注册</a>
                </c:otherwise>
                </c:choose>
                <div>
            </ul>
        </div>
    </div>
</nav>
<!-- header的js -->
<script>username = '${username}'; isLogin = ${isLogin}</script>
<script src="static/js/header.js"></script>