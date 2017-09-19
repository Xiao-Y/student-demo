<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/pub/taglib.jsp" %>
<%@ include file="/pub/pubTips.jsp" %>

<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script type="text/javascript">
    var path = "${ctx}";
</script>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${ctx }/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx }/static/css/upload.css">
    <jsp:include page="/pub/pubTableCss.jsp"/>
    <jsp:include page="/pub/pubJs.jsp"/>
    <script type="text/javascript" src="${ctx }/static/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript"
            src="${ctx }/static/plugins/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
    <script type="text/javascript"
            src="${ctx }/static/plugins/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
    <script type="text/javascript" src="${ctx }/static/plugins/jQuery-File-Upload/js/jquery.fileupload.js"></script>
    <title>文件上传</title>
</head>
<body>
<form action="${ctx }/sysUploadController/upload" enctype="multipart/form-data">
    <input type="hidden" id="type" name="type" value="file">
    <div class="jquery-fileupload">
        <div class="uploadBtn">
            &nbsp;
            <input name="file" type="file" fileType="file" multiple/>
            <span>上传共享文件</span>
        </div>
        <div class="uploadBtn">
            &nbsp;
            <input name="file" type="file" fileType="workflowZip" multiple/>
            <span>上传流程zip</span>
        </div>
        <span class="tips"></span>
        <div style="clear: both;"></div>
        <!-- 文件列表 -->
        <table>
            <tbody class="uploadfiles">
            </tbody>
        </table>
    </div>
</form>

<!--上传的文件-->
<fieldset class="layui-elem-field">
    <legend>数据列表</legend>
    <div class="layui-field-box jquery-fileupload">
        <table class="site-table table-hover">
            <thead>
            <tr>
                <th style="text-align: center;" width="25%">文件名称</th>
                <th style="text-align: center;" width="15%">文件大小</th>
                <th style="text-align: center;" width="15%">文件类型</th>
                <th style="text-align: center;" width="15%">创建人</th>
                <th style="text-align: center;" width="15%">创建时间</th>
                <th style="text-align: center;" width="15%">文件操作</th>
            </tr>
            </thead>
            <tbody class="filesName">
            <c:forEach var="uploadLog" items="${page}">
                <tr class="${uploadLog.id}">
                    <td><p class="fileName">${uploadLog.fileName }</p></td>
                    <td><p class="fileType">${uploadLog.fileSizeF }</p></td>
                    <td><p class="fileSize">${uploadLog.fileType }</p></td>
                    <td><p class="createCode">${uploadLog.createCode }</p></td>
                    <td>
                        <p class="createTime">
                            <fmt:formatDate value="${uploadLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </p>
                    </td>
                    <td class="btns">
                        <button class="delete" name="deleteFile"
                                onclick="deleteFile('${uploadLog.id}','${uploadLog.fileName }');">删除
                        </button>&nbsp;
                        <button class="download">下载</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</fieldset>
</body>
<script type="text/javascript" src="${ctx }/static/page/system/uploadIndex.js"></script>
</html>