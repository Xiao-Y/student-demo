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
		/*layer.alert(JSON.stringify(data.field), {  
	      title: '最终的提交信息'  
	    })*/
		var form = data.form;
	    var url = form.action;
	    var data = JSON.stringify(data.field);
	    //var data = $(this).serialize();
//		console.log(JSON.stringify(data.field));
//		console.log($(form).serialize());
		$.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: data,
            success: function (obj) {
            	tipsCenter(obj);
            },
            error: function(obj) {
            	layer.alert('网络错误', {  
			      title: '提示信息'  
			    });
            }
        });
		return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	
	function tipsCenter(data) {
		var message = data.message;
		var success = data.success;
		if (success === true) {
			message = '<font color="#00CC00">' + message + '</font>';
		} else {
			message = '<font color="#FF0000">' + message + '</font>';
		}
		var content = '<div style="padding: 40px 80px;">' + message + '</div>';
		layer.open({
			type : 1,
			offset : 'auto', // 具体配置参考：offset参数项
			content : content,
			time : 2000,// 2s后自动关闭
			shade : 0.5,// 不显示遮罩
			end : function () {
				if(success === true){
		            if(data.root){
		            	location.href = path + data.root;
		            }
				}
	        }
		});
	}
});