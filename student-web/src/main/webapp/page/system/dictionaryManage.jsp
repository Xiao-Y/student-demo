<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>菜单列表</title>
<jsp:include page="/pub/pubTableCss.jsp" />
<jsp:include page="/pub/pubJs.jsp" />
<script type="text/javascript" src="${ctx }/page/system/dictionaryManage.js"></script>
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
							<th>模块名称</th>
							<th>模块CODE</th>
							<th>字段名称</th>
							<th>字段CODE</th>
							<th>下拉显示</th>
							<th>下拉VALUE</th>
							<th>创建时间</th>
							<th>更新时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dic" items="${page.list}">
							<tr>
								<td>${dic.modelName }</td>
								<td>${dic.modelCode }</td>
								<td>${dic.fieldName }</td>
								<td>${dic.fieldCode }</td>
								<td>${dic.displayField }</td>
								<td>${dic.valueField }</td>
								<td>
									<fmt:formatDate value="${dic.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<fmt:formatDate value="${dic.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
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