layui.config({
	base: path + '/static/plugins/layui/modules/'
});

layui.use(['laypage','layer'], function() {
	var $ = layui.jquery,
		laypage = layui.laypage,
		//form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer;
	/*//表单校验
	$('input').iCheck({
		checkboxClass: 'icheckbox_flat-green'
	});*/
	//分页
	laypage({
		cont: 'page',
		pages: $("#pages").val(), //总页数
		groups: 5, //连续显示分页数
		curr: $("#pageNum").val(), 
		jump: function(obj, first) {
			//得到了当前页，用于向服务端请求对应数据
			//var curr = obj.curr;
			if(!first) {
				//layer.msg('第 '+ obj.curr +' 页');
				location.href = path + '/sysActDeploy/queryDeployList?pageNo='+obj.curr;
			}
		}
	});

	$('#search').on('click', function() {
		parent.layer.alert('你点击了搜索按钮');
	});

	$("a[name='deploy']").on("click",function(){
		$this = $(this);
		var id = $this.attr("data-id");
		var name = $this.attr("data-name");
		var url = path + '/sysAct/deploy/' + name + "/" + id;
		$.ajax({
	        type : 'POST',
	        url : url,
	        dataType : 'json',
	        success : function(obj) {
				new TipBox({type:obj.type,str:obj.message,hasBtn:true,setTime:1500});
	        }
	    });
	});
});