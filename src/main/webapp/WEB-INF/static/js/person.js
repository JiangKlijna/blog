
window.cj = {
    FOLLOW_USER_URL: "user/follow.json",
    onLoad: function (){


        $('.follow').click(cj.onClickFollow);
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
                var $numofollow = $('#numofollow');
                if (result.data == 1) {
                    $follow.html("取消关注");
                } else {
                    $follow.html("关注");
                }
            } else dialog.info(result.message);
        });

    },
}
$(cj.onLoad)