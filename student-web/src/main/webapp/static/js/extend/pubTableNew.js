/**
 * 对列表的操作
 */
$(function () {
    //对指定的行删除
    $("a[data-opt='del']").on('click', function () {
        var url = $(this).attr("url");
        var tipBox = null;
        new TipBox({
            type: 'confirm',
            str: '确定删除？',
            hasBtn: true,
            callBack: function () {
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    beforeSend: function (XHR) {
                        tipBox = new TipBox({type: 'load', str: "正在删除..."});
                    },
                    success: function (data) {
                        tipBox.close();
                        tipsTable(data);
                    }
                });
            }
        });
    });
});

//删除完成后的
function tipsTable(data) {
    var message = data.message;
    var type = data.type;
    new TipBox({
        type: type, str: message, hasBtn: true, setTime: 2500, callBack: function () {
            if (type === 'success') {
                location.reload();//刷新一下列表
                if (data.root) {//打开一个窗口
                    window.open(data.root);
                }
            }
        }
    });
}