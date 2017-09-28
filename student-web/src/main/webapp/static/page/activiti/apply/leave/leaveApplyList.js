layui.config({
	base : path + '/static/plugins/layui/modules/'
});

layui.use([ 'laypage', 'layer','form', 'laydate'], function() {
	var $ = layui.jquery, 
	laypage = layui.laypage, 
	form = layui.form(),
	laydate = layui.laydate(),
	layer = parent.layer === undefined ? layui.layer : parent.layer;
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
				location.href = path + '/applyLeave/findLeaveList?pageNo=' + obj.curr;
			}
		}
	});

	$('#search').on('click', function() {
		parent.layer.alert('你点击了搜索按钮');
	});
	//添加申请
	$('#add').on('click', function() {
		var editUrl = path + '/applyLeave/editLeave';
		var saveUrl = path + '/applyLeave/saveLeave';
		switchSubject(null,editUrl,saveUrl);
		//form.render();
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
