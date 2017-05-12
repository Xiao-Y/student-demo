layui.use('form', function() {
	var $ = layui.jquery,form = layui.form(),layer = parent.layer === undefined ? layui.layer : parent.layer;
	//自定义验证规则
	form.verify({
		title : function(value) {
			if (value.length < 5) {
				return '标题也太短了吧';
			}
		},
		pass : [ /(.+){6,12}$/, '密码必须6到12位' ]
	});

	//监听提交
	form.on('submit(*)', function(data) {
		console.log(data);
		layer.alert(JSON.stringify(data.field), {  
	      title: '最终的提交信息'  
	    })
		return false;
	});
});