layui.config({
	base : path + '/plugins/layui/modules/'
});

layui.use([ 'laypage', 'layer', 'laydate'], function() {
	var $ = layui.jquery, 
	laypage = layui.laypage, 
	laydate = layui.laydate(),
	layer = parent.layer === undefined ? layui.layer : parent.layer;
	//分页
	laypage({
		cont : 'page',
		pages : $("#pages").val(), //总页数
		groups : 5, //连续显示分页数
		curr : $("#pageNum").val(),
		jump : function(obj, first) {
			//得到了当前页，用于向服务端请求对应数据
			if (!first) {
				location.href = path + '/formkey/applyLeave/findLeaveList?pageNo=' + obj.curr;
			}
		}
	});

	$('#search').on('click', function() {
		parent.layer.alert('你点击了搜索按钮');
	});
});
