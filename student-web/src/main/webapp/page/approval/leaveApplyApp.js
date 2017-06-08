layui.use(['form', 'laydate'], function() {
	var $ = layui.jquery,
	form = layui.form(),
	laydate = layui.laydate(),
	layer = parent.layer === undefined ? layui.layer : parent.layer;

	//监听提交
	form.on('submit(*)', function(data) {
		var form = data.form;
	    var url = form.action;
		var data = $(form).serialize();
		var redrect = false;
		$.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            async: false,
            data: data,
            success: function (obj) {
            	var message = obj.message;
				var type = obj.type;
				var root = obj.root;
			 	if(type == 'success'){
		            new TipBox({type:type,str:message,hasBtn:true,setTime:1500,callBack:function(){
		            	$(window.location).attr('href', path + root);
		            }});  
			 	}else{
		            new TipBox({type:type,str:message,hasBtn:true})  
			 	}
            },
            error: function(obj) {
            	layer.alert('网络错误', {
			      title: '提示信息'  
			    });
            }
        });
		return false;
	});
});