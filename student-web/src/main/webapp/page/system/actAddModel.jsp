<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="diagram" class="layui-form" action="${ctx}/sysAct/diagram">
	<div class="layui-form-item">
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">KEY</label>
		<div class="layui-input-block">
			<input type="text" name="key" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">模板名称</label>
		<div class="layui-input-block">
			<input type="text" id="name" name="name" placeholder="请输入" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">简介</label>
		<div class="layui-input-block">
			<textarea name="description" placeholder="请输入" class="layui-textarea"></textarea>
		</div>
	</div>
</form>