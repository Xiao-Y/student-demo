$(document).ready(function() {
 	$.ajaxSetup({  
	    async : false  
	}); 
    var uuid;//后台唯一值
    var count = 0;
    var arr = [];//用于记录所有定时器序号
    //显示二维码
    showQrGen();
    
    //绑定刷新
    $("#QrGen").on("click",function(){
    	debugger;
    	clearIntervals(arr);
	    showQrGen();
    });
    //显示二维码
    function showQrGen(){
    	console.info("***************");
    	var url = path + "/home/showQrGen";
	    $.get(url, function(data, status) {
	        var obj = eval("(" + data + ")");
	        //设置该uuid值
	        uuid = obj.uuid;
	        //设置二维码图片地址
	        $("#QrGen").attr("src", path + obj.img);
	        //检查验证登录
	        checkScan();
	    });
    }
    
    //轮询
	function checkScan() {
    	arr.push(setInterval(function() {
        	count++;
        	var flag = "";
            $.get(path + "/home/checkScan?uuid=" + uuid + "&count=" + count,
                function(data, status) {
            	    flag = data;
                    if (data == "ok") {
                        //验证成功并重定向到welcome页面
                        top.location = path + "/home/homeIndex?type=weichat";
                    }
            	});
	    	if (flag == 'invalid') {
	    		debugger;
	    		count = 0;
	    		clearIntervals(arr);
                $("#QrGen").attr("src", path + "/images/0617135536.png");
	    	}
        },1000));
    }
    
    //清除所有定时器 
    function clearIntervals(array){
    	debugger;
        for (var i = 0; i < array.length; i++) {
           window.clearInterval(array[i]);
        };
    }
});
