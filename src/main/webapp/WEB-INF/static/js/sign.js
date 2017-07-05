
window.cj = {
    SIGNIN_URL: 'user/login.json',
    SIGNUP_URL: 'user/regist.json',
    isSignIn: true,
    onLoad: function () {//注冊各种事件
        $('#title_signin').click(cj.onClickTitleSignin);
        $('#title_signup').click(cj.onClickTilteSignup);
        $('#btn_signin').click(cj.onClickSignin);
        $('#btn_signup').click(cj.onClickSignup).hide();
        $('#sign_username').bind('input propertychange', cj.onChangeInput);
        $('#sign_password').bind('input propertychange', cj.onChangeInput);

        $('#title_signin').css('color', '#ea6f5a').css("border-bottom","3px solid #ea6f5a");

        document.onkeydown = cj.onkeydown;
    },
    onkeydown: function(e) {
        if(e && e.keyCode == 13) {
            if (cj.isSignIn) cj.onClickSignin();
            else cj.onClickSignup();
        }
    },
    onClickSignin: function () {//当点击登陆按钮的时候触发
        var data = cj.getSignData();
        if (data) $.post(cj.SIGNIN_URL, data, function(result){
            if (result.code == 0) location = "./index.do";
            else cj.alert(result.message);
        });
    },
    onClickSignup: function () {//当点击登陆按钮的时候触发
        var data = cj.getSignData();
        if (data) $.post(cj.SIGNUP_URL, data, function(result){
            cj.alert(result.message);
            if (result.code == 0) {
                $('#title_signin').click();
            }
        });
    },
    onClickTitleSignin: function () {//当点击上方登陆按钮的时候触发
        $('#btn_signup').hide();
        $('#btn_signin').show();
        $('#title_signin').css('color', '#ea6f5a').css("border-bottom","3px solid #ea6f5a");
        $('#title_signup').removeAttr("style");
        cj.isSignIn = true;
    },
    onClickTilteSignup: function () {//当点击上方注册按钮的时候触发
        $('#btn_signup').show();
        $('#btn_signin').hide();
        $('#title_signup').css('color', '#ea6f5a').css("border-bottom","3px solid #ea6f5a");
        $('#title_signin').removeAttr("style");
        cj.isSignIn = false;
    },
    onChangeInput: function () {//当两个输入框内容改变的时候触发
        if($(this).val() == '')
            $(this).parent().addClass("has-error");
        else
            $(this).parent().removeClass("has-error");
    },
    getSignData: function () {
        var username = $('#sign_username').trigger('input').val();
        var password = $('#sign_password').trigger('input').val();
        if (username == '' || password == '') {
            cj.alert('用户名或密码不能为空');
            return null;
        }
        return {'username': username, 'password': password, 'action': 'json'};
    },
    alert: function (msg) {
        $('#alert').slideDown().html(msg);
        setTimeout("$('#alert').slideUp(1200)", 1200);
    }
}
$(cj.onLoad);