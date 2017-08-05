layui.use('form', function() {
	var $ = layui.jquery,form = layui.form(),layer = parent.layer === undefined ? layui.layer : parent.layer;
	//自定义验证规则
	form.verify({
		bean : function(value) {
			var beanClass = $("#beanClass").val();
			var springId = $("#springId").val();
			if (beanClass == '' && springId == '') {
				return 'beanClass和springId必填一个';
			}
		}
	});

	//监听提交
	form.on('submit(*)', function(data) {
		return submitFormNewTip(data);
	});
	
	//用于显示radio的选种
	var arry = ['spread_radio','validind_radio','pid_select'];
	pubPopForm.checkedDisplay(arry,form);
});