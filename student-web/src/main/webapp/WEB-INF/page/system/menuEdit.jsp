<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/taglib.jsp"%>
<%@ include file="/pub/pubTips.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>菜单列表</title>
<jsp:include page="/pub/pubFormCss.jsp" />
<jsp:include page="/pub/pubFormJs.jsp" />
<script type="text/javascript" src="${ctx }/static/page/system/menuEdit.js"></script>
</head>
<body style="padding: 10px;">
	<form class="layui-form layui-form-pane1" data-type="ajax" action="${ctx }/sysMenu/menuSave">
		<input type="hidden" name="id" value="${menu.id }">
		<input type="hidden" name="pageNo" value="${menu.pageNo }">
		<div class="layui-form-item">
			<label class="layui-form-label">菜单名称</label>
			<div class="layui-input-block">
				<input type="text" name="title" lay-verify="required" required placeholder="请输入菜单名称" autocomplete="off"
					class="layui-input" value="${menu.title }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">菜单图标</label>
			<div class="layui-input-block">
				<input type="text" name="icon" autocomplete="off" class="layui-input" value="${menu.icon }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">是否展开</label>
			<div class="layui-input-inline">
				<input type="hidden" id="spread_radio" def="false" value="${menu.spread}">
				<input type="radio" name="spread" value="true" title="是">
				<input type="radio" name="spread" value="false" title="否">
			</div>
			<label class="layui-form-label">是否有效</label>
			<div class="layui-input-inline">
				<input type="hidden" id="validind_radio" def="true" value="${menu.validind }">
				<input type="radio" name="validind" value="true" title="是">
				<input type="radio" name="validind" value="false" title="否">
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">菜单链接</label>
			<div class="layui-input-block">
				<input type="text" name="href" id="href" lay-verify="bean" value="${menu.href }" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">父级菜单</label>
			<div class="layui-input-block">
				<input type="hidden" id="pid_select" def="0" value="${menu.pid }">
				<select id="pid" name="pid" lay-filter="pid">
					<option value="0" selected="selected">0-父级菜单</option>
					<c:forEach items="${pids }" var="pid">
						<option value="${pid.id }">${pid.id }-${pid.title }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">排序序号</label>
			<div class="layui-input-block">
				<input type="text" name="displayno" lay-verify="required" value="${menu.displayno }" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
				<button class="layui-btn layui-btn-primary" type="reset">重置</button>
			</div>
		</div>
	</form>
</body>
</html>