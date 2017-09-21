window.cj = {


    onLoad: function () {
        $('#article_load_more').click(cj.onClickArticleLoadMore);
        $('#subject_load_more').click(cj.onClickSubjectLoadMore);
    },
    // 当点击#articles #article_load_more
    onClickArticleLoadMore: function () {
    },
    // 当点击#subjects #subject_load_more
    onClickSubjectLoadMore: function () {
    },
}
$(cj.onLoad)