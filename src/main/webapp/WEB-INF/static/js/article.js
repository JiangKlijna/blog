window.cj = {
    COMMENT_LIST_URL: "comment/listByArticle.json",
    WRITE_COMMENT_URL: "comment/write.json",
    onLoad: function() {
        cj.onLoadComments();
        $('#article_comment_submit').click(cj.onClickComment);
        $('#load_more_btn').click(cj.onLoadComments);
    },
    // 当点击评论时
    onClickComment: function (){
        var comment = $('#article-comment').val()
        if (comment == '') return
        $.post(cj.WRITE_COMMENT_URL, {'articleid': articleid, 'content': comment}, function (result) {
            if (result.code == 0) history.go();
            else dialog.info(result.message);
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
        return "<div class=\"comment\"><div><span class=\"username\"><a href=\"person.do?name=" + c.username + "\">"
            + c.username + "</a></span><span class=\"time\">"
            + cj.timestampToString(c.createtime) + "</span></div><p class=\"content\">"
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