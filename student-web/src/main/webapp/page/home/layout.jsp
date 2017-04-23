﻿<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/taglib.jsp"%>
<jsp:include page="/static/public.jsp" />
<html>

<head>
<meta charset="utf-8">
<title>Layout</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

</head>

<body>
	<div class="layui-layout layui-layout-admin beg-layout-container">
		<div class="layui-header beg-layout-header">
			<div class="beg-layout-main beg-layout-logo">
				<a href="#" target="_blank">LOGO</a>
			</div>
			<div class="beg-layout-main beg-layout-side-toggle">
				<i class="fa fa-bars" aria-hidden="true"></i>
			</div>
			<!--一级菜单-->
			<div class="beg-layout-main beg-layout-menu" id="menu">
				<ul class="layui-nav beg-layout-nav" lay-filter="">
					<li class="layui-nav-item layui-this"><a href="javascript:;" data-module-id="1">
							<i class="fa fa-desktop" aria-hidden="true"></i> <cite>内容管理</cite>
						</a></li>
					<li class="layui-nav-item"><a href="javascript:;">
							<i class="fa fa-shopping-bag" aria-hidden="true"></i> <cite>商品管理</cite>
						</a></li>
					<li class="layui-nav-item"><a href="javascript:;" data-module-id="3">
							<i class="fa fa-users" aria-hidden="true"></i> <cite>会员管理</cite>
						</a></li>
					<li class="layui-nav-item"><a href="javascript:;">
							<i class="fa fa-tv" aria-hidden="true"></i> <cite>订单管理</cite>
						</a></li>
					<li class="layui-nav-item"><a href="javascript:;">
							<i class="fa fa-gears" aria-hidden="true"></i> <cite>设置</cite>
						</a></li>
					<li class="layui-nav-item"><a href="javascript:;">
							<i class="fa fa-plug" aria-hidden="true"></i> <cite>扩展</cite>
						</a></li>
					<li class="layui-nav-item"><a href="javascript:;">
							<i class="fa fa-paper-plane" aria-hidden="true"></i> <cite>社区</cite>
						</a></li>
				</ul>
			</div>
			<div class="beg-layout-main beg-layout-panel">
				<ul class="layui-nav beg-layout-nav" lay-filter="user">
					<li class="layui-nav-item"><a href="javascript:;" class="beg-layou-head">
							<img src="${ctx}/static/images/0.jpg" />
							<span>beginner</span>
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" data-tab="true" data-url='user.html'>
									<i class="fa fa-user-circle" aria-hidden="true"></i> <cite>个人信息</cite>
								</a>
							</dd>
							<dd>
								<a href="javascript:;" data-tab="true" data-url="setting.html">
									<i class="fa fa-gear" aria-hidden="true"></i> <cite>设置</cite>
								</a>
							</dd>
							<dd>
								<a href="login.html">
									<i class="fa fa-sign-out" aria-hidden="true"></i> <cite>注销</cite>
								</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>
		<!--侧边导航栏-->
		<div class="layui-side beg-layout-side" id="side" lay-filter="side"></div>
		<!--内容区域-->
		<div class="layui-body beg-layout-body">
			<div class="layui-tab layui-tab-brief layout-nav-card" lay-filter="layout-tab" style="border: 0; margin: 0; box-shadow: none; height: 100%;">
				<ul class="layui-tab-title">
					<li class="layui-this"><a href="javascript:;">
							<i class="fa fa-dashboard" aria-hidden="true"></i> <cite>控制面板</cite>
						</a></li>
				</ul>
				<div
					style="width: 41px; height: 41px; text-align: center; line-height: 41px; position: absolute; right: 10px; background-color: #ccc; top: 0; font-size: 18px; cursor: pointer;">
					<i class="fa fa-toggle-down" aria-hidden="true"></i>
					<!--<ul class="layui-nav" lay-filter="">
							<li class="layui-nav-item">
								<a href="javascript:;">&nbsp;</a>
								<dl class="layui-nav-child">
									<dd>
										<a href="javascript:">关闭当前</a>
									</dd>
									<dd>
										<a href="javascript:">关闭所有</a>
									</dd>
								</dl>
							</li>
						</ul>-->
				</div>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<iframe src="${ctx}/home/main"></iframe>
					</div>
				</div>
			</div>
		</div>
		<!--页脚-->
		<div class="layui-footer beg-layout-footer">
			<p>
				2016 &copy;
				<a href="#">beginner.zhengjinfan.cn</a>
				LGPL license
			</p>
		</div>
	</div>
	<div id="contextmenu" class="layout-tab-contextmenu">
		<ul class="layui-nav " lay-filter>
			<li class="layui-nav-item " data-toggle="closeCurrent"><a href="javascsript:; ">关闭标签</a></li>
			<li class="layui-nav-item " data-toggle="refresh"><a href="javascsript:; ">刷新</a></li>
			<li class="layui-nav-item " data-toggle="closeLeft"><a href="javascsript:; ">关闭左侧</a></li>
			<li class="layui-nav-item " data-toggle="closeRight"><a href="javascsript:; ">关闭右侧</a></li>
			<li class="layui-nav-item " data-toggle="closeOther"><a href="javascsript:; ">关闭其他</a></li>
			<li class="layui-nav-item " data-toggle="closeAll"><a href="javascsript:; ">关闭所有</a></li>
		</ul>
	</div>
	<!--<script>
            //这是js的枚举，哈哈。
			var closedEnum = {
				closeCurrent:'closeCurrent', //关闭当前
				refresh:'refresh',           //刷新
				closeLeft:'closeLeft',       //关闭左侧
				closeRight:'closeRight',     //关闭右侧
				closeOther:'closeOther',     //关闭其他
				closeAll:'closeAll'          //关闭所有
			};
		
			layui.use('jquery', function() {
				var $ = layui.jquery;

				var $contextMenu = $('#contextmenu');

				$(document).on('mousedown', '.layout-nav-card > ul.layui-tab-title', function (e) {
                    //防止事件冒泡
				    e.preventDefault();
					//鼠标右击
					if(e.which === 3) {
						var $this = $(e.target);
					    //元素定位
						$contextMenu.css({
						    left: e.pageX-2,
						    top: e.pageY-2
						}).show()
						.children('ul.layui-nav').children('li.layui-nav-item').each(function () {
						    var $that = $(this);
						    //绑定点击事件
						    $that.on('click', function () {
						        var toggle = $that.data('toggle');
						        var localName = $this[0].localName;
						        var $li;
						        if (localName === 'ul') {
						            $li = $this.children('li.layui-this');
						        } else if (localName === 'i' || localName === 'cite') {
						            $li = $this.parent('li');
						        } else {
						            $li = $this;
						        }
						        switch (toggle) {
						            //关闭当前标签
						            case closedEnum.closeCurrent:
						                $li.children('i.layui-tab-close').click();
						                break;
						            case closedEnum.refresh:
						                layer.msg('你点击了刷新哦');
						                break;
						            case closedEnum.closeLeft:
						                layer.msg('你点击了关闭左侧哦');
						                break;
						            case closedEnum.closeRight:
						                layer.msg('你点击了关闭右侧哦');
						                break;
						            case closedEnum.closeOther:
						                layer.msg('你点击了关闭其他哦');
						                break;
						            case closedEnum.closeAll:
						                layer.msg('你点击了关闭所有哦');
						                break;
						            default:
						                break;
						        }

						        console.log($li);
						    });
						});

						
					    $contextMenu.on('mouseover', function (e) {
						    $(this).show();
						}).on('mouseout', function () {
						    $(this).hide();
						});
					}					
				});
				
				//禁用鼠标右键
				$(document).bind("contextmenu ", function(e) {
					return false;
				});
			});
		</script>-->
</body>

</html>