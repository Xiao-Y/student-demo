<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/taglib.jsp"%>
<%@ include file="/pub/pubTips.jsp"%>
<html>
<head>
<title>请假申请</title>
<jsp:include page="/pub/pubFormCss.jsp" />
<jsp:include page="/pub/pubFormJs.jsp" />
<script type="text/javascript" src="${ctx }/page/apply/form-key/leaveApply.js"></script>
</head>

<body>
	<fieldset class="layui-elem-field">
		<legend>请假申请信息-外置表单</legend>
		<form id="dataForm" class="layui-form layui-form-pane1" style="margin: 15px;" data-type="ajax" action="${ctx }/formkey/applyLeave/saveLeave">
			<input type="hidden" name="processDefinitionKey" value="${leaveDto.processDefinitionKey }">
			${dataForm }
		</form>
	</fieldset>
</body>
</html>
