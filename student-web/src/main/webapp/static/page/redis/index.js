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


	$("button[name='readMsgBt']").on("click",function(){
		var index = $("button[name='readMsgBt']").index(this);
        var key = $("input[name='key']").eq(index).val();
		var url = $(".pathUrl").eq(index).val() + "?key=" + key;
		console.info("url" + url);
		var layerIndex = layer.load();
		$.post(url,function(data){
			layer.close(layerIndex);
			if(data == ''){
				new TipBox({type:'error',str:'没有更多消息...',hasBtn:true})
			}else{
				$("[name='value']").eq(index).text(data);
			}
		});
	})
});