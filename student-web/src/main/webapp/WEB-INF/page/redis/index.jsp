<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pub/taglib.jsp"%>
<%@ include file="/pub/pubTips.jsp"%>
<html>
<head>
<title>Dubbo</title>
<jsp:include page="/pub/pubFormCss.jsp" />
<jsp:include page="/pub/pubFormJs.jsp" />
<script type="text/javascript" src="${ctx }/static/page/redis/index.js"></script>
</head>

<body>
	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		<ul class="layui-tab-title">
			<li class="layui-this">通过Redis测试</li>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-tab-item layui-show">
				<div style="margin: 15px;">
					<div class="layui-form-item">
						<textarea readonly="readonly" class="layui-textarea" name="readMsgTx"></textarea>
					</div>
					<div class="layui-form-item">
						<input type="hidden" class="pathUrl" value="${ctx }/redisController/sayHello">
						<button class="layui-btn" name="readMsgBt" type="button">调用</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
