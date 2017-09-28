<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/taglib.jsp"%>
<%@ include file="/pub/pubTips.jsp"%>
<html>
<head>
<title>请假申请审批</title>
<jsp:include page="/pub/pubCss.jsp" />
<jsp:include page="/pub/pubFormCss.jsp" />
<jsp:include page="/pub/pubJs.jsp" />
<jsp:include page="/pub/pubFormJs.jsp" />
<script type="text/javascript" src="${ctx }/static/page/activiti/approval/leave/leaveApplyApp.js"></script>
</head>

<body>
	<fieldset class="layui-elem-field">
		<legend>请假申请信息</legend>
		<div class="layui-form layui-form-pane1" style="margin: 15px;">
			<div class="layui-form-item">
				<label class="layui-form-label">请假类型：</label>
				<div class="layui-input-block">
					<input type="hidden" id="leaveTypeTemp" value="${leaveDto.leaveType}">
					<select id="leaveType" name="leaveType" lay-verify="required" lay-search disabled="disabled">
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
						value="<fmt:formatDate value="${leaveDto.startTime }" pattern="yyyy-MM-dd" />" disabled="disabled">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">结束时间：</label>
				<div class="layui-input-block">
					<input type="text" name="endTime" id="endTime" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input"
						value="<fmt:formatDate value="${leaveDto.endTime }" pattern="yyyy-MM-dd" />" disabled="disabled">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">请假原因：</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入内容" lay-verify="required" class="layui-textarea" name="reason" disabled="disabled">${leaveDto.reason }</textarea>
				</div>
			</div>
	</fieldset>
	<fieldset class="layui-elem-field">
		<legend>申请审批</legend>
		<form class="layui-form layui-form-pane1" style="margin: 15px;" data-type="ajax" action="${ctx }/approvalLeave/saveLeaveApplyApp">
			<div class="layui-form-item">
				<label class="layui-form-label">审批：</label>
				<div class="layui-input-block">
					<input type="radio" name="applyPass" value="true" title="同意" checked>
					<input type="radio" name="applyPass" value="false" title="驳回">
				</div>
			</div>
			<input type="hidden" name="id" value="${leaveDto.id}">
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">批注信息：</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入内容" lay-verify="required" class="layui-textarea" name="commentInfo"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<input type="hidden" value="" id="outcome" name="outcome">
					<c:forEach var="transName" items="${transNames }">
						<button class="layui-btn" lay-submit lay-filter="*">${transName }</button>
					</c:forEach>
				</div>
			</div>
		</form>
	</fieldset>
	<fieldset class="layui-elem-field">
		<legend>历史批注信息</legend>
		<div class="layui-field-box">
			<div class="layui-field-box">
				<table class="site-table table-hover">
					<thead>
						<tr>
							<th>用户名</th>
							<th>时间</th>
							<th>批注信息</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="comment" items="${leaveDto.comments }">
							<tr>
								<td>${comment.userId }</td>
								<td>
									<fmt:formatDate value="${comment.time }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>${comment.fullMessage }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</fieldset>
</body>
<script type="text/javascript">
	$(function() {
		//请假类型赋值
		$("#leaveType").val($("#leaveTypeTemp").val());
	});
</script>
</html>
