/**
 * 对列表的操作
 */
$(function(){
	//对指定的行删除
	$("a[data-opt='del']").on('click',function(){
		var url = $(this).attr("url");
		layer.confirm('确定删除？',{icon: 2}, function(index){//yes
			$.ajax({
		        type : 'POST',
		        url : url,
		        dataType : 'json',
		        success : function(data) {
		         	tipsTableRB(data);
		        }
		    });
			layer.close(index);
		},function(index){//cancel
		  	layer.close(index);
		});
	});
});

function tipsTableRB(data){
	var message = data.message;
	var success = data.success;
	if(success === true){
		message = '<font color="#00CC00">' + message + '</font>';
	}else{
		message = '<font color="#FF0000">' + message + '</font>';
	}
	var content = '<div style="padding: 40px 80px;"><div style="height: 75px;">' + message + '</div></div>';
	layer.open({
		type : 1,
		offset : 'rb', // 具体配置参考：offset参数项
		content : content,
		time: 2000,//2s后自动关闭
		shade : 0,// 不显示遮罩
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