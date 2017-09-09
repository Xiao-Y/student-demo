<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>流程模板列表</title>
<jsp:include page="/pub/pubTableCss.jsp" />
<jsp:include page="/pub/pubTableJs.jsp" />
<script type="text/javascript" src="${ctx}/static/page/activiti/deploy/actDeploy.js"></script>
<script type="text/javascript" src="${ctx}/static/js/extend/pubPopForm.js"></script>
</head>
<body>
	<div class="admin-main">
		<blockquote class="layui-elem-quote">
			<a href="javascript:;" class="layui-btn layui-btn-small" id="add">
				<i class="layui-icon">&#xe608;</i>
				添加模板
			</a>
			<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
				<i class="layui-icon">&#xe615;</i>
				搜索
			</a>
			<a href="${ctx}/sysActDeploy/queryDeployList?pageNo=${page.pageNum }" class="layui-btn layui-btn-small">
				<i class="fa fa-refresh" aria-hidden="true"></i>
				刷新
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
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="actModelList">
						<c:forEach var="deploy" items="${page.list}" varStatus="status">
							<tr>
								<td>
									<input type="checkbox">
								</td>
								<td>${deploy.id}</td>
								<td>${deploy.name }</td>
								<td>
									<fmt:formatDate value="${deploy.deploymentTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<a href="${ctx }/sysAct/viewPicDepId/image/${deploy.id}" target="_blank" class="layui-btn layui-btn-normal layui-btn-mini">
										<i class="fa fa-eye" aria-hidden="true"></i>
										预览
									</a>
									<a href="${ctx }/process-editor/modeler.html?modelId=${deploy.id}" target="_blank"
										class="layui-btn layui-btn-mini">
										<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
										编辑
									</a>
									<a href="${ctx }/sysAct/export/${deploy.id}" class="layui-btn layui-btn-mini" target="_blank">
										<i class="fa fa-shopping-cart" aria-hidden="true"></i>
										导出XML
									</a>
									<a href="javascript:;" name="deploy" data-id="${deploy.id }" data-name="${deploy.name }"
										class="layui-btn layui-btn-warm layui-btn-mini">
										<i class="fa fa-anchor" aria-hidden="true"></i>
										部署
									</a>
									<a href="javascript:;" data-opt="del" data-id="${status.index }" title="actModelList"
										url="${ctx }/sysAct/deleteModel/${deploy.id}" class="layui-btn layui-btn-danger layui-btn-mini">
										<i class="fa fa-times" aria-hidden="true"></i>
										删除
									</a>
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