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
				location.href = path + '/approvalLeave/findApprovalLeave?pageNo=' + obj.curr;
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

	//任务签收
	$("a[name='leaveClaim']").on('click',function(){
		var $this = $(this);
		var id = $this.attr("id");
		var taskId = $this.attr("taskId");
		var url = path + "/approvalLeave/leaveClaim/" + id + "/" + taskId;
		$.ajax({
	        type: "POST",
	        dataType: "json",
	        url: url,
	        success: function (obj) {
	        	var message = obj.message;
				var type = obj.type;
				var root = obj.root;
			 	if(type == 'success'){
		            new TipBox({type:type,str:message,hasBtn:true,setTime:1500000,callBack:function(){
		            	$(window.location).attr('href', path + root);
		            }});  
			 	}else{
		            new TipBox({type:type,str:message,hasBtn:true})  
			 	}
	        },
	        error: function(obj) {
	        	new TipBox({type:'error',str:'网络错误',hasBtn:true})  
	        }
	    });
	});
});
