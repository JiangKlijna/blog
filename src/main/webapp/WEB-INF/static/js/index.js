window.cj = {
    LIST_ARTICLE_INDEX_URL: 'article/listByIndex.json',
    LIST_SUBJECT_INDEX_URL: 'subject/listByIndex.json',
    LIST_ARTICLE_FOLLOW_URL: 'article/listByFollow.json',
    LIST_SUBJECT_FOLLOW_URL: 'subject/listByFollow.json',
    LIST_ARTICLE_SEARCH_URL: 'article/listBySearch.json',
    LIST_SUBJECT_SEARCH_URL: 'subject/listBySearch.json',
    // 当前页数 article
    a_pageNum: 0,
    // 每页多少 article
    a_perPage: 10,
    // 当前页数 subject
    s_pageNum: 0,
    // 每页多少 subject
    s_perPage: 10,
    onLoad: function () {
        cj.onClickArticleLoadMore();
        cj.onClickSubjectLoadMore();
        $('#article_load_more').click(cj.onClickArticleLoadMore);
        $('#subject_load_more').click(cj.onClickSubjectLoadMore);
        $(document).on("click", ".article .pointer", cj.openArticle);
    },
    // 当点击#articles #article_load_more
    onClickArticleLoadMore: function () {
        var url = null;
        var obj = {'perPage': cj.a_perPage, 'pageNum': cj.a_pageNum};
        if (type === 'index') {
            url = cj.LIST_ARTICLE_INDEX_URL;
        } else if (type === 'follow') {
            url = cj.LIST_ARTICLE_FOLLOW_URL;
        } else {
            url = cj.LIST_ARTICLE_SEARCH_URL;
            obj.search = type;
        }
        $.post(url, obj, function (result) {
            if (result.code == 0) cj.displayArticles(result.data);
            else dialog.info(result.message);
        });
    },
    // 当点击#subjects #subject_load_more
    onClickSubjectLoadMore: function () {
        var url = null;
        var obj = {'perPage': cj.s_perPage, 'pageNum': cj.s_pageNum};
        if (type === 'index') {
            url = cj.LIST_SUBJECT_INDEX_URL;
        } else if (type === 'follow') {
            url = cj.LIST_SUBJECT_FOLLOW_URL;
        } else {
            url = cj.LIST_SUBJECT_SEARCH_URL;
            obj.search = type;
        }

        $.post(url, obj, function (result) {
            if (result.code == 0) cj.displaySubjects(result.data);
            else dialog.info(result.message);
        });
    },
    // 显示文章列表
    displayArticles(articles) {
        if (articles.length == 0) {
            $('#article_load_more').html("没有数据").unbind("");
            return;
        }
        cj.a_pageNum++;
        var $articles = $('#articles');
        for (var i in articles) {
            var html = cj.articleHtml(articles[i]);
            $articles.prepend(html);
        }
         // 如果文章数小于期望的数量则隐藏加载更多
        if (articles.length < cj.a_perPage) {
            $('#article_load_more').html("没有数据").unbind("");
            return;
        }
    },
    // 获得文章的html
    articleHtml(a) {
        return "<div class=\"article\" data-id=\"" + a.id
            + "\"><p><span class=\"atitle pointer\">" + a.title
            + "<span></p><p><span class=\"content pointer\">"
            + a.preview + "</span><span class=\"readmore pointer\">...查看全文</span></p><p><span class=\"username\"><a href=\"person.do?name="
            + a.username + "\">" + a.username
            + "</a></span><span class=\"time\">"
            + cj.timestampToString(a.createtime)
            + "</span><span class=\"right pointer\">" + a.numberOfComments
            + "条评论</span><span class=\"right pointer\">" + a.favoritenumber
            + " 赞</span></p><hr></div>";
    },
    // 显示主题列表
    displaySubjects(subjects) {
        if (subjects.length == 0) {
            $('#subject_load_more').html("没有数据").unbind("");
            return;
        }
        cj.s_pageNum++;
        var $subjects = $('#subjects');
        for (var i in subjects) {
            var html = cj.subjectHtml(subjects[i]);
            $subjects.prepend(html);
        }
         // 如果文章数小于期望的数量则隐藏加载更多
        if (subjects.length < cj.s_perPage) {
            $('#subject_load_more').html("没有数据").unbind("");
            return;
        }
    },
    // 获得文章的html
    subjectHtml(s) {
        return "<div class=\"subject\"><a href='subject.do?id=" + s.id
            + "'><span class=\"badge pull-right\">" + s.numberOfArticles
            + "</span>" + s.title + "</a><hr></div>";
    },
    // 时间戳转换字符串
    timestampToString(time) {
        var d = new Date(time);
        return (d.getYear() + 1900) + "-" +
                (d.getMonth() + 1) + "-" +
                d.getDate() + " " +
                d.getHours() + ":" +
                d.getMinutes();
    },
    // 打开article
    openArticle() {
        location = "article.do?id=" + $(this).parent().parent().data("id");
    },
}
$(cj.onLoad)