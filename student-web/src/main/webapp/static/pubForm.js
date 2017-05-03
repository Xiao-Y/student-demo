/**
  * 打开弹出层
  * @param {} param 请求弹出层参数
  * @param {} editUrl 请求弹出层url
  * @param {} saveUrl 提交的url
  */
function switchSubject(param,editUrl,saveUrl){
	$.get(editUrl, param, function(content) {
		layer.open({
			type: 1,
			title: '添加模板',
			content: content,
			btn: ['提交', '取消'],
			area: ['600px', '400px'],
			maxmin: true,
			yes: function(index,layero) {
				var data = layero.find("form").serialize();
				if(data){
					submitForm(data,saveUrl);
				}else{
					layer.msg("表单序列化错误！");
				}
				layer.close(index);
			},
			full: function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on('resize', function() {
					var $this = $(this);
					elem.width($this.width()).height($this.height()).css({
						top: 0,
						left: 0
					});
					elem.children('div.layui-layer-content').height($this.height() - 95);
				});
			}
		});
	});
}
/**
  * 打开弹出层
  * @param {} param 请求弹出层参数
  * @param {} fun 回调函数
  * @param {} editUrl 请求弹出层url
  * @param {} saveUrl 提交的url
  */
function switchSubjectFun(param,fun,editUrl,saveUrl){
	$.get(editUrl, param, function(content) {
		layer.open({
			type: 1,
			title: '添加模板',
			content: content,
			btn: ['提交', '取消'],
			area: ['600px', '400px'],
			maxmin: true,
			yes: function(index,layero) {
				var data = layero.find("form").serialize();
				if(data){
					fun(data,saveUrl);
				}else{
					layer.msg("表单序列化错误！");
				}
				layer.close(index);
			},
			full: function(elem) {
				var win = window.top === window.self ? window : parent.window;
				$(win).on('resize', function() {
					var $this = $(this);
					elem.width($this.width()).height($this.height()).css({
						top: 0,
						left: 0
					});
					elem.children('div.layui-layer-content').height($this.height() - 95);
				});
			}
		});
	});
}

/**
 * 提交表单
 * @param {} data
 */
function submitForm(formInfo,saveUrl){
	$.ajax({
        type : 'POST',
        url : saveUrl,
        data : formInfo,
        dataType : 'json',
        success : function(data) {
         	tipsRB(data);
        }
    });
}

/**
 * 提示信息
 * @param {} info
 */
function tipsRB(data){
	var message = data.message;
	var success = data.success;
	if(success === true){
		message = '<font color="#00CC00">' + message + '</font>';
	}else{
		message = '<font color="#FF0000">' + message + '</font>';
	}
	var content = '<div style="padding: 20px 80px;">' + message + '</div>';
	layer.open({
		type : 1,
		offset : 'rb', // 具体配置参考：offset参数项
		content : content,
		time: 3000,//2s后自动关闭
		//btn : '关闭全部',
		//btnAlign : 'c', // 按钮居中
		shade : 0,// 不显示遮罩
		//yes : function() {
		//	layer.closeAll();
		//},
		end : function () {
			if(success === true){
	            location.reload();//刷新一下列表
	            if(data.root){
	            	window.open(data.root);
	            }
			}
        }
	});
}