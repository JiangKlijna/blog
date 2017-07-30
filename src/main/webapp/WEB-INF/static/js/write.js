
window.cj = {
    PUBLISH_URL: 'article/publish.json',
    $title: $('#write_title'),
    $subject: $('#write_subject'),
    $content: $('#write_content'),
    onLoad: function() {
        // 开启富文本编辑模式
        cj.$content.notebook();
        // 设置btn的点击事件
        $('#btn_submit').click(cj.submit);
        $('#btn_clear').click(cj.clear);
    },
    submit: function() {
        dialog.show('是否发布内容', '发布', function () {
            cj.remove_content_attr();
            var content_text = cj.content_text()
            var obj  = {'content': cj.$content.html(),
                        'preview': content_text.substr(0, 80),
                        'title': cj.$title.val(),
                        'subject': cj.$subject.val(),
                        'numberofwords': content_text.length};
            $.post(cj.PUBLISH_URL, obj, function (result) {
                dialog.dismiss();
                if (result.code == 0) location = "./person.do?name=" + un;
                else dialog.error(result.message);
            });
        });
    },
    clear: function() {
        dialog.show('是否清空内容', '清空', function () {
            cj.$title.val('');
            cj.$subject.val('');
            cj.$content.html('<h4>请输入正文</h4>');
            dialog.dismiss();
        });
    },
    // 在发布文章之前,先删掉content html里面无用的属性
    remove_content_attr: function() {
        $('#write_content *').removeAttr('contenteditable');
    },
    // 获得content的纯文本数据
    content_text: function() {
        return cj.$content.text().trim()
    }
}
$(cj.onLoad);
