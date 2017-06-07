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
				<input type="hidden" name="id" value="${leaveDto.id}">
				<input type="hidden" id="actionType" name="actionType" value="${leaveDto.actionType}">
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">请假类型：</label>
				<div class="layui-input-block">
					<input type="hidden" id="leaveTypeTemp" value="${leaveDto.leaveType}">
					<select id="leaveType" name="leaveType" lay-verify="required" lay-search>
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
						value="<fmt:formatDate value="${leaveDto.startTime }" pattern="yyyy-MM-dd" />" onclick="layui.laydate({elem: this})">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">结束时间：</label>
				<div class="layui-input-block">
					<input type="text" name="endTime" id="endTime" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input"
						value="<fmt:formatDate value="${leaveDto.endTime }" pattern="yyyy-MM-dd" />" onclick="layui.laydate({elem: this})">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">请假原因：</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入内容" lay-verify="required" class="layui-textarea" name="reason">${leaveDto.reason }</textarea>
				</div>
			</div>
			<div class="layui-form-item" id="saveLeaveDiv">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="*">申请</button>
					<button type="reset" id="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</div>
	</form>
	<div style="margin: 15px;">
		<form class="layui-form" action="">
			<blockquote class="layui-elem-quote">
				<p>添加批注信息</p>
			</blockquote>
			<div class="layui-form-item layui-form-text">
				<div class="layui-input-block">
					<textarea placeholder="请输入内容" lay-verify="required" class="layui-textarea" name="commentInfo"></textarea>
				</div>
			</div>
			<div class="layui-form-item" id="commentDiv">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="*">同意</button>
				</div>
			</div>
		</form>
	</div>
	<c:if test="${leaveDto.actionType == 'view'}">
		<div class="layui-field-box">
			<fieldset class="layui-elem-field">
				<legend>批注信息</legend>
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
										<fmt:formatDate value="${comment.time }" pattern="yyyy-MM-dd" />
									</td>
									<td>${comment.fullMessage }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</fieldset>
		</div>
	</c:if>
</body>
<script type="text/javascript">
	$(function() {
		$("#leaveType").val($("#leaveTypeTemp").val());
		var actionType = $("#actionType").val();
		if (actionType == 'view') {
			$("input").attr('disabled', true);
			$("textarea").attr('readonly', true);
			$(':radio').attr('disabled', true);
			$(':checkbox').attr('disabled', true);
			$(':button').attr('disabled', true);
			$('a').removeAttr('onclick');
			$('select').attr('disabled', true);

			$("#saveLeaveDiv").remove();
		}
	});
</script>
</html>
