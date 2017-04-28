<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp"%>
<jsp:include page="/static/public.jsp" />
<html>

<head>
<meta charset="UTF-8">
<title>Table</title>
<!-- <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="css/global.css" media="all">
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
		<link rel="stylesheet" href="css/table.css" /> -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/global.css" media="all">
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/table.css">
</head>

<body>
	<div class="admin-main">

		<blockquote class="layui-elem-quote">
			<a href="javascript:;" class="layui-btn layui-btn-small" id="add">
				<i class="layui-icon">&#xe608;</i>
				添加信息
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
			<legend>数据列表</legend>
			<div class="layui-field-box">
				<table class="site-table table-hover">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="selected-all">
							</th>
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
								<td>
									<input type="checkbox">
								</td>
								<td>${menu.title }</td>
								<td><i class="${menu.icon }"></i></td>
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
		<div class="admin-table-page">
			<div id="page" class="page"></div>
		</div>
		<input type="hidden" value="${page.pages }" id="pages">
		<input type="hidden" value="${page.pageNum }" id="pageNum">
	</div>
	<script>
			layui.config({
				base: path + '/static/plugins/layui/modules/'
			});

			layui.use(['icheck', 'laypage','layer'], function() {
				var $ = layui.jquery,
					laypage = layui.laypage,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
				//表单校验
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-green'
				});
				//分页
				laypage({
					cont: 'page',
					pages: $("#pages").val(), //总页数
					groups: 5, //连续显示分页数
					curr: $("#pageNum").val(), 
					jump: function(obj, first) {
						//得到了当前页，用于向服务端请求对应数据
						//var curr = obj.curr;
						if(!first) {
							//layer.msg('第 '+ obj.curr +' 页');
							location.href = path + '/system/menuManage?pageNo='+obj.curr;
						}
					}
				});

				$('#search').on('click', function() {
					parent.layer.alert('你点击了搜索按钮');
				});

				$('#add').on('click', function() {
					$.get('temp/edit-form.html', null, function(form) {
						layer.open({
							type: 1,
							title: '添加表单',
							content: form,
							btn: ['保存', '取消'],
							area: ['600px', '400px'],
							maxmin: true,
							yes: function(index) {
								console.log(index);
							},
							full: function(elem) {
								var win = window.top === window.self ? window : parent.window;
								$(win).on('resize', function() {
									var $this = $(this);
									elem.width($this.width()).height($this.height()).css({
										top: 0,
										left: 0
									});
									elem.children('div.layui-layer-content').height($this.height() - 95);
								});
							}
						});
					});
				});

				$('#import').on('click', function() {
					var that = this;
					var index = layer.tips('只想提示地精准些', that,{tips: [1, 'white']});
					$('#layui-layer'+index).children('div.layui-layer-content').css('color','#000000');
				});

				$('.site-table tbody tr').on('click', function(event) {
					var $this = $(this);
					var $input = $this.children('td').eq(0).find('input');
					$input.on('ifChecked', function(e) {
						$this.css('background-color', '#EEEEEE');
					});
					$input.on('ifUnchecked', function(e) {
						$this.removeAttr('style');
					});
					$input.iCheck('toggle');
				}).find('input').each(function() {
					var $this = $(this);
					$this.on('ifChecked', function(e) {
						$this.parents('tr').css('background-color', '#EEEEEE');
					});
					$this.on('ifUnchecked', function(e) {
						$this.parents('tr').removeAttr('style');
					});
				});
				$('#selected-all').on('ifChanged', function(event) {
					var $input = $('.site-table tbody tr td').find('input');
					$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
				});
			});
		</script>
</body>

</html>