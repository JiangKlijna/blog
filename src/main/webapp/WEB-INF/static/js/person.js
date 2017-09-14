window.cj = {
    ARTICLE_LIST_URL: "article/listByUser.json",
    FOLLOW_USER_LIST_URL: "user/listByFollowUser.json",
    USER_FOLLOW_LIST_URL: "user/listByUserFollow.json",
    FOLLOW_USER_URL: "user/follow.json",
    onLoad: function (){
        if (!isExist) return;
        cj.onLoadMore();
        $('#load_more_btn').click(cj.onLoadMore);
        $('.label.pointer').click(cj.onClickLabel);

        $(document).on("click", ".follow", cj.onClickFollow);
        $(document).on("click", ".article .pointer", cj.openArticle);
        $(document).on("click", ".user .pointer", cj.openUser);
    },
    //当点击label
    onClickLabel: function (){
        var type = $(this).data("type");
        if (type == cj.type) return;


    },
    //当点击关注的时候
    onClickFollow: function (){
        if (!isLogin) {
            dialog.show("请先登录", "登录", function(){location='sign.do'});
            return;
        }
        var $follow = $(this);
        $.post(cj.FOLLOW_USER_URL, {'tousername': $(this).data('name')}, function (result) {
            if (result.code == 0) {
                if (result.data == 1) {
                    $follow.html("取消关注");
                } else {
                    $follow.html("关注");
                }
            } else dialog.info(result.message);
        });
    },
    // 0 is 文章 1 is 粉丝 2 is 关注
    type: 2,
    // 当前页数
    pageNum: 0,
    // 每页多少
    perPage: 10,
    // 加载更多
    onLoadMore: function() {
        if (cj.type == 0) { // 加载文章
            $.post(cj.ARTICLE_LIST_URL, {'username': name, 'perPage': cj.perPage, 'pageNum': cj.pageNum}, function (result){
                if (result.code == 0) cj.displayArticles(result.data);
                else dialog.info(result.message);
            });
        } else if (cj.type == 1) { // 加载粉丝
            $.post(cj.USER_FOLLOW_LIST_URL, {'userid': userid, 'perPage': cj.perPage, 'pageNum': cj.pageNum}, function (result){
                if (result.code == 0) cj.displayUsers(result.data);
                else dialog.info(result.message);
            });
        } else { // 加载关注
            $.post(cj.FOLLOW_USER_LIST_URL, {'userid': userid, 'perPage': cj.perPage, 'pageNum': cj.pageNum}, function (result){
                if (result.code == 0) cj.displayUsers(result.data);
                else dialog.info(result.message);
            });
        }
    },
    // 显示文章列表
    displayArticles(articles) {
        if (articles.length == 0) {
            $('#loadmore').hide();
            return;
        }
        cj.pageNum++;
        var $articles = $('#listview');
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
    // 显示用户列表
    displayUsers(users) {
        if (users.length == 0) {
            $('#loadmore').hide();
            return;
        }
        cj.pageNum++;
        var $users = $('#listview');
        for (var i in users) {
            var html = cj.userHtml(users[i]);
            $users.append(html);
        }
        if (users.length < cj.perPage) { // 如果用户数小于期望的数量则隐藏加载更多
            $('#loadmore').hide();
            return;
        }
    },

    // 获得用户的html
    userHtml(u) {
        if (u.favoriteNumber == null) u.favoriteNumber = 0;
        if (u.numberOfWords == null) u.numberOfWords = 0;
        var isFollow = u.follow ? "取消关注" : "关注";
        var follow_html = u.username == username ? "" : "<button class=\"follow\" data-name=\"" + u.username
                                                                     + "\">" + isFollow
                                                                     + "</button>"
        return "<div class=\"user\" data-name=\"" + u.username
            + "\"><span class=\"pointer name\">" + u.username
            + "</span><span class=\"label label-primary pointer\">文章:" + u.numberOfArticles
            + "</span><span class=\"label label-info pointer\">粉丝:" + u.numberOfFans
            + "</span><span class=\"label label-warning pointer\">关注:" + u.numberOfConcerns
            + "</span><span class=\"label label-success pointer\">喜欢:" + u.favoriteNumber
            + "</span><span class=\"label label-danger pointer\">字数:" + u.numberOfWords
            + "</span>" + follow_html
            + "<hr></div>";
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
    // 打开user
    openUser() {
        location = "person.do?name=" + $(this).parent().data("name");
    }
}
$(cj.onLoad)