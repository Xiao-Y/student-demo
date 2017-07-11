layui.config({
	base : path + '/plugins/layui/lay/modules/'
});
layui.use(['form','element'], function() {
	var $ = layui.jquery,
	form = layui.form(),
	element = layui.element(), //Tab的切换功能
	layer = parent.layer === undefined ? layui.layer : parent.layer;

	//监听提交
	form.on('submit(*)', function(data) {
		return submitFormNewTip(data);
	});
	
	$("#readMsgBt").on("click",function(){
		var url = path + "/activeMQ/readQueueMessage";
		$.post(url,function(data){
			if(data == ''){
				new TipBox({type:'error',str:'没有更多消息...',hasBtn:true})
			}else{
				$("#readMsgTx").text(data);
			}
		});
	})
});