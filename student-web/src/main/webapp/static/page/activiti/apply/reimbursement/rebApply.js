layui.config({
	base : path + '/static/plugins/layui/lay/modules/'
});
layui.use(['form', 'laydate'], function() {
	var $ = layui.jquery,
	form = layui.form(),
	laydate = layui.laydate(),
	layer = parent.layer === undefined ? layui.layer : parent.layer;

	//监听提交
	form.on('submit(*)', function(data) {
		return submitFormNewTip(data);
	});
});