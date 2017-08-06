$('#fileupload').fileupload({
    url: path + '/sysUploadController/upload',
    autoUpload:true,
    sequentialUploads: true,
    add: function(e, data) {
        var filename = data.files[0].name;
        var fileListLenght = $('.filesName').find('tr').length;
        for (var i = 0; i < fileListLenght; i++) {
            if (filename == $('.filesName').find('.name').eq(i).text()) {
                $('.tips').text("不能重复上传！");
                return false;
            }
        }
        filesList = "filesList" + fileListLenght;
       	var filesListHTML = 
    	   '<tr class="' + filesList + '">' +
		        '<td colspan="3">' +
			        '<p class="name">' + filename + '&nbsp;&nbsp;<span class="displayProgress" style="color: green;">00%</span>' +'</p>' +
			        '<div class="progress progress-striped active" style="width: 98%">' +
				        '<div class="progress-bar progress-bar-success progress-bar-striped"' +  
				        	'role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 0%">'+
				        '</div>' +
			        '</div>' +
			        '<strong class="error"></strong>' +
		        '</td>' +
	        '</tr>';
        $('.uploadfiles').append(filesListHTML);
        data.context = $("." + filesList);
        data.submit();
    },
    //单个进度条
    progress: function(e, data) {
        var progress = parseInt(data.loaded / data.total * 100, 10);
        data.context.find(".displayProgress").text(progress + '%');
        data.context.find('.progress-bar').css('width', progress + '%');
    },
    //上传失败
    fail: function(e, data) {
        data.context.find('.error').text('上传失败');
    },
    //上传完成
    done : function(e, data) {
        //$('.filesName').find('.progress').parent().parent().remove();
        data.context.find('.progress').parent().parent().remove();
        $.each(data.files, function (index, file) {
            var res = data.result.split(",");
            if(res[0] == "success:"){
                var filesList = "filesList" + $('.filesName').find('tr').length;
                var filesListHTML =
                	'<tr class="' + filesList + '">' +
		                '<td>' +
		                	'<p class="name">' + res[3] + '</p>' +
		                '</td>' +
		                '<td>' +
		                	'<p class="size">' + res[4] + '</p>' +
		                '</td>' +
		                '<td class="btns">' +
			                '<button class="delete">删除</button>&nbsp;' +
			                '<button class="download">下载</button>' +
		                '</td>' +
	                '</tr>';
                $(".filesName").append(filesListHTML);
				//绑定删除
                $("." + filesList).find('.delete').click(function(){
					if(confirm('请注意，删除的附件将无法恢复，是否确认删除？')){
				        $.ajax({
                            async:false,
                            type:'post',
                            url:'',
                            dataType:'html',
                            data:'id=' + res[2],
                            success:function(msg){
                                $("."+filesList).remove();
                            }
						});
					}
                });
                $("."+filesList).find('.download').click(function(){
                    downloadFile(res[1],res[3]);
                });
            }
        });     
    }
});