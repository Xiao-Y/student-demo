layui.use([ 'layer', 'form' ], function() {
	var layer = layui.layer, $ = layui.jquery, form = layui.form();
	form.on('submit(login)', function(data) {
		return true;
	});
	
	$("#weixin").on("click", function() {
		layer.open({
			title : "",
			type : 2,//1-文字内容，2-url链接
			area : [ '360px', '400px' ],
			offset : 'auto', // 具体配置参考：offset参数项
			content : path + "/home/showWeiChat",
			time : 0,
			shade : 0.5
		});
	})
});
