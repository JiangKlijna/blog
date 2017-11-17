window.h = {
    LOGOUT_URL: "user/logout.json",
    init: function(){
        $('#nav_logout').click(h.logout);
        $('#nav_search').click(h.search).blur(h.searchBlur);

        // 浏览器关闭前,close websocket,google浏览器没用.
        // 不写这句也不影响,页面关闭时服务端会close
        $(window).bind('beforeunload', h.onBeforeUnload);
        // init websocket
        if (!isLogin) return;
        h.ws = new WebSocket(h.getWsUrl());
        h.ws.onopen = h.onWsOpen;
        h.ws.onmessage = h.onWsMessage;
        h.ws.onerror = h.onWsError;
    },
    // websocket连接上的时候, 发送username
    onWsOpen: function(){
        h.ws.send(username);
    },
    // websocket接受消息
    onWsMessage: function(msg){
        console.log(msg);
    },
    // websocket错误处理
    onWsError: function(){
        h.ws.close();
    },
    // 注销
    logout: function() {
        $.post(h.LOGOUT_URL,{"username": username}, function(result){
            if (result.code == 0) history.go();
            else dialog.info(result.message);
        });
    },
    // 搜索
    search: function() {
        var q = $('#nav_text').val();
        var form = $(this).parent().parent().parent();
        if (q == '') return form.addClass('has-error');
        form.submit();
    },
    // 搜索btn是去焦点
    searchBlur: function() {
        var form = $(this).parent().parent().parent();
        form.removeClass('has-error');
    },
    // 浏览器关闭前
    onBeforeUnload: function() {
        h.ws.close();
    },
    // websocket url
    getWsUrl: function() {
        return "ws://" + location.host + "/blog/echo.ws";
    }

}
h.init();