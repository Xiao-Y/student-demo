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
<script type="text/javascript" src="${ctx}/js/extend/pubTable.js"></script>

</head>
<body>
	<div class="admin-main">
		<blockquote class="layui-elem-quote">
			<a href="${ctx }/sysMenu/menuEdit" class="layui-btn layui-btn-small">
				<i class="layui-icon">&#xe608;</i>
				添加信息
			</a>
			<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
				<i class="layui-icon">&#xe615;</i>
				搜索
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
									<a href="${ctx }/sysMenu/menuEdit?id=${menu.id }" class="layui-btn layui-btn-mini">编辑</a>
									<a href="javascript:;" data-opt="del" url="${ctx }/sysMenu/menuDel?id=${menu.id }"
										class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
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