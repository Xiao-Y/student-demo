layui.config({
	base : path + '/plugins/layui/modules/'
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
	
	//绑定开头事件
	form.on('switch', function(field){
		var checked = field.elem.checked;
		var jobId = field.elem.value;
		//checked true 表示启用，否则禁用
		var jobStatus = checked === true ? 1 : 0;
		var url = path + "/sysAutoTask/updateJobStatus/" + jobId;
		$.post(
			url,
			{jobStatus : jobStatus},
			function(data, status){
				if(data.success === false){
					if(checked === true){
						field.elem.checked = false;
					}else{
						field.elem.checked = true;
					}
				}
				form.render('checkbox');
				tipsRB(data);
			});
	});
	
	/**
	 * 提示信息
	 * 
	 * @param {}
	 *            info
	 */
	function tipsRB(data) {
		var message = data.message;
		var success = data.success;
		if (success === true) {
			message = '<font color="#00CC00">' + message + '</font>';
		} else {
			message = '<font color="#FF0000">' + message + '</font>';
		}
		var content = '<div style="padding: 40px 80px;"><div style="height: 75px;">'
				+ message + '</div></div>';
		layer.open({
			type : 1,
			offset : 'rb', // 具体配置参考：offset参数项
			content : content,
			time : 2000,// 2s后自动关闭
			shade : 0// 不显示遮罩
		});
	}
});