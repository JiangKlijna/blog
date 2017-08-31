window.h = {
    LOGOUT_URL: "user/logout.json",
    init: function(){
        $('#nav_logout').click(h.logout);
    },
    // 注销
    logout: function() {
        $.post(h.LOGOUT_URL,{"username": username}, function(result){
            if (result.code == 0) history.go();
            else dialog.info(result.message);
        });
    }

}
h.init();