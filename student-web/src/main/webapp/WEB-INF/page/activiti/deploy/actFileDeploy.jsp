<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<%@ include file="/pub/pubTips.jsp" %>

<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script type="text/javascript">
    var path = "${ctx}";
</script>
<link rel="stylesheet" href="${ctx }/static/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx }/static/css/upload.css">
<script type="text/javascript" src="${ctx }/static/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
        src="${ctx }/static/plugins/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript"
        src="${ctx }/static/plugins/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${ctx }/static/plugins/jQuery-File-Upload/js/jquery.fileupload.js"></script>

<form id="deploy" class="layui-form" action="${ctx}/sysActDeploy/saveFileDeploy" enctype="multipart/form-data">
    <!-- 文件上传信息 -->
    <table width="100%">
        <tr>
            <td>
                <div class="jquery-fileupload">
                    <div class="uploadBtn">
                        <input id="fileupload" type="file" name="zipFile" multiple/>
                        <span>上传文件</span>
                    </div>
                </div>
            </td>
        </tr>
        <tbody class="uploadfiles"></tbody>
    </table>
</form>

<script>
    $('#fileupload').fileupload({
        url: path + '/sysActDeploy/saveFileDeploy',
        autoUpload: true,
        sequentialUploads: true,
        add: function (e, data) {
            var filename = data.files[0].name;
            var filesListHTML =
                '<tr class="filesList">' +
                '<td colspan="6">' +
                '<p class="name">' + filename + '&nbsp;&nbsp;<span class="displayProgress" style="color: green;">00%</span>' + '</p>' +
                '<div class="progress progress-striped active" style="width: 98%">' +
                '<div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div>' +
                '</div>' +
                '<strong class="error"></strong>' +
                '</td>' +
                '</tr>';
            $('.uploadfiles').append(filesListHTML);
            data.context = $(".filesList");
            data.submit();
        },
        //单个进度条
        progress: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            data.context.find(".displayProgress").text(progress + '%');
            data.context.find('.progress-bar').css('width', progress + '%');
        },
        //上传失败
        fail: function (e, data) {
            data.context.find('.error').text('上传失败');
        },
        //上传完成
        done: function (e, data) {
            //data.context.find('.progress').parent().parent().remove();
            //关闭窗口
            layer.close();
            var success = data.success;
            if(success == true){

            }
            /*$.each(data.files, function (index, file) {
                var res = data.result.split(",");
                if (res[0] == "success:") {

                }
            });*/
        }
    });
</script>