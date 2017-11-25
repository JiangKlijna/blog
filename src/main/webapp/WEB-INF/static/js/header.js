window.h = {
    LOGOUT_URL: "user/logout.json",
    MESSAGE_LIST_URL: "message/list.json",
    MESSAGE_READ_URL: "message/read.json",
    MESSAGE_UNREAD_COUNT_URL: "message/unread/count.json",
    init: function(){
        $('#nav_logout').click(h.logout);
        $('#nav_search').click(h.search).blur(h.searchBlur);

        // 浏览器关闭前,close websocket,google浏览器没用.
        // 不写这句也不影响,页面关闭时服务端会close
        $(window).bind('beforeunload', h.onBeforeUnload);
        // init websocket
        if (!isLogin) return;
        $('#header_nav #msg_loadmore').click(h.onLoadMessages);
        $('#header_nav').on("click", ".message", h.openMessage);
        h.getUnReadNum();
        h.onLoadMessages();

        h.ws = new WebSocket(h.getWsUrl());
        h.ws.onopen = h.onWsOpen;
        h.ws.onmessage = h.onWsMessage;
        h.ws.onerror = h.onWsError;
    },
    // websocket连接上的时候, 发送username
    onWsOpen: function(){
        h.ws.send(username);
    },
    // websocket接受消息, 并请求消息列表
    onWsMessage: function(msg){
        h.getUnReadNum();
        h.onLoadMessages();
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
    },
    // 获得消息未读数量
    getUnReadNum() {
        $.post(h.MESSAGE_UNREAD_COUNT_URL, {}, function (result){
            if (result.code == 0) {
                if (result.data > 0) $('#header_nav #unReadNum').html(result.data);
            } else dialog.info(result.message);
        });
    },
    // 当前页数
    pageNum: 0,
    // 每页多少
    perPage: 5,
    // 当点及加载更多时 and 加载5条消息
    onLoadMessages(e) {
        if (e) e.stopPropagation();
        $.post(h.MESSAGE_LIST_URL, {'perPage': h.perPage, 'pageNum': h.pageNum}, function (result){
            if (result.code == 0) h.displayMessages(result.data);
            else dialog.info(result.message);
        });
    },
    // 显示消息列表
    displayMessages(messages) {
        var $loadmore = $('#header_nav #msg_loadmore');
        if (messages.length == 0) {
            $loadmore.hide();
            return;
        }
        h.pageNum++;
        for (var i in messages) {
            var html = h.messageHtml(messages[i]);
            $loadmore.before(html);
        }
        if (messages.length < h.perPage) { // 如果消息数小于期望的数量则隐藏加载更多
            $loadmore.hide();
        }
    },
    // 获得消息的html
    messageHtml(m) {
        var msg_str = '';
        if(m.flag == 0) {
            msg_str = m.fromusername + "关注了你";
        } else if(m.flag == 1) {
            msg_str = m.fromusername + "评论了你的文章";
        } else if(m.flag == 2) {
            msg_str = m.fromusername + "发布了一篇文章";
        }
        if (!m.isread) msg_str += '<b>●</b>';
        return "<li class=\"message pointer\" data-id=\"" + m.id
            + "\" data-fromuser=\"" + m.fromusername
            + "\"><a>" + msg_str
            + "</a></li>";
    },
    // 打开message
    openMessage() {
        var dom = $(this);
        open('person.do?name=' + dom.data('fromuser'));
        $.post(h.MESSAGE_READ_URL, {messageid:dom.data('id')}, function(result){
            if (result.code == 0) {
                dom.find('b').hide();
                var $unReadNum = $('#header_nav #unReadNum');
                var count = parseInt($unReadNum.html()) - 1;
                $unReadNum.html(count);
                if (count == 0) $unReadNum.hide();
            } else dialog.info(result.message);
        })
    }

}
h.init();