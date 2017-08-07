
window.cj = {
    COMMENT_LIST_URL: "comment/listByArticle.json",
    WRITE_COMMENT_URL: "comment/write.json",
    onLoad: function() {
        cj.onLoadComments();
        $('#article_comment_submit').click(cj.onClickComment);
    },
    // 当点击评论时
    onClickComment() {
        var comment = $('#article-comment').val()
        if (comment == '') return
        $.post(cj.WRITE_COMMENT_URL, {'articleid': articleid, 'content': comment}, function (result) {
            if (result.code == 0) history.go();
            else dialog.info(result.message);
        });
    },
    // 当前页数
    pageNum: 0,
    // 加载10条评论
    onLoadComments() {
        $.post(cj.COMMENT_LIST_URL, {'articleid': articleid, 'perPage': 10, 'pageNum': cj.pageNum}, function (result){
            if (result.code == 0) cj.displayComments(result.data);
            else dialog.info(result.message);
        });
    },
    // 显示评论列表
    displayComments(comments) {
        cj.pageNum++;
        var $comments = $('#comments');
        for (var i in comments) {
            var html = cj.commentHtml(comments[i]);
            $comments.append(html);
        }
    },
    // 获得一条评论的html
    commentHtml(c) {
        return "<div class=\"comment\"><div><span><a href=\"person.do?name=" + c.username + "\">"
            + c.username + "</a></span><span>"
            + c.createtime + "</span></div><p class=\"content\">"
            + c.content + "</p><hr></div>";
    }

}
$(cj.onLoad)