<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>登陆</title>
</head>
<%@ include file="js.jsp" %>
<link rel="stylesheet" href="static/css/sign.css" />
<body>
<section class="hundred">
    <br />
    <div id="sign-div">
        <div id="alert" class="alert alert-danger"></div>
        <div id="title">
            <a id="title_signin" href="#">登录</a>
            <b>·</b>
            <a id="title_signup" href="#">注册</a>
        </div><br>
        <div class="input-group">
            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
            <input id="sign_username" type="text" class="form-control" placeholder="请输入用户名">
        </div><br>
        <div class="input-group">
            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
            <input id="sign_password" type="password" class="form-control" placeholder="请输入密码">
        </div><br>
        <Button id="btn_signin">登录</button>
        <Button id="btn_signup">注册</button>
    </div>
</section>
<script src="static/js/sign.js"></script>
</body>
</html>
