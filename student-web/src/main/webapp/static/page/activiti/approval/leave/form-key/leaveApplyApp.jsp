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
<script type="text/javascript" src="${ctx }/page/activiti/approval/leaveApplyApp.js"></script>
</head>

<body>
	<div id="dataForm" class="layui-form layui-form-pane1" style="margin: 15px;">${dataForm }</div>
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
						<c:forEach var="comment" items="${comments }">
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
	<input type="hidden" id="id" name="id" value="${leaveDto.id }">
	<input type="hidden" id="taskId" name="taskId" value="${leaveDto.taskId }">
	<input type="hidden" id="processInstanceId" name="processInstanceId" value="${leaveDto.processInstanceId }">
</body>
<script type="text/javascript">
	$(function() {
		//请假类型赋值
		$("#leaveType").val($("#leaveTypeTemp").val());
		var id = $("#id").val();
		var taskId = $("#taskId").val();
		var processInstanceId = $("#processInstanceId").val();
		$("#submitForm").attr("action", path + "/formkey/approvalLeave/saveLeaveApplyApp/" + id + "/" + taskId + "/" + processInstanceId);
	});
</script>
</html>
