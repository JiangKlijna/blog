<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<!-- dialog -->
<div id="dialog">
    <button id="dialog_show" data-toggle="modal" data-target="#dialog_modal" style="display: none"></button>
    <div class="modal fade" id="dialog_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" id="dialog_header_area">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title" id="dialog_title">标题</h3>
                </div>
                <div class="modal-body" id="dialog_body">内容</div>
                <div class="modal-footer" id="dialog_footer_area">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="dialog_primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
window.dialog = {
    error: function (msg) {
        dialog.toast('错误', msg);
    },
    info: function (msg) {
        dialog.toast('提示', msg);
    },
    toast: function (title, msg) {
        $('#dialog_header_area').show();
        $('#dialog_title').html(title);
        $('#dialog_body').html(msg);
        $('#dialog_footer_area').hide();
        if ($('#dialog_modal').css('display') == 'none')
            $('#dialog_show').click();
    },
    show: function (msg, primary, onClickPrimary) {
        $('#dialog_header_area').hide();
        $('#dialog_body').html(msg);
		$('#dialog_primary').text(primary).click(onClickPrimary)
        $('#dialog_footer_area').show();
        if ($('#dialog_modal').css('display') == 'none')
            $('#dialog_show').click();
    },
	dismiss: function () {
	    if ($('#dialog_modal').css('display') != 'none')
		    $('#dialog_show').click();
	}
}
</script>