<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/taglib.jsp"%>
<%@ include file="/pub/pubTips.jsp"%>
<html>
<head>
<title>Queue</title>
<jsp:include page="/pub/pubFormCss.jsp" />
<jsp:include page="/pub/pubFormJs.jsp" />
<script type="text/javascript" src="${ctx }/page/rabbitMQ/direct.js"></script>
</head>

<body>
	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		<ul class="layui-tab-title">
			<li class="layui-this">发送Direct</li>
			<li>读取Direct</li>
			<li>发送ListenerDirect</li>
			<li>发送确认ListenerDirect</li>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<form id="formLeave" class="layui-form layui-form-pane1" style="margin: 15px;" data-type="ajax"
					action="${ctx }/rabbitMQ/directSender">
					<div class="layui-form-item layui-form-text">
						<textarea placeholder="请输入内容" lay-verify="required" class="layui-textarea" name="message"></textarea>
					</div>
					<div class="layui-form-item">
						<button class="layui-btn" lay-submit lay-filter="*">发送</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</form>
			</div>
			<div class="layui-tab-item">
				<div style="margin: 15px;">
					<div class="layui-form-item">
						<textarea readonly="readonly" class="layui-textarea" name="readMsgTx"></textarea>
					</div>
					<div class="layui-form-item">
						<input type="hidden" class="pathUrl" value="${ctx }/rabbitMQ/readDirectMessage">
						<button class="layui-btn" name="readMsgBt" type="button">读取</button>
					</div>
				</div>
			</div>
			<div class="layui-tab-item">
				<form id="formLeave" class="layui-form layui-form-pane1" style="margin: 15px;" data-type="ajax"
					action="${ctx }/rabbitMQ/queueListenerSender">
					<div class="layui-form-item layui-form-text">
						<textarea placeholder="请输入内容" lay-verify="required" class="layui-textarea" name="message"></textarea>
					</div>
					<div class="layui-form-item">
						<button class="layui-btn" lay-submit lay-filter="*">发送ListenerQueue</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</form>
			</div>
			<div class="layui-tab-item">
				<form id="formLeave" class="layui-form layui-form-pane1" style="margin: 15px;" data-type="ajax"
					action="${ctx }/rabbitMQ/queueConfirmListenerSender">
					<div class="layui-form-item layui-form-text">
						<textarea placeholder="请输入内容" lay-verify="required" class="layui-textarea" name="message"></textarea>
					</div>
					<div class="layui-form-item">
						<button class="layui-btn" lay-submit lay-filter="*">发送确认ListenerQueue</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
