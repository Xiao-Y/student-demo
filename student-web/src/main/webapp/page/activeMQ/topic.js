layui.config({
	base : path + '/plugins/layui/lay/modules/'
});
layui.use(['form','element'], function() {
	var $ = layui.jquery,
	form = layui.form(),
	element = layui.element(), //Tab的切换功能
	layer = parent.layer === undefined ? layui.layer : parent.layer;

	//监听提交
	form.on('submit(*)', function(data) {
		return submitFormNewTip(data);
	});
});