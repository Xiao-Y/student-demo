<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp"%>
<jsp:include page="/static/public.jsp" />
<html>

<head>
<meta charset="UTF-8">
<title>Table</title>
<link rel="stylesheet" href="${ctx }/static/css/global.css" media="all">
<link rel="stylesheet" href="${ctx }/static/css/table.css">
</head>

<body>
	<div class="admin-main">
		<blockquote class="layui-elem-quote">
			<a href="javascript:;" class="layui-btn layui-btn-small" id="add">
				<i class="layui-icon">&#xe608;</i>
				添加模板
			</a>
			<a href="#" class="layui-btn layui-btn-small" id="import">
				<i class="layui-icon">&#xe608;</i>
				导入信息
			</a>
			<a href="#" class="layui-btn layui-btn-small">
				<i class="fa fa-shopping-cart" aria-hidden="true"></i>
				导出信息
			</a>
			<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
				<i class="layui-icon">&#xe615;</i>
				搜索
			</a>
		</blockquote>
		<fieldset class="layui-elem-field">
			<legend>流程模板列表</legend>
			<div class="layui-field-box">
				<table class="site-table table-hover">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="selected-all">
							</th>
							<th>ID</th>
							<th>名称</th>
							<th>KEY</th>
							<th>部署ID</th>
							<th>创建时间</th>
							<th>最后更新时间</th>
							<th>版本</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="model" items="${pages.list}">
							<tr>
								<td>
									<input type="checkbox">
								</td>
								<td>${model.id}</td>
								<td>${model.name }</td>
								<td>${model.key }</td>
								<td>${model.deploymentId }</td>
								<td>
									<fmt:formatDate value="${model.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<fmt:formatDate value="${model.lastUpdateTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>${model.version }</td>
								<td>
									<a href="/detail-1" target="_blank" class="layui-btn layui-btn-normal layui-btn-mini">预览</a>
									<a href="/manage/article_edit_1" class="layui-btn layui-btn-mini">编辑</a>
									<a href="javascript:;" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</fieldset>
		<div class="admin-table-page">
			<div id="page" class="page"></div>
		</div>
		<input type="hidden" value="${pages.pages }" id="pages">
		<input type="hidden" value="${pages.pageNum }" id="pageNum">
	</div>
	<script type="text/javascript" src="${ctx}/page/system/actModel.js"></script>
	<script type="text/javascript" src="${ctx}/static/pubForm.js"></script>
</body>

</html>