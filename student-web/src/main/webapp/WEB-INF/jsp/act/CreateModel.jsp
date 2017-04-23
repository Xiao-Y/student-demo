<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Activeti Model</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
.h1 {
	margin: 0 auto;
}

#model {
	width: 48%;
	border: 1px solid blue;
	height: 60%;
	align: center;
	margin: 0 auto;
}

body {
	text-align: center;
}

div {
	text-align: center;
}

textarea {
	width: 80%;
	height: 100px;
	border: 1px solid gray;
}

button {
	background-color: rgb(62, 156, 66);
	border: none;
	font-weight: bold;
	color: white;
	height: 30px;
}
</style>
</head>

<body>
	<h1>Create Activeti Model</h1>
	<form action="<%=basePath%>/activitiController/saveModel">
		<div id="model">
			<h2>Model</h2>
			key：
			<input type="text" name="key">
			<br> 名称：
			<input type="text" name="name">
			<br> 简介：
			<textarea name="description"></textarea>
			<br> <br>
			<button type="submit">创建</button>
			<button type="reset">取消</button>
			<br>
		</div>
	</form>
</body>
</html>
