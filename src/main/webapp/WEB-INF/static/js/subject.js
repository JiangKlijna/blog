window.cj = {
    ARTICLE_LIST_URL: "article/listBySubject.json",
    onLoad: function (){
        cj.onLoadArticles();
        $('#load_more_btn').click(cj.onLoadArticles);
    },
    // 当前页数
    pageNum: 0,
    // 每页多少
    perPage: 10,
    // 当点及加载更多时 and 加载10条评论
    onLoadArticles() {
        $.post(cj.ARTICLE_LIST_URL, {'subjectid': subjectid, 'perPage': cj.perPage, 'pageNum': cj.pageNum}, function (result){
            if (result.code == 0) cj.displayArticles(result.data);
            else dialog.info(result.message);
        });
    },
    // 显示文章列表
    displayArticles(articles) {
        if (articles.length == 0) {
            $('#loadmore').hide();
            return;
        }
        cj.pageNum++;
        var $articles = $('#articles');
        for (var i in articles) {
            var html = cj.articleHtml(articles[i]);
            $articles.append(html);
        }
        if (articles.length < cj.perPage) { // 如果文章数小于期望的数量则隐藏加载更多
            $('#loadmore').hide();
            return;
        }
    },
    // 获得文章的html
    articleHtml(a) {
        return "<p>" + a.preview + "</p><hr>";
    },
}
$(cj.onLoad);