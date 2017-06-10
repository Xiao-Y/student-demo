<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ include file="/pub/taglib.jsp"%>
<html>
<head>
<title>流程状态</title>
<jsp:include page="/pub/pubCss.jsp" />
<jsp:include page="/pub/pubJs.jsp" />
</head>
<body>
	<div style="text-align: center;">
		<img src="<c:url value='/workFlow/getActivitiProccessImage/${processInstanceId }' />">
	</div>
	<fieldset class="layui-elem-field">
		<legend>历史批注信息</legend>
		<div class="layui-field-box">
			<div class="layui-field-box">
				<table class="site-table table-hover">
					<thead>
						<tr>
							<th>用户名</th>
							<th>时间</th>
							<th>批注信息</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="comment" items="${comments }">
							<tr>
								<td>${comment.userId }</td>
								<td>
									<fmt:formatDate value="${comment.time }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>${comment.fullMessage }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</fieldset>
</body>
</html>