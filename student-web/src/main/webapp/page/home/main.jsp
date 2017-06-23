<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>我的工作台</title>
<link rel="stylesheet" href="${ctx }/plugins/layui/css/layui.css" media="all" />
<script type="text/javascript" src="${ctx }/plugins/layui/layui.js"></script>
</head>

<body>
	<fieldset class="layui-elem-field">
		<legend>部署流程</legend>
		<div class="layui-field-box">
			<a href="${ctx }/sysAct/deployTest">部署</a>
		</div>
	</fieldset>
	<fieldset class="layui-elem-field">
		<legend>我的待办任务</legend>
		<div class="layui-field-box">内容区域</div>
	</fieldset>
</body>
</html>