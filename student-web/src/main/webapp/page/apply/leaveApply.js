layui.config({
	base : path + '/plugins/layui/lay/modules/'
});
layui.use(['form', 'laydate'], function() {
	var $ = layui.jquery,
	form = layui.form(),
	laydate = layui.laydate(),
	layer = parent.layer === undefined ? layui.layer : parent.layer;

	//监听提交
	form.on('submit(*)', function(data) {
		var form = data.form;
	    var url = form.action;
		var data = $(form).serialize();
		$.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: data,
            success: function (obj) {
            	var message = obj.message;
				var type = obj.type;
				new TipBox({type:type,str:message,hasBtn:true});
				if(type == 'success'){
					$("#reset").click();
				}
            },
            error: function(obj) {
            	layer.alert('网络错误', {
			      title: '提示信息'  
			    });
            }
        });
		return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
});