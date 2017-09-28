<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>请假申请列表</title>
<jsp:include page="/pub/pubTableCss.jsp" />
<jsp:include page="/pub/pubJs.jsp" />
<script type="text/javascript" src="${ctx }/static/page/activiti/apply/leave/leaveApplyList.js"></script>
<script type="text/javascript" src="${ctx}/static/js/extend/pubPopForm.js"></script>

</head>
<body>
	<div class="admin-main">
		<blockquote class="layui-elem-quote">
			<a href="${ctx }/applyLeave/editLeave" class="layui-btn layui-btn-small">
				<i class="layui-icon">&#xe608;</i> 请假申请
			</a>
			<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
				<i class="layui-icon">&#xe615;</i> 搜索
			</a>
		</blockquote>
		<fieldset class="layui-elem-field">
			<legend>请假申请列表</legend>
			<div class="layui-field-box">
				<table class="site-table table-hover">
					<thead>
						<tr>
							<th>假种</th>
							<th>状态</th>
							<th>原因</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>申请时间</th>
							<th>当前节点</th>
							<th>任务创建时间</th>
							<th>流程状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="leave" items="${page.list}">
							<c:set var="task" value="${leave.task }" />
							<c:set var="pi" value="${leave.historicProcessInstance }" />
							<tr>
								<td>${leave.leaveType }</td>
								<td>${leave.status }</td>
								<td>${leave.reason }</td>
								<td>
									<fmt:formatDate value="${leave.startTime }" pattern="yyyy-MM-dd" />
								</td>
								<td>
									<fmt:formatDate value="${leave.endTime }" pattern="yyyy-MM-dd" />
								</td>
								<td>
									<fmt:formatDate value="${leave.applyTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<a target="_blank" title="点击查看流程图" href='${ctx }/sysAct/openActivitiProccessImagePage/leaveComment/${leave.processInstanceId }'> ${leave.taskName } </a>
								</td>
								<td>
									<fmt:formatDate value="${task.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<b title='流程版本号'>V: ${leave.processDefinition.version }</b>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</fieldset>
		<!-- 分页 -->
		<jsp:include page="/pub/pubPage.jsp" />
	</div>
</body>

</html>