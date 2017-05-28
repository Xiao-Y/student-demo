<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/taglib.jsp"%>
<%@ include file="/pub/pubTips.jsp"%>
<html>
<head>
<title>请假申请</title>
<jsp:include page="/pub/pubCss.jsp" />
<jsp:include page="/pub/pubJs.jsp" />
<script type="text/javascript" src="${ctx }/page/apply/leaveApply.js"></script>
</head>

<body>
	<form class="layui-form layui-form-pane1" data-type="ajax" action="${ctx }/applyLeave/saveLeave">
		<div style="margin: 0 auto; width: 400px;">
			<div class="layui-form-item">
				<label class="layui-form-label"></label>
				<div class="layui-input-block"></div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">请假类型：</label>
				<div class="layui-input-block">
					<select name="leaveType" lay-verify="required" lay-search>
						<option value=""></option>
						<option value="1">公休</option>
						<option value="1">病假</option>
						<option value="2">调休</option>
						<option value="3">事假</option>
						<option value="4">婚假</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">开始时间：</label>
				<div class="layui-input-block">
					<input type="text" name="startTime" id="startTime" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input"
						onclick="layui.laydate({elem: this})">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">结束时间：</label>
				<div class="layui-input-block">
					<input type="text" name="endTime" id="endTime" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input"
						onclick="layui.laydate({elem: this})">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">请假原因：</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入内容"  lay-verify="required" class="layui-textarea" name="reason"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="*">申请</button>
					<button type="reset" id="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
