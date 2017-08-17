window.cj = {
    COMMENT_LIST_URL: "comment/listByArticle.json",
    WRITE_COMMENT_URL: "comment/write.json",
    DELETE_COMMENT_URL: "comment/delete.json",
    FOLLOW_ARTICLE_URL: "article/follow.json",
    onLoad: function() {
        cj.onLoadComments();
        $('#article_comment_submit').click(cj.onClickComment);
        $('#load_more_btn').click(cj.onLoadComments);
        $('#favnum').click(cj.onClickFavNum);
        $('#articletime').html(cj.timestampToString(parseInt($('#articletime').html())));
    },
    // 当点击点赞时, 只能点击一次
    onClickFavNum: function (){
        if (!isLogin) {
            dialog.show("请先登录", "登录", function(){location='sign.do'});
            return;
        }
        if (cj.isClickFavNum) return;
        cj.isClickFavNum = true
        $.post(cj.FOLLOW_ARTICLE_URL, {'articleid': articleid}, function (result) {
            if (result.code == 0) {
                var $b = $('#favnum b');

                $b.html(parseInt($b.html()) + 1);

            } else dialog.info(result.message);
        });

    },
    // 当点击评论时
    onClickComment: function (){
        var comment = $('#article-comment').val()
        if (comment == '') return dialog.info("评论不能为空");
        if (comment.length > 20) return dialog.info("评论不能超过20个字");
        $.post(cj.WRITE_COMMENT_URL, {'articleid': articleid, 'content': comment}, function (result) {
            if (result.code == 0) history.go();
            else dialog.info(result.message);
        });
    },
    // 当点击删除评论时
    onDelComment: function (commentid){
        dialog.show("删除评论", "删除", function () {
            $.post(cj.DELETE_COMMENT_URL, {'commentid': commentid}, function (result){
                if (result.code == 0) history.go();
                else dialog.info(result.message);
            });
        });
    },
    // 当前页数
    pageNum: 0,
    // 每页多少
    perPage: 10,
    // 当点及加载更多时 and 加载10条评论
    onLoadComments() {
        $.post(cj.COMMENT_LIST_URL, {'articleid': articleid, 'perPage': cj.perPage, 'pageNum': cj.pageNum}, function (result){
            if (result.code == 0) cj.displayComments(result.data);
            else dialog.info(result.message);
        });
    },
    // 显示评论列表
    displayComments(comments) {
        if (comments.length == 0) {
            $('#loadmore').hide();
            return;
        }
        cj.pageNum++;
        var $comments = $('#comments');
        for (var i in comments) {
            var html = cj.commentHtml(comments[i]);
            $comments.append(html);
        }
        if (comments.length < cj.perPage) { // 如果评论数小于期望的数量则隐藏加载更多
            $('#loadmore').hide();
            return;
        }
    },
    // 获得一条评论的html
    commentHtml(c) {
        var deleteHtml = c.username != username ? "" : "<span class=\"delete\"><a href\"#\" onclick=\"cj.onDelComment("+c.id+")\">删除</a></span>";
        return "<div class=\"comment\"><div><span class=\"username\"><a href=\"person.do?name=" + c.username + "\">"
            + c.username + "</a></span><span class=\"time\">"
            + cj.timestampToString(c.createtime) + "</span>" + deleteHtml
            + "</div><p class=\"content\">"
            + c.content + "</p><hr></div>";
    },
    // 时间戳转换字符串
    timestampToString(time) {
        var d = new Date(time);
        return (d.getYear() + 1900) + "-" +
                (d.getMonth() + 1) + "-" +
                d.getDate() + " " +
                d.getHours() + ":" +
                d.getMinutes();
    }

}
$(cj.onLoad)