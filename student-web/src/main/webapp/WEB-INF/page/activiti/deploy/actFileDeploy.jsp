<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
.a-upload {
	padding: 4px 10px;
	height: 20px;
	line-height: 20px;
	position: relative;
	cursor: pointer;
	color: #888;
	background: #fafafa;
	border: 1px solid #ddd;
	border-radius: 4px;
	overflow: hidden;
	display: inline-block;
	*display: inline;
	*zoom: 1
}

.a-upload  input {
	position: absolute;
	font-size: 100px;
	right: 0;
	top: 0;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer
}

.a-upload:hover {
	color: #444;
	background: #eee;
	border-color: #ccc;
	text-decoration: none
}
</style>
<form id="deploy" class="layui-form" action="${ctx}/sysAct/saveFileDeploy" enctype="multipart/form-data">
	<div class="layui-form-item"></div>
	<div class="layui-form-item">
		<label class="layui-form-label">部署名称</label>
		<div class="layui-input-block">
			<input type="text" name="deployName" placeholder="请输入" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">上传文件</label>
		<div class="layui-input-inline">
			<a href="javascript:;" class="a-upload">
				<input type="file" name="zipFile" id="zipFile">
				点击这里上传文件
			</a>
		</div>
	</div>
</form>