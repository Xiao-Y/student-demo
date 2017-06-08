layui.config({
	base : path + '/plugins/layui/modules/'
});

layui.use([ 'laypage', 'layer' ], function() {
	var $ = layui.jquery, laypage = layui.laypage, layer = parent.layer === undefined ? layui.layer : parent.layer;
	//表单校验
	// 				$('input').iCheck({
	// 					checkboxClass: 'icheckbox_flat-green'
	// 				});
	//分页
	laypage({
		cont : 'page',
		pages : $("#pages").val(), //总页数
		groups : 5, //连续显示分页数
		curr : $("#pageNum").val(),
		jump : function(obj, first) {
			//得到了当前页，用于向服务端请求对应数据
			//var curr = obj.curr;
			if (!first) {
				//layer.msg('第 '+ obj.curr +' 页');
				location.href = path + '/sysMenu/menuManage?pageNo=' + obj.curr;
			}
		}
	});

	$('#search').on('click', function() {
		parent.layer.alert('你点击了搜索按钮');
	});

	$('#add').on('click', function() {
		$.get('temp/edit-form.html', null, function(form) {
			layer.open({
				type : 1,
				title : '添加表单',
				content : form,
				btn : [ '保存', '取消' ],
				area : [ '600px', '400px' ],
				maxmin : true,
				yes : function(index) {
					console.log(index);
				},
				full : function(elem) {
					var win = window.top === window.self ? window : parent.window;
					$(win).on('resize', function() {
						var $this = $(this);
						elem.width($this.width()).height($this.height()).css({
							top : 0,
							left : 0
						});
						elem.children('div.layui-layer-content').height($this.height() - 95);
					});
				}
			});
		});
	});

	$('#import').on('click', function() {
		var that = this;
		var index = layer.tips('只想提示地精准些', that, {
			tips : [ 1, 'white' ]
		});
		$('#layui-layer' + index).children('div.layui-layer-content').css('color', '#000000');
	});

	$('.site-table tbody tr').on('click', function(event) {
		var $this = $(this);
		var $input = $this.children('td').eq(0).find('input');
		$input.on('ifChecked', function(e) {
			$this.css('background-color', '#EEEEEE');
		});
		$input.on('ifUnchecked', function(e) {
			$this.removeAttr('style');
		});
		//$input.iCheck('toggle');
	}).find('input').each(function() {
		var $this = $(this);
		$this.on('ifChecked', function(e) {
			$this.parents('tr').css('background-color', '#EEEEEE');
		});
		$this.on('ifUnchecked', function(e) {
			$this.parents('tr').removeAttr('style');
		});
	});
	$('#selected-all').on('ifChanged', function(event) {
		var $input = $('.site-table tbody tr td').find('input');
		//$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
	});
});
