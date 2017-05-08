<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp"%>
<jsp:include page="/static/public.jsp" />
<html>

<head>
<meta charset="UTF-8">
<title>自动任务管理</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/global.css" media="all">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/table.css">
<script type="text/javascript" src="${ctx }/page/system/autoTaskManage.js"></script>
</head>
<body>
	<div class="admin-main">

		<blockquote class="layui-elem-quote">
			<a href="javascript:;" class="layui-btn layui-btn-small" id="add">
				<i class="layui-icon">&#xe608;</i> 添加
			</a>
			<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
				<i class="layui-icon">&#xe615;</i> 搜索
			</a>
		</blockquote>
		<div class="layui-form">
			<input type="checkbox" checked name="open" lay-skin="switch" lay-text="启用|禁用">
			<div class="layui-form-item" pane>
				<label class="layui-form-label">开关开</label>
				<div class="layui-input-block">
					<input type="checkbox" checked name="open" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF">
					<input type="checkbox" name="open" lay-skin="switch" lay-filter="switchTest" lay-text="启用|禁用">
				</div>
			</div>
		</div>
		<fieldset class="layui-elem-field">
			<legend>自动任务列表</legend>
			<div class="layui-field-box">
				<table class="site-table table-hover">
					<thead>
						<tr>
							<th>任务分组</th>
							<th>任务名称</th>
							<th>cron表达式</th>
							<th>任务是否有状态</th>
							<th>BeanClass</th>
							<th>SpringId</th>
							<th>执行方法</th>
							<th>描述</th>
							<th>创建时间</th>
							<th>更新时间</th>
							<th>任务状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="task" items="${page.list}">
							<tr>
								<td>${task.jobGroup }</td>
								<td>${task.jobName }</td>
								<td>${task.cronExpression }</td>
								<td>${task.isConcurrent }</td>
								<td>${task.beanClass }</td>
								<td>${task.springId }</td>
								<td>${task.methodName }</td>
								<td>${task.description }</td>
								<td>
									<fmt:formatDate value="${task.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<fmt:formatDate value="${task.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<%-- ${task.jobStatus } --%>

								</td>
								<td>
									<a href="${ctx }/sysAutoTask/editAutoTask" class="layui-btn layui-btn-mini">编辑</a>
									<a href="javascript:;" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</fieldset>
		<jsp:include page="/static/pubPage.jsp" />
	</div>
</body>
</html>