<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>菜单列表</title>
<jsp:include page="/pub/pubTableCss.jsp" />
<jsp:include page="/pub/pubJs.jsp" />
<script type="text/javascript" src="${ctx }/page/system/menuManage.js"></script>
</head>
<body>
	<div class="admin-main">
		<blockquote class="layui-elem-quote">
			<a href="javascript:;" class="layui-btn layui-btn-small" id="add">
				<i class="layui-icon">&#xe608;</i> 添加信息
			</a>
			<a href="#" class="layui-btn layui-btn-small" id="import">
				<i class="layui-icon">&#xe608;</i> 导入信息
			</a>
			<a href="#" class="layui-btn layui-btn-small">
				<i class="fa fa-shopping-cart" aria-hidden="true"></i> 导出信息
			</a>
			<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
				<i class="layui-icon">&#xe615;</i> 搜索
			</a>
		</blockquote>
		<fieldset class="layui-elem-field">
			<legend>数据列表</legend>
			<div class="layui-field-box">
				<table class="site-table table-hover">
					<thead>
						<tr>
							<th>菜单名</th>
							<th>图标</th>
							<th>是否展开</th>
							<th>链接</th>
							<th>状态</th>
							<th>排序值</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="menu" items="${page.list}">
							<tr>
								<td>${menu.title }</td>
								<td>
									<i class="fa ${menu.icon }"></i>
								</td>
								<td>${menu.spread }</td>
								<td>${menu.href }</td>
								<td>${menu.validind }</td>
								<td>${menu.displayno }</td>
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
		<!-- 分页 -->
		<jsp:include page="/pub/pubPage.jsp" />
	</div>
</body>

</html>