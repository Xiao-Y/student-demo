$("input[name='file']").fileupload({
    url: path + '/sysUploadController/upload',
    autoUpload:true,
    sequentialUploads: true,
    add: function(e, data) {
        var filename = data.files[0].name;
        var fileListLenght = $('.filesName').find('tr').length;
        filesList = "filesList" + fileListLenght;
       	var filesListHTML = 
    	   '<tr class="' + filesList + '">' +
		        '<td colspan="6">' +
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
        var fileTpye = $(this).attr('fileType');
        var url = path + '/sysUploadController/upload?fileType=' + fileTpye;
        $(this).fileupload('option', 'url', url);
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
        data.context.find('.progress').parent().parent().remove();
        $.each(data.files, function (index, file) {
            var res = data.result.split(",");
            if(res[0] == "success:"){
                var filesList = res[1];
                var filesListHTML =
                	'<tr class="' + filesList + '">' +
		                '<td><p class="fileName">' + res[2] + '</p></td>' +
                        '<td><p class="fileType">' + res[3] + '</p></td>' +
		                '<td><p class="fileSize">' + res[4] + '</p></td>' +
                        '<td><p class="createCode">' + res[5] + '</p></td>' +
                        '<td><p class="createTime">' + res[6] + '</p></td>' +
		                '<td class="btns">' +
			                '<button class="delete" name="deleteFile">删除</button>&nbsp;' +
			                '<button class="download">下载</button>' +
		                '</td>' +
	                '</tr>';
                $(".filesName").append(filesListHTML);
				//绑定删除
                $("." + filesList).find('[name="deleteFile"]').click(function(){
                    deleteFile(res[1],res[2]);
                });
                $("."+filesList).find('.download').click(function(){
                    downloadFile(res[1],res[3]);
                });
            }
        });     
    }
});

/**
 * 根据id删除文件
 * @param id
 */
function deleteFile(id,fileName){
    if(confirm('请注意，删除的附件将无法恢复，是否确认删除？')){
        var indexUrl = path + '/sysUploadController/upload';
        $.ajax({
            async:false,
            type:'post',
            url: path + '/sysUploadController/deleteFile/' + fileName + "/" + id,
            dataType:'json',
            success:function(obj){
                var message = obj.message;
                var type = obj.type;
                var root = obj.root;
                if(type == 'success'){
                    $("."+id).remove();
                    new TipBox({type:type,str:message,hasBtn:true,setTime:1500,callBack:function(){
                        if(root != '' && root != null && root != 'null'){
                            $(window.location).attr('href', indexUrl);
                        }
                    }});
                }else{
                    new TipBox({type:type,str:message,hasBtn:true})
                }
            }
        });
    }
}