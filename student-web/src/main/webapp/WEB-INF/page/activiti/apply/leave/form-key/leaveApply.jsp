<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/taglib.jsp"%>
<%@ include file="/pub/pubTips.jsp"%>
<html>
<head>
<title>请假申请</title>
<jsp:include page="/pub/pubFormCss.jsp" />
<jsp:include page="/pub/pubFormJs.jsp" />
<script type="text/javascript" src="${ctx }/static/page/activiti/apply/leave/form-key/leaveApply.js"></script>
</head>

<body>
	<fieldset class="layui-elem-field">
		<legend id="title">请假申请信息-外置表单</legend>
		<form id="dataForm" class="layui-form layui-form-pane1" style="margin: 15px;" data-type="ajax"
			action="${ctx }/formkey/applyLeave/saveLeave">
			<input type="hidden" id="id" name="id" value="${leaveDto.id}">
			<input type="hidden" id="status" name="status" value="${leaveDto.status}">
			<input type="hidden" name="processDefinitionKey" value="${leaveDto.processDefinitionKey }">
			<input type="hidden" name="processInstanceId" value="${leaveDto.processInstanceId }">
			${dataForm }
		</form>
	</fieldset>
</body>
<script type="text/javascript">
	$(function() {
		//请假类型赋值
		$("#leaveType").val($("#leaveTypeTemp").val());
		var status = $("#status").val();
		if (status == '7') {//请假申请信息修改
			$("#title").text("请假申请信息修改-外置表单");
			$("#dataForm").attr("action", path + "/formkey/applyLeave/updateLeave");
		}
	});
</script>
</html>
