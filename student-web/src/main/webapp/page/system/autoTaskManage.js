layui.config({
	base : path + '/static/plugins/layui/modules/'
});

layui.use(['laypage', 'layer', 'form'], function() {
	var $ = layui.jquery, laypage = layui.laypage,form = layui.form(), layer = parent.layer === undefined
			? layui.layer
			: parent.layer;
	// 分页
	laypage({
		cont : 'page',
		pages : $("#pages").val(), // 总页数
		groups : 5, // 连续显示分页数
		curr : $("#pageNum").val(),
		jump : function(obj, first) {
			// 得到了当前页，用于向服务端请求对应数据
			if (!first) {
				location.href = path
						+ '/sysAutoTask/findAutoTask?pageNo='
						+ obj.curr;
			}
		}
	});
});