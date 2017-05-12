<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>自动任务管理</title>
<jsp:include page="/static/public.jsp" />
<link rel="stylesheet" href="${ctx }/static/css/global.css" />
<link rel="stylesheet" href="${ctx }/static/css/table.css" />
<script type="text/javascript" src="${ctx }/page/system/autoTaskManage.js"></script>
<script type="text/javascript" src="${ctx}/static/pubForm.js"></script>
</head>
<body>
	<div class="admin-main">

		<blockquote class="layui-elem-quote">
			<a href="javascript:;" class="layui-btn layui-btn-small" id="add">
				<i class="layui-icon">&#xe608;</i>
				添加
			</a>
			<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
				<i class="layui-icon">&#xe615;</i>
				搜索
			</a>
		</blockquote>
		<fieldset class="layui-elem-field">
			<legend>自动任务列表</legend>
			<div class="layui-field-box">
				<table class="site-table table-hover">
					<thead>
						<tr>
							<th>任务分组</th>
							<th>任务名称</th>
							<th>cron表达式</th>
							<th>是否有状态</th>
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
								<td>
									<c:if test="${task.isConcurrent == 1 }">
										<i class="fa fa-check-circle fa-2x" />
									</c:if>
									<c:if test="${task.isConcurrent == 0 }">
										<i class="fa fa-ban fa-2x" />
									</c:if>
								</td>
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
									<div class="layui-form">
										<input name="jobId" type="hidden" value="${task.jobId }" />
										<input type="checkbox" value="${task.jobId }" <c:if test="${task.jobStatus == 1 }">checked</c:if>
											name=jobStatus lay-skin="switch" lay-text="启用|禁用" />
									</div>
								</td>
								<td>
									<a href="${ctx }/sysAutoTask/editAutoTask/${task.jobId}" class="layui-btn layui-btn-mini">编辑</a>
									<a href="javascript:;" data-opt="del" url="${ctx }/sysAutoTask/deleteAutoTask/${task.jobId}"
										class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
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