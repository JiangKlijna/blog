<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<%@ page isELIgnored="false"%>
<style>
#header_nav {
    width: 100%;
    position: fixed;
    top: 0;
    z-index: 1000;
}
#header_menu {
    width: 80%;
}
</style>
<nav class="navbar navbar-default" role="navigation"></nav>
<nav id="header_nav" class="navbar navbar-default" role="navigation">
    <div class="container-fluid" id="header_menu">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">sign</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Kotlin</a></li>
                <li><a href="#">GIT</a></li>
            </ul>
        </div>
    </div>
</nav>