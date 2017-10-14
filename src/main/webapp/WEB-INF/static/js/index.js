window.cj = {
    LIST_ARTICLE_INDEX_URL: 'article/listByIndex.json',
    LIST_SUBJECT_INDEX_URL: '',
    LIST_ARTICLE_FOLLOW_URL: '',
    LIST_SUBJECT_FOLLOW_URL: '',
    LIST_ARTICLE_SEARCH_URL: '',
    LIST_SUBJECT_SEARCH_URL: '',
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
        }

        $.post(url, obj, function (data) {

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
        }

        $.post(url, obj, function (data) {

        });
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
}
$(cj.onLoad)