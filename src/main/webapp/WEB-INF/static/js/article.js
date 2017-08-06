
window.cj = {
    WRITE_COMMENT_URL: "comment/write.json",
    onLoad: function() {
        $('#article_comment_submit').click(cj.onClickComment);
    },
    onClickComment() {
        var comment = $('#article-comment').val()
        if (comment == '') return
        $.post(cj.WRITE_COMMENT_URL, {'articleid': articleid, 'content': comment}, function (result) {
            if (result.code == 0) history.go();
            else dialog.info(result.message);
        });
    }
}
$(cj.onLoad)